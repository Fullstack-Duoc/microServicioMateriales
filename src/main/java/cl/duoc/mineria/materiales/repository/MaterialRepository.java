package cl.duoc.mineria.materiales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.mineria.materiales.model.Materiales;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Materiales, Long>{
    Optional<Materiales> findByNombreIgnoreCase(String nombre);
    List<Materiales> findByClasificacionIgnoreCase(String clasificacion);
}
