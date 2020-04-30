package com.fcf.bibliotecadigital.service.impl;

import com.fcf.bibliotecadigital.dao.ILibroDAO;
import com.fcf.bibliotecadigital.model.Libro;
import com.fcf.bibliotecadigital.service.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroServiceImpl implements ILibroService {

    @Autowired
    private ILibroDAO libroDAO;

    @Override
    public Libro registrar(Libro libro) {
        return libroDAO.save(libro);
    }

    @Override
    public void eliminar(Integer id) {
        libroDAO.deleteById(id);
    }

    @Override
    public void editar(Libro libro) {
        libroDAO.save(libro);
    }

    @Override
    public List<Libro> listar() {
        return libroDAO.findAll();
    }

    @Override
    public Libro obtener(Integer id) {
        return libroDAO.getOne(id);
    }
}
