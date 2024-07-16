package com.challengeliteratura.challengeliteratura.repository;

import com.challengeliteratura.challengeliteratura.model.LibroDatos;

import com.challengeliteratura.challengeliteratura.entity.AutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<AutorEntity, Long> {
    Optional<AutorEntity> findByTituloContainsIgnoreCase(String nombreAutor);
    List<AutorEntity> findForYear(int anio);
}

    //List<Serie> findByTotalTemporadasLessThanEqualAndEvaluacionGreaterThanEqual(int totalTemporadas, Double evaluacion);

    //@Query("SELECT s FROM Serie s WHERE s.totalTemporadas <= :totalTemporadas AND s.evaluacion >= :evaluacion")
    //List<Serie> seriesPorTemparadaYEvaluacion(int totalTemporadas, Double evaluacion);

