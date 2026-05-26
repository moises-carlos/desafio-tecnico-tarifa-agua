package br.com.moisescarlos.aguatarifa.dto.response;

import java.math.BigDecimal;

public record DetalhesFaixaResponse(
        FaixaInfo faixa,
        Integer m3Cobrados,
        BigDecimal valorUnitario,
        BigDecimal subtotal
) {}