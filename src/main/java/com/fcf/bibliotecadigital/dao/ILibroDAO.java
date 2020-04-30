package com.fcf.bibliotecadigital.dao;

import com.fcf.bibliotecadigital.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILibroDAO extends JpaRepository<Libro, Integer> {
}
