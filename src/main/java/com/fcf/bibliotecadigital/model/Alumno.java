package com.fcf.bibliotecadigital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAlumno;

    @Column(name = "nombres", nullable = true, length = 80)
    private String nombres;

    @Column(name = "apellidos", nullable = true, length = 80)
    private String apellidos;

    @Column(name = "legajo", nullable = true, length = 10)
    private String legajo;

    @Column(name = "dni", nullable = true, length = 8)
    private int dni;

    @Column(name = "correo", nullable = true, length = 40)
    private String correo;

    @Column(name = "carrera", nullable = true, length = 120)
    @Enumerated(EnumType.STRING)
    private CarreraEnum carrera;

    @Column(name = "telefono", nullable = true, length = 60)
    private String telefono;

    //Para ver los prestamos de un alumno (mappedBy=NOMBRE DE ESTA TABLA EN BD)
    //si o si en una de las 2 relacions tiene q ir el jsonIgnore
    @JsonIgnore
    @OneToMany(mappedBy="alumno" , cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE} , fetch=FetchType.LAZY, orphanRemoval=true)
    private List<Prestamo> prestamos;

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public CarreraEnum getCarrera() {
        return carrera;
    }

    public void setCarrera(CarreraEnum carrera) {
        this.carrera = carrera;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
}
