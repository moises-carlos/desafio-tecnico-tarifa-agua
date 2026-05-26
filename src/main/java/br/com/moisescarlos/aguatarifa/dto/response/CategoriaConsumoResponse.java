package br.com.moisescarlos.aguatarifa.dto.response;

import br.com.moisescarlos.aguatarifa.model.enums.TipoCategoria;
import java.util.List;
import java.util.UUID;

public record CategoriaConsumoResponse(
        UUID id,
        TipoCategoria tipo,
        List<FaixaConsumoResponse> faixas
) {}