package br.com.moisescarlos.aguatarifa.repository;

import br.com.moisescarlos.aguatarifa.model.TabelaTarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TabelaTarifaRepository extends JpaRepository<TabelaTarifa, UUID> {
    Optional<TabelaTarifa> findFirstByOrderByDataVigenciaDesc();
}