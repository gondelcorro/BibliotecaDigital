package com.fcf.bibliotecadigital.model;

import javax.persistence.*;

@Entity
@Table
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLibro;

    @Column(name = "titulo", nullable = true, length = 120)
    private String titulo;

    @Column(name = "autor", nullable = true, length = 120)
    private String autor;

    @Column(name = "icbn", nullable = true, length = 20)
    private String icbn;

    @Column(name = "original", nullable = true)
    private boolean original;

    @Column(name = "codigo", nullable = true, length = 20)
    private String codigo;

    @Column(name = "num_ejemplares", nullable = true, length = 5)
    private int numEjemplares;

    @Column(name = "ejemplares_disp", nullable = true, length = 5)
    private int ejemplaresDisp;

    @Column(name = "editorial", nullable = true, length = 100)
    private String editorial;

    @Column(name = "anio", nullable = true, length = 4)
    private int anio;

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIcbn() {
        return icbn;
    }

    public void setIcbn(String icbn) {
        this.icbn = icbn;
    }

    public boolean isOriginal() {
        return original;
    }

    public void setOriginal(boolean original) {
        this.original = original;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getNumEjemplares() {
        return numEjemplares;
    }

    public void setNumEjemplares(int numEjemplares) {
        this.numEjemplares = numEjemplares;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getEjemplaresDisp() {
        return ejemplaresDisp;
    }

    public void setEjemplaresDisp(int ejemplaresDisp) {
        this.ejemplaresDisp = ejemplaresDisp;
    }
}
