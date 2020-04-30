package com.fcf.bibliotecadigital.controller;

import com.fcf.bibliotecadigital.model.Prestamo;
import com.fcf.bibliotecadigital.service.IPrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/prestamo")
public class PrestamoController {

    @Autowired
    private IPrestamoService service;

    @PostMapping(value = "/registrar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Prestamo> registrar(@RequestBody Prestamo prestamo){
        Prestamo pres = new Prestamo();
        try{
            pres = service.registrar(prestamo);
        }catch (Exception ex){
            return new ResponseEntity<Prestamo>(pres, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Prestamo>(pres, HttpStatus.OK);
    }


    @DeleteMapping(value = "/elimar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> eliminar(@PathVariable("id") Integer id){
        Integer rpta = 0;
        try {
            service.eliminar(id);
            rpta = 1;
        }catch (Exception ex){
            return new ResponseEntity<Integer>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
    }

    @PutMapping(value = "/editar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> editar(@RequestBody Prestamo prestamo){
        Integer rpta = 0;
        try{
            service.editar(prestamo);
            rpta = 1;
        }catch (Exception ex){
            return new ResponseEntity<Integer>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
    }

    @GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Prestamo>> listar(){
        List<Prestamo> listaPrestamo = new ArrayList<>();
        try{
            listaPrestamo = service.listar();
        }catch (Exception ex){
            return new ResponseEntity<List<Prestamo>>(listaPrestamo, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<Prestamo>>(listaPrestamo, HttpStatus.OK);
    }

    @GetMapping(value = "/obtener/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Prestamo> obtener(@PathVariable("id") Integer id){
        Prestamo pres = new Prestamo();
        try{
            pres = service.obtener(id);
        }catch (Exception ex){
            return new ResponseEntity<Prestamo>(pres, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Prestamo>(pres, HttpStatus.OK);
    }
}
