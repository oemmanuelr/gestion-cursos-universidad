package com.universidad.gestioncursos.repository;

import com.universidad.gestioncursos.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByCodigo(String codigo);
    List<Curso> findByNombreContainingIgnoreCase(String nombre);
}