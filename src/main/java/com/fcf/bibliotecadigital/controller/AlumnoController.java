package com.fcf.bibliotecadigital.controller;

import com.fcf.bibliotecadigital.model.Alumno;
import com.fcf.bibliotecadigital.service.IAlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {

    @Autowired
    private IAlumnoService service;

    @PostMapping(value = "/registrar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Alumno> registrar(@RequestBody Alumno alumno){
        Alumno alu = new Alumno();
        try{
            alu = service.registrar(alumno);
        }catch (Exception e) {
            return new ResponseEntity<Alumno>(alu,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Alumno>(alu, HttpStatus.OK);
    }

    @DeleteMapping(value = "/eliminar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> elimiar(@PathVariable("id") Integer id){
        int rpta = 0;
        try{
            service.eliminar(id);
            rpta = 1;
        }catch (Exception e){
            return new ResponseEntity<Integer>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
    }

    @PutMapping(value = "/editar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> editar(@RequestBody Alumno alumno){
        Integer rpta = 0;
        try{
            service.editar(alumno);
            rpta= 1;
        }catch (Exception e){
            return new ResponseEntity<Integer>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
    }

    @GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Alumno>> listar(){
        List<Alumno> listaAlumnos = new ArrayList<>();
        try{
            listaAlumnos = service.listar();
        }
        catch (Exception e){
            return new ResponseEntity<List<Alumno>>(listaAlumnos, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<Alumno>>(listaAlumnos, HttpStatus.OK);
    }

    @GetMapping(value = "/obtener/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Alumno> obtener(@PathVariable("Id") Integer id){
        Alumno alumno = new Alumno();
        try{
            alumno = service.obtener(id);
        }catch (Exception e){
            return new ResponseEntity<Alumno>(alumno, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPorDni/{dni}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> buscarPorDni(@PathVariable("dni") String dni){
        Boolean existe = false;
        try {
            existe = service.buscarPorDni(dni);
        }catch (Exception e){
            return new ResponseEntity<Boolean>(existe, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Boolean>(existe, HttpStatus.OK);
    }
}
