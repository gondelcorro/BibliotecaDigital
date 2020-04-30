package com.fcf.bibliotecadigital.controller;

import com.fcf.bibliotecadigital.model.Libro;
import com.fcf.bibliotecadigital.service.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    private ILibroService service;

    @PostMapping(value = "/registrar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Libro> registrar(@RequestBody Libro libro){
        Libro lib = new Libro();
        try {
            lib = service.registrar(libro);
        }catch (Exception e){
            return  new ResponseEntity<Libro>(lib, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Libro>(lib, HttpStatus.OK);
    }

    @DeleteMapping(value = "/eliminar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> eliminar(@PathVariable("id") Integer id){
        int rpta = 0;
        try {
            service.eliminar(id);
            rpta = 1;
        }catch (Exception e){
            return new ResponseEntity<Integer>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
    }

    @PutMapping(value = "/editar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> editar(@RequestBody Libro libro){
        int rpta = 0;
        try {
            service.editar(libro);
            rpta = 1;
        }catch (Exception e){
            return new ResponseEntity<Integer>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
    }

    @GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Libro>> listar(){
        List<Libro> listaLibros = new ArrayList<>();
        try {
            listaLibros = service.listar();
        }catch (Exception e){
            return new ResponseEntity<List<Libro>>(listaLibros, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<Libro>>(listaLibros, HttpStatus.OK);
    }

    @GetMapping(value = "/obtener/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Libro> obtener(@PathVariable("id") Integer id){
        Libro libro = new Libro();
        try {
            libro = service.obtener(id);
        }catch (Exception e){
            return new ResponseEntity<Libro>(libro, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Libro>(libro, HttpStatus.OK);
    }
}
