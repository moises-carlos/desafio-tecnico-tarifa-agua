package br.com.moisescarlos.aguatarifa.dto.request;

import br.com.moisescarlos.aguatarifa.model.enums.TipoCategoria;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record CategoriaConsumoRequest(

        @NotNull(message = "O tipo de categoria é obrigatório")
        TipoCategoria tipo,

        @NotEmpty(message = "A categoria deve ter pelo menos uma faixa")
        List<FaixaConsumoRequest> faixas
) {}