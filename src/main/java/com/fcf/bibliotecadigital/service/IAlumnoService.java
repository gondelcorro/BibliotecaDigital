package com.fcf.bibliotecadigital.service;


import com.fcf.bibliotecadigital.model.Alumno;

import java.util.List;

public interface IAlumnoService {

    Alumno registrar(Alumno alumno);
    void eliminar(Integer id);
    void editar(Alumno alumno);
    List<Alumno> listar();
    Alumno obtener(Integer id);
}
