package Modelo;

import java.io.Serializable;
import javax.swing.JOptionPane;

public class Usuario implements Serializable {

    /*=========
      Atributos
    ===========*/
    private String correo;
    private String contraseña;
    private String telefono;
    private boolean estadoDeConexion;
    private int dispositivosConectados;
    private int pantallasActivas;

    /*===========
      Constructor
    =============*/
    public Usuario(String Correo, String contraseña) {
        this.correo = Correo;
        this.contraseña = contraseña;
        this.estadoDeConexion = Boolean.FALSE;
        this.dispositivosConectados = 0;
    }

    public Usuario(String Correo, String contraseña, String telefono) {
        this.correo = Correo;
        this.contraseña = contraseña;
        this.telefono = telefono;
        this.estadoDeConexion = Boolean.FALSE;
    }

    public Usuario() {
    }

    /*=================
      Getters y Setters
    ===================*/
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getContraseñaOculta() {
        String contraseñaAux = "";
        for (int i = 0; i < contraseña.length(); i++) {
            contraseñaAux = contraseñaAux + "*";
        }
        return contraseñaAux;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean validarCorreoContraseña(String correo, String contraseña) {
        if (this.correo.equals(correo) && this.contraseña.equals(contraseña)) {
            return true;
        }
        return false;
    }

    public boolean validarCorreo(String correo) {
        if (this.correo.equals(correo)) {
            return true;
        }
        return false;
    }

    public int getDispositivosConectados() {
        return dispositivosConectados;
    }

    public void aumentarDispositivo() {
        this.dispositivosConectados++;
    }

    public void disminuirDispositivo() {
        if (this.dispositivosConectados > 0) {
            this.dispositivosConectados--;
            System.out.println("Dispositivo desconectado");
            System.out.println("Antes: " + this.dispositivosConectados + 1);
            System.out.println("Ahora: " + this.dispositivosConectados);
        } else {
            System.out.println("Error al cerrar sesión en dispositivo");
        }
    }

    public int getPantallasActivas() {
        return pantallasActivas;
    }

    public void setPantallasActivas(int pantallasActivas) {
        this.pantallasActivas = pantallasActivas;
    }

    public void aumentarPantalla() {
        this.pantallasActivas++;
    }

    public void disminuirPantalla() {
        if (this.pantallasActivas > 0) {
            this.pantallasActivas--;
            System.out.println("Pantalla desconectada");
            System.out.println("Antes: " + this.pantallasActivas + 1);
            System.out.println("Ahora: " + this.pantallasActivas);
        } else {
            System.out.println("Error al cerrar pantalla");
        }
    }

}
