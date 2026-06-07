package com.universidad.gestioncursos.repository;

import com.universidad.gestioncursos.model.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Long> {
    Optional<Carrera> findByCodigo(String codigo);
    List<Carrera> findByNombreContainingIgnoreCase(String nombre);
}