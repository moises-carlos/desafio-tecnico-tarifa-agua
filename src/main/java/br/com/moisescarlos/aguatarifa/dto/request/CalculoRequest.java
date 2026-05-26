package br.com.moisescarlos.aguatarifa.dto.request;

import br.com.moisescarlos.aguatarifa.model.enums.TipoCategoria;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CalculoRequest(

        @NotNull(message = "A categoria é obrigatória")
        TipoCategoria categoria,

        @NotNull(message = "O consumo é obrigatório")
        @Min(value = 0, message = "O consumo não pode ser negativo")
        Integer consumo
) {}