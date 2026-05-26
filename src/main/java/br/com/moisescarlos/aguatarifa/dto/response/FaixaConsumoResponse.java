package br.com.moisescarlos.aguatarifa.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record FaixaConsumoResponse(
        UUID id,
        Integer inicio,
        Integer fim,
        BigDecimal valorUnitario
) {}