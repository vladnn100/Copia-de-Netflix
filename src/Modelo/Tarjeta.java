package Modelo;

import java.io.Serializable;

public class Tarjeta implements Serializable {

    /*=========
      Atributos
    ===========*/
    private String nombre;
    private String apellido;
    private String nroTarjeta;
    private String fechaVencimiento;
    private Integer cvv;
    private float saldo;

    /*===========
      Constructor
    =============*/
    public Tarjeta(String nombre, String apellido, String nroTarjeta, String fechaVencimiento, Integer cvv) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nroTarjeta = nroTarjeta;
        this.fechaVencimiento = fechaVencimiento;
        this.cvv = cvv;
        this.saldo = 500;
    }

    public Tarjeta(String nombre, String apellido, String nroTarjeta, String fechaVencimiento, Integer cvv, Integer saldo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nroTarjeta = nroTarjeta;
        this.fechaVencimiento = fechaVencimiento;
        this.cvv = cvv;
        this.saldo = saldo;
    }

    /*=================
    Getters y Setters    
    ===================*/
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNroTarjeta() {
        return nroTarjeta;
    }

    public void setNroTarjeta(String nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getSaldo() {
        return this.saldo;
    }

    public boolean validar() {
        int[] num = new int[this.nroTarjeta.length()];
        for (int i = 0; i < this.nroTarjeta.length(); i++) {
            num[i] = Integer.parseInt(this.nroTarjeta.substring(i, i + 1));
        }
        for (int i = num.length - 2; i >= 0; i = i - 2) {
            int j = num[i];
            j = j * 2;
            if (j > 9) {
                j = j % 10 + 1;
            }
            num[i] = j;
        }
        int sum = 0;
        for (int i = 0; i < num.length; i++) {
            sum += num[i];
        }
        if (sum % 10 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
