package Modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Suscripcion implements Serializable {

    /*=========
      Atributos
    ===========*/
    private LocalDate fechaRegistro;
    private LocalDate fechaFacturacion;
    private Boolean estadoMembresia;
    private Membresia membresia;

    /*===========
      Constructor
    =============*/
    public Suscripcion(Membresia membresia) {
        this.membresia = membresia;
        this.estadoMembresia = Boolean.TRUE;
        this.fechaRegistro = LocalDate.now();
        this.fechaFacturacion = this.fechaRegistro.plusMonths(1);
    }

    /*=================
      Getters y Setters    
    ===================*/
    public Boolean getEstadoMembresia() {
        return estadoMembresia;
    }

    public void setEstadoMembresia(Boolean estadoMembresia) {
        this.estadoMembresia = estadoMembresia;
    }

    public Membresia getMembresia() {
        return membresia;
    }

    public void setMembresia(Membresia membresia) {
        this.membresia = membresia;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void cambiarFecha(int nDia) {
        int actualDia = this.fechaRegistro.getDayOfMonth();
        int sumDia = nDia - actualDia;
        this.fechaRegistro = this.fechaRegistro.plusDays(sumDia);
        actualizarFechaFacturacion();
    }

    public LocalDate getFechaFacturacion() {
        return fechaFacturacion;
    }

    public void actualizarFechaFacturacion() {
        this.fechaFacturacion = fechaRegistro.plusMonths(1);
    }

    public void añadirMesFacturacion() {
        this.fechaFacturacion = fechaFacturacion.plusMonths(1);
    }

}
