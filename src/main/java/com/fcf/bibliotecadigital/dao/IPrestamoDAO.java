package com.fcf.bibliotecadigital.dao;

import com.fcf.bibliotecadigital.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPrestamoDAO extends JpaRepository<Prestamo, Integer> {

    @Query(value = "SELECT * FROM prestamo WHERE id_alumno = ?1", nativeQuery = true)
    List<Prestamo> prestamosPorAlumno(Integer idAlumno);

}
