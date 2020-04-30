package com.fcf.bibliotecadigital.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum EstadoPrestamoEnum implements Serializable {

    @JsonProperty("Prestado")
    PRESTADO,
    @JsonProperty("Devuelto")
    DEVUELTO,
    @JsonProperty("Vencido")
    VENCIDO;
}
