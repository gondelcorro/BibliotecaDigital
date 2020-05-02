package com.fcf.bibliotecadigital.service;

import com.fcf.bibliotecadigital.model.Prestamo;

import java.util.List;

public interface IPrestamoService {
    Prestamo registrar(Prestamo prestamo);
    void eliminar(Integer id);
    void editar(Prestamo prestamo);
    List<Prestamo> listar();
    Prestamo obtener(Integer id);
    List<Prestamo> prestamosPorAlumno(Integer idAlumno);
}
