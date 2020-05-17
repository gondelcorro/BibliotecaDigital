package com.fcf.bibliotecadigital.service;

import com.fcf.bibliotecadigital.model.Libro;

import java.util.List;

public interface ILibroService {

    Libro registrar(Libro libro);
    void eliminar(Integer id);
    void editar(Libro libro);
    List<Libro> listar();
    Libro obtener(Integer id);
    boolean buscarPorCodigo(String codigo);
}
