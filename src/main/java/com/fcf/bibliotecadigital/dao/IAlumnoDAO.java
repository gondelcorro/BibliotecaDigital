package com.fcf.bibliotecadigital.dao;

import com.fcf.bibliotecadigital.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAlumnoDAO extends JpaRepository<Alumno, Integer> {
}
