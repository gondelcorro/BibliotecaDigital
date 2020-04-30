package com.fcf.bibliotecadigital.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum CarreraEnum implements Serializable {

    @JsonProperty("Ingeniería Forestal")
    ING_FORESTAL,
    @JsonProperty("Licenciatura en Ecología")
    LIC_ECOLOGIA,
    @JsonProperty("Tecnicatura en Fitosanitario")
    TEC_FITOSANITARIO,
    @JsonProperty("Ingeniería en Industrias Forestales")
    INGINDFORESTALES;
}
