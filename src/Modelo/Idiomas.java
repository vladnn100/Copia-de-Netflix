package Modelo;

import java.io.Serializable;

public enum Idiomas implements Serializable {
    SPANISH("ES", "Español"),
    ENGLISH("EN", "English"),
    FRENCH("FR", "Français"),
    DEUTSCH("DE", "Deutsch"),
    ITALIANO("IT", "Italiano"),
    PORTUGUESE("PR", "Português");

    private final String siglas;
    private final String descripcion;

    private Idiomas(String siglas, String descripcion) {
        this.siglas = siglas;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return siglas + " - " + descripcion;
    }

    public String getSiglas() {
        return siglas;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
