package com.fcf.bibliotecadigital.dao;

import com.fcf.bibliotecadigital.model.Libro;
import com.fcf.bibliotecadigital.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ILibroDAO extends JpaRepository<Libro, Integer> {

   /* @Transactional
    @Modifying
    @Query(value = "UPDATE libro SET ejemplares_disp = ?1 WHERE id_prestamo = ?2", nativeQuery = true)
    void actualizarEjemplares(Integer  ejemplaresDisp, Integer idLibro);*/

}
