package com.fcf.bibliotecadigital.service.impl;

import com.fcf.bibliotecadigital.dao.IAlumnoDAO;
import com.fcf.bibliotecadigital.model.Alumno;
import com.fcf.bibliotecadigital.service.IAlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlumnoServiceImpl implements IAlumnoService {

    @Autowired
    private IAlumnoDAO alumnoDAO;

    @Override
    public Alumno registrar(Alumno alumno) {
        return alumnoDAO.save(alumno);
    }

    @Override
    public void eliminar(Integer id) {
        alumnoDAO.deleteById(id);
    }

    @Override
    public void editar(Alumno alumno) { alumnoDAO.save(alumno); }

    @Override
    public List<Alumno> listar() {
        return alumnoDAO.findAll();
    }

    @Override
    public Alumno obtener(Integer id) {
        return alumnoDAO.getOne(id);
    }

    @Override
    public boolean buscarPorDni(String dni) {
        return !alumnoDAO.buscarPorDni(dni).isEmpty() && (alumnoDAO.buscarPorDni(dni) != null);
    }
}
