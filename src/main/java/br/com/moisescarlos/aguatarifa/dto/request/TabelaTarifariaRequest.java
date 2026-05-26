package br.com.moisescarlos.aguatarifa.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public record TabelaTarifariaRequest(

        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotNull(message = "A data de vigência é obrigatória")
        LocalDate dataVigencia,

        @NotEmpty(message = "A tabela deve ter pelo menos uma categoria")
        List<CategoriaConsumoRequest> categorias
) {}