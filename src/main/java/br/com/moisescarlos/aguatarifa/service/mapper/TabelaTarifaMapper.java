package br.com.moisescarlos.aguatarifa.service.mapper;

import br.com.moisescarlos.aguatarifa.dto.request.CategoriaConsumoRequest;
import br.com.moisescarlos.aguatarifa.dto.request.FaixaConsumoRequest;
import br.com.moisescarlos.aguatarifa.dto.request.TabelaTarifariaRequest;
import br.com.moisescarlos.aguatarifa.dto.response.CategoriaConsumoResponse;
import br.com.moisescarlos.aguatarifa.dto.response.FaixaConsumoResponse;
import br.com.moisescarlos.aguatarifa.dto.response.TabelaTarifariaResponse;
import br.com.moisescarlos.aguatarifa.model.CategoriaConsumo;
import br.com.moisescarlos.aguatarifa.model.FaixaConsumo;
import br.com.moisescarlos.aguatarifa.model.TabelaTarifa;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TabelaTarifaMapper {

    public TabelaTarifa toEntity(TabelaTarifariaRequest request) {
        TabelaTarifa tabela = new TabelaTarifa();
        tabela.setNome(request.nome());
        tabela.setDataVigencia(request.dataVigencia());

        List<CategoriaConsumo> categorias = request.categorias().stream()
                .map(categoriaRequest -> toCategoria(categoriaRequest, tabela))
                .toList();

        tabela.setCategorias(categorias);
        return tabela;
    }

    private CategoriaConsumo toCategoria(CategoriaConsumoRequest request, TabelaTarifa tabela) {
        CategoriaConsumo categoria = new CategoriaConsumo();
        categoria.setTipo(request.tipo());
        categoria.setTabelaTarifa(tabela);

        List<FaixaConsumo> faixas = request.faixas().stream()
                .map(faixaRequest -> toFaixa(faixaRequest, categoria))
                .toList();

        categoria.setFaixas(faixas);
        return categoria;
    }

    private FaixaConsumo toFaixa(FaixaConsumoRequest request, CategoriaConsumo categoria) {
        FaixaConsumo faixa = new FaixaConsumo();
        faixa.setInicio(request.inicio());
        faixa.setFim(request.fim());
        faixa.setValorUnitario(request.valorUnitario());
        faixa.setCategoriaConsumo(categoria);
        return faixa;
    }

    public TabelaTarifariaResponse toResponse(TabelaTarifa tabela) {
        List<CategoriaConsumoResponse> categorias = tabela.getCategorias().stream()
                .map(this::toCategoriaResponse)
                .toList();

        return new TabelaTarifariaResponse(
                tabela.getId(),
                tabela.getNome(),
                tabela.getDataVigencia(),
                categorias
        );
    }

    private CategoriaConsumoResponse toCategoriaResponse(CategoriaConsumo categoria) {
        List<FaixaConsumoResponse> faixas = categoria.getFaixas().stream()
                .map(this::toFaixaResponse)
                .toList();

        return new CategoriaConsumoResponse(
                categoria.getId(),
                categoria.getTipo(),
                faixas
        );
    }

    private FaixaConsumoResponse toFaixaResponse(FaixaConsumo faixa) {
        return new FaixaConsumoResponse(
                faixa.getId(),
                faixa.getInicio(),
                faixa.getFim(),
                faixa.getValorUnitario()
        );
    }
}