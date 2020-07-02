package com.fcf.bibliotecadigital.dao;

import com.fcf.bibliotecadigital.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAlumnoDAO extends JpaRepository<Alumno, Integer> {

    @Query("FROM Alumno a WHERE a.dni=:dni")
    List<Alumno> buscarPorDni(@Param("dni") String dni);
}
