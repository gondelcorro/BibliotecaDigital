package com.fcf.bibliotecadigital.dao;

import com.fcf.bibliotecadigital.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioDAO extends JpaRepository<Usuario, Integer> {

    @Query("FROM Usuario u WHERE u.username=:username")
    Usuario buscarPorNombre(String username);

}
