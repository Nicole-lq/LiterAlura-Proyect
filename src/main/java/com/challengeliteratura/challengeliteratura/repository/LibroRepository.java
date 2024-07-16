package com.challengeliteratura.challengeliteratura.repository;

import com.challengeliteratura.challengeliteratura.model.LibroDatos;

import com.challengeliteratura.challengeliteratura.entity.LibroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<LibroEntity, Long> {

    List<LibroEntity> findForLanguaje(String seleccion);

    //@Query(SELECT )
}



