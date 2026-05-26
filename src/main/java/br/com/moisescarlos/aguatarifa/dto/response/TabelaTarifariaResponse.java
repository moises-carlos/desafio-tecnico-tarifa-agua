package br.com.moisescarlos.aguatarifa.dto.response;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record TabelaTarifariaResponse(
        UUID id,
        String nome,
        LocalDate dataVigencia,
        List<CategoriaConsumoResponse> categorias
) {}