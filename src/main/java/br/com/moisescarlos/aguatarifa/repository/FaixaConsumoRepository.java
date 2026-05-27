package br.com.moisescarlos.aguatarifa.repository;

import br.com.moisescarlos.aguatarifa.model.FaixaConsumo;
import br.com.moisescarlos.aguatarifa.model.enums.TipoCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface FaixaConsumoRepository extends JpaRepository<FaixaConsumo, UUID> {

    List<FaixaConsumo> findByCategoriaConsumoTipoAndCategoriaConsumoTabelaTarifaId(
            TipoCategoria tipo,
            UUID tabelaTarifaId
    );
}