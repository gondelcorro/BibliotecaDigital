package com.fcf.bibliotecadigital.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@NamedQuery(name = Prestamo.BUSCAR_PRESTAMOS_A_VENCER, query = Prestamo.BUSCAR_PRESTAMOS_A_VENCER_QUERY)
public class Prestamo {

    public static final String BUSCAR_PRESTAMOS_A_VENCER = "PrestamoNameQuery.BUSCAR_PRESTAMOS_A_VENCER";
    public static final String BUSCAR_PRESTAMOS_A_VENCER_QUERY = "SELECT p FROM Prestamo p WHERE p.fechaMaxDevolucion=:fechaActual";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPrestamo;

    //Muchos prestamos pertenecen a un mismo alumno
    //para evitar el mapeo ciclico, se ignora este atributo en el json q viaja en los distintos services
    //xq prestamo tiene una ref a alumno y alumno tb a prestamo, va en la entidad de la q quiera ignorar el json
    //En este caso no xq cuando guarde un prestamo necesito el alumno. Entoncs lo pongo en el alumno.
    //En cambio si tengo una consulta medica, cuando guardo su consulta si le paso el detalle de la consulta.
    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_alumno", nullable=false)
    private Alumno alumno;

    //Un prestamo tiene un solo libro (prestamos por libro)
    @OneToOne
    @JoinColumn(name="id_libro", nullable=false)
    private Libro libro;

    //Anotacion de jackson para q las fechas tengan el formato año mes dia (agregar dependencia en el pom)
    @JsonSerialize(using= ToStringSerializer.class)
    private LocalDate fechaPrestamo;

    @JsonSerialize(using= ToStringSerializer.class)
    private LocalDate fechaMaxDevolucion;

    @JsonSerialize(using= ToStringSerializer.class)
    private LocalDate fechaDevolucion;

    @Column(name = "estadoPrestamo", nullable = true, length = 120)
    @Enumerated(EnumType.STRING)
    private EstadoPrestamoEnum estadoPrestamo;

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaMaxDevolucion() {
        return fechaMaxDevolucion;
    }

    public void setFechaMaxDevolucion(LocalDate fechaMaxDevolucion) {
        this.fechaMaxDevolucion = fechaMaxDevolucion;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public EstadoPrestamoEnum getEstadoPrestamo() {
        return estadoPrestamo;
    }

    public void setEstadoPrestamo(EstadoPrestamoEnum estadoPrestamo) {
        this.estadoPrestamo = estadoPrestamo;
    }
}
