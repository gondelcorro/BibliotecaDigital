package com.fcf.bibliotecadigital.service.impl;

import com.fcf.bibliotecadigital.dao.ILibroDAO;
import com.fcf.bibliotecadigital.dao.IPrestamoDAO;
import com.fcf.bibliotecadigital.model.Libro;
import com.fcf.bibliotecadigital.model.Prestamo;
import com.fcf.bibliotecadigital.service.IPrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrestamoImpl implements IPrestamoService {

    @Autowired
    private IPrestamoDAO dao;
    @Autowired
    private ILibroDAO libroDAO;

    @Transactional
    @Override
    public Prestamo registrar(Prestamo prestamo) {
        Prestamo pres = new Prestamo();
        try {
            pres = dao.save(prestamo);
            Libro lib = libroDAO.getOne(pres.getLibro().getIdLibro());
            lib.setEjemplaresDisp(lib.getNumEjemplares() - 1);
            libroDAO.save(lib);
        }catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }
        return pres;
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

    @Override
    public List<Prestamo> prestamosPorAlumno(Integer idAlumno) {
        return dao.prestamosPorAlumno(idAlumno);
    }
}
