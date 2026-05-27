package br.com.moisescarlos.aguatarifa.service;

import br.com.moisescarlos.aguatarifa.dto.request.CalculoRequest;
import br.com.moisescarlos.aguatarifa.dto.response.CalculoResponse;
import br.com.moisescarlos.aguatarifa.dto.response.DetalhesFaixaResponse;
import br.com.moisescarlos.aguatarifa.dto.response.FaixaInfo;

import br.com.moisescarlos.aguatarifa.model.FaixaConsumo;
import br.com.moisescarlos.aguatarifa.model.TabelaTarifa;
import br.com.moisescarlos.aguatarifa.repository.FaixaConsumoRepository;
import br.com.moisescarlos.aguatarifa.repository.TabelaTarifaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CalculoService {

    private final FaixaConsumoRepository faixaConsumoRepository;
    private final TabelaTarifaRepository tabelaTarifariaRepository;

    public CalculoResponse calcular(CalculoRequest request) {
        TabelaTarifa tabela = tabelaTarifariaRepository.findFirstByOrderByDataVigenciaDesc()
                .orElseThrow(() -> new IllegalArgumentException("Nenhuma tabela tarifária encontrada no sistema"));

        List<FaixaConsumo> faixas = faixaConsumoRepository
                .findByCategoriaConsumoTipoAndCategoriaConsumoTabelaTarifaId(
                        request.categoria(),
                        tabela.getId()
                );

        if (faixas.isEmpty()) {
            throw new IllegalArgumentException(
                    "Nenhuma faixa encontrada para a categoria " + request.categoria()
            );
        }

        List<FaixaConsumo> faixasOrdenadas = faixas.stream()
                .sorted(Comparator.comparingInt(FaixaConsumo::getInicio))
                .toList();

        return calcularProgressivo(request, faixasOrdenadas);
    }

    private CalculoResponse calcularProgressivo(CalculoRequest request, List<FaixaConsumo> faixas) {
        int consumoRestante = request.consumo();
        BigDecimal valorTotal = BigDecimal.ZERO;
        List<DetalhesFaixaResponse> detalhamento = new ArrayList<>();

        for (FaixaConsumo faixa : faixas) {
            if (consumoRestante <= 0) break;

            int capacidadeFaixa = (faixa.getInicio() == 0) ? faixa.getFim() : (faixa.getFim() - faixa.getInicio() + 1);
            int m3Cobrados = Math.min(consumoRestante, capacidadeFaixa);

            BigDecimal subtotal = faixa.getValorUnitario()
                    .multiply(BigDecimal.valueOf(m3Cobrados));

            valorTotal = valorTotal.add(subtotal);
            consumoRestante -= m3Cobrados;

            detalhamento.add(new DetalhesFaixaResponse(
                    new FaixaInfo(faixa.getInicio(), faixa.getFim()),
                    m3Cobrados,
                    faixa.getValorUnitario(),
                    subtotal
            ));
        }

        if (consumoRestante > 0) {
            throw new IllegalArgumentException("O consumo informado excede a cobertura máxima das faixas cadastradas na tabela tarifária.");
        }

        return new CalculoResponse(
                request.categoria(),
                request.consumo(),
                valorTotal,
                detalhamento
        );
    }
}