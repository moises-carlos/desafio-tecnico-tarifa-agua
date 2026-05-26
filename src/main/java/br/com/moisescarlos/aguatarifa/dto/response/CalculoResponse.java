package br.com.moisescarlos.aguatarifa.dto.response;

import br.com.moisescarlos.aguatarifa.model.enums.TipoCategoria;
import java.math.BigDecimal;
import java.util.List;

public record CalculoResponse(
        TipoCategoria categoria,
        Integer consumoTotal,
        BigDecimal valorTotal,
        List<DetalhesFaixaResponse> detalhamento
) {}