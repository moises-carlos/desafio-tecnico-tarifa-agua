package br.com.moisescarlos.aguatarifa.service.validator;

import br.com.moisescarlos.aguatarifa.dto.request.CategoriaConsumoRequest;
import br.com.moisescarlos.aguatarifa.dto.request.FaixaConsumoRequest;
import br.com.moisescarlos.aguatarifa.dto.request.TabelaTarifariaRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FaixaConsumoValidator {

    public void validar(TabelaTarifariaRequest request) {
        for (CategoriaConsumoRequest categoria : request.categorias()) {
            validarCategoria(categoria);
        }
    }

    private void validarCategoria(CategoriaConsumoRequest categoria) {
        List<FaixaConsumoRequest> faixas = categoria.faixas();

        validarCobertura(categoria, faixas);
        validarOrdem(faixas);
        validarSobreposicao(categoria, faixas);
    }

    private void validarCobertura(CategoriaConsumoRequest categoria, List<FaixaConsumoRequest> faixas) {
        boolean iniciaNaZero = faixas.stream()
                .anyMatch(f -> f.inicio() == 0);
        if (!iniciaNaZero) {
            throw new IllegalArgumentException(
                    "A categoria " + categoria.tipo() + " deve ter uma faixa iniciando em 0"
            );
        }
    }

    private void validarOrdem(List<FaixaConsumoRequest> faixas) {
        for (FaixaConsumoRequest faixa : faixas) {
            if (faixa.inicio() >= faixa.fim()) {
                throw new IllegalArgumentException(
                        "O início da faixa deve ser menor que o fim: " +
                                faixa.inicio() + " >= " + faixa.fim()
                );
            }
        }
    }

    private void validarSobreposicao(CategoriaConsumoRequest categoria, List<FaixaConsumoRequest> faixas) {
        List<FaixaConsumoRequest> ordenadas = faixas.stream()
                .sorted((a, b) -> a.inicio().compareTo(b.inicio()))
                .toList();

        for (int i = 0; i < ordenadas.size() - 1; i++) {
            FaixaConsumoRequest atual = ordenadas.get(i);
            FaixaConsumoRequest proxima = ordenadas.get(i + 1);
            if (atual.fim() >= proxima.inicio()) {
                throw new IllegalArgumentException(
                        "Faixas se sobrepõem na categoria " + categoria.tipo() +
                                ": [" + atual.inicio() + "-" + atual.fim() +
                                "] e [" + proxima.inicio() + "-" + proxima.fim() + "]"
                );
            }
        }
    }
}