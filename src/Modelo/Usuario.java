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
    public Usuario(String correo, String contraseña) {
        this.correo = correo;
        this.contraseña = contraseña;
        this.estadoDeConexion = Boolean.FALSE;
        this.dispositivosConectados = 0;
    }

    public Usuario(String correo, String contraseña, String telefono) {
        this(correo, contraseña);
        this.telefono = telefono;
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
        return this.correo.equals(correo) && this.contraseña.equals(contraseña);
    }

    public boolean validarCorreo(String correo) {
        return correo.length() > 5 && correo.contains("@");
    }
    
    public boolean validarContraseña(String contraseña) {
        return contraseña.length() >= 8;
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

    @Override
    public String toString() {
        return "Usuario{" + "correo=" + correo + ", contrase\u00f1a=" + contraseña + '}';
    }
}
