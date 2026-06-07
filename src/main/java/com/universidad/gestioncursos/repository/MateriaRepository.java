package com.universidad.gestioncursos.repository;

import com.universidad.gestioncursos.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {
    List<Materia> findByCarreraId(Long carreraId);
}