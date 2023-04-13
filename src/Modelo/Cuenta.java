package Modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Cuenta implements Serializable {

    /*=========
      Atributos
    ===========*/
    private Usuario usuario;

    private ArregloPerfiles perfiles;
    private Suscripcion suscripcion;
    private Tarjeta tarjeta;
    private Boolean sesion;
    private int numeroPantallasActuales;

    /*===========
      Constructor
    =============*/
    public Cuenta() {
        this.sesion = Boolean.FALSE;
        perfiles = new ArregloPerfiles();
    }

    /*=================
      Getters y Setters
    ===================*/
    public ArregloPerfiles getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(ArregloPerfiles perfiles) {
        this.perfiles = perfiles;
    }

    public Perfil getPerfil(int index) {
        try {
            return perfiles.getPerfil(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public Perfil[] getPerfiles1() {
        return perfiles.getPerfiles();
    }

    public Suscripcion getSuscripcion() {
        return suscripcion;
    }

    public void setSuscripcion(Suscripcion suscripcion) {
        this.suscripcion = suscripcion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    public Boolean getSesion() {
        return sesion;
    }

    public void setSesion(Boolean sesion) {
        this.sesion = sesion;
    }

    public boolean cobrar() {
        float saldo = this.tarjeta.getSaldo();
        float precio = this.suscripcion.getMembresia().getPrecio();

        System.out.println("      COBRANDO");
        System.out.println("-------------------------");
        System.out.println("saldo : " + saldo);
        System.out.println("precio: " + precio);
        System.out.println("-------------------------");

        if (saldo >= precio) {
            saldo = saldo - precio;
            this.suscripcion.setEstadoMembresia(true);
            System.out.println("Saldo restante:" + saldo);
            this.tarjeta.setSaldo(saldo);
            suscripcion.añadirMesFacturacion();
            return true;
        } else {
            System.out.println("No hay saldo suficiente");
            this.suscripcion.setEstadoMembresia(false);
            return false;
        }
    }

    public boolean debeCobrar() {

        LocalDate hoy = LocalDate.now();

        hoy = hoy.plusMonths(1);
        
        int diaHoy = hoy.getDayOfMonth();
        int mesHoy = hoy.getMonthValue();

        int diaRegistro = suscripcion.getFechaFacturacion().getDayOfMonth();
        int mesRegistro = suscripcion.getFechaRegistro().getMonthValue();

        int diaFacturacion = suscripcion.getFechaFacturacion().getDayOfMonth();
        int mesFacturacion = suscripcion.getFechaFacturacion().getMonthValue();

        if ((diaHoy >= diaFacturacion && mesHoy == mesFacturacion)) {
            return true;
        }

        return false;
    }

}
