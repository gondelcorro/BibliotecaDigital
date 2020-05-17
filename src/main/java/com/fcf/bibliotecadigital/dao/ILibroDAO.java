package com.fcf.bibliotecadigital.dao;

import com.fcf.bibliotecadigital.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ILibroDAO extends JpaRepository<Libro, Integer> {

    @Query(value = "FROM Libro l WHERE l.codigo=:codigo")
    List<Libro> buscarPorCodigo(@Param("codigo") String codigo);

}
