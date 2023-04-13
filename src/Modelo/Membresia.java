package Modelo;

import java.io.Serializable;

public enum Membresia implements Serializable {
    BASIC("BÁSICO", 29.99f, 1),
    STANDARD("ESTÁNDAR", 39.99f, 2),
    PREMIUM("PREMIUM", 49.99f, 4);

    /*=========
      Atributos
    ===========*/
    private final String descripcionMembresia;
    private final float precio;
    private final int pantallas;

    /*===========
      Constructor
    =============*/
    private Membresia(String descripcion, float precio, int pantallas) {
        this.descripcionMembresia = descripcion;
        this.precio = precio;
        this.pantallas = pantallas;
    }

    /*=================
      Getters y Setters    
    ===================*/
    public String getDescripcionMembresia() {
        return descripcionMembresia;
    }

    public float getPrecio() {
        return precio;
    }

    public float getPantallas() {
        return pantallas;
    }

}
