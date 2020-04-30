package com.fcf.bibliotecadigital.service.impl;

import com.fcf.bibliotecadigital.dao.IPrestamoDAO;
import com.fcf.bibliotecadigital.model.Prestamo;
import com.fcf.bibliotecadigital.service.IPrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestamoImpl implements IPrestamoService {

    @Autowired
    private IPrestamoDAO dao;

    @Override
    public Prestamo registrar(Prestamo prestamo) {
        return dao.save(prestamo);
    }

    @Override
    public void eliminar(Integer id) {
        dao.deleteById(id);
    }

    @Override
    public void editar(Prestamo prestamo) {
        dao.save(prestamo);
    }

    @Override
    public List<Prestamo> listar() {
        return dao.findAll();
    }

    @Override
    public Prestamo obtener(Integer id) {
        return dao.getOne(id);
    }
}
