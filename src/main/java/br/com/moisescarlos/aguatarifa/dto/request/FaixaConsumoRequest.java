package br.com.moisescarlos.aguatarifa.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record FaixaConsumoRequest(

        @NotNull(message = "'inicio' é obrigatório")
        @Min(value = 0, message = "'inicio' não pode ser negativo")
        Integer inicio,

        @NotNull(message = "'fim' é obrigatório")
        @Min(value = 1, message = "'fim' deve ser maior que zero")
        Integer fim,

        @NotNull(message = "O valor unitário é obrigatório")
        @Min(value = 0, message = "O valor unitário não pode ser negativo")
        BigDecimal valorUnitario
) {}