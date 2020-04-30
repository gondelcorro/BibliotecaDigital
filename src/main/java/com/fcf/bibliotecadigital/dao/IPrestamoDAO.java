package com.fcf.bibliotecadigital.dao;

import com.fcf.bibliotecadigital.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPrestamoDAO extends JpaRepository<Prestamo, Integer> {
}
