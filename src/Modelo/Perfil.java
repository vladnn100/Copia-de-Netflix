package Modelo;

import Media.Visualizacion;
import Media.Visualizaciones;
import java.io.Serializable;

public class Perfil implements Serializable {

    /*=========
      Atributos
    ===========*/
    private String nombre;
    private boolean sesion;
    private boolean restriccionDeEdad;
    private Idiomas idioma;
    private Usuario usuario;
    private Visualizaciones visualizaciones;
    private boolean activo = false;

    /*===========
      Constructor    
    =============*/
    public Perfil(String nombre, Usuario usuario) {
        Visualizaciones v = new Visualizaciones();
        this.usuario = usuario;
        this.nombre = nombre;
        this.sesion = Boolean.FALSE;
        this.restriccionDeEdad = Boolean.FALSE;
        this.idioma = Idiomas.SPANISH;
        visualizaciones = v;
    }

    public Perfil(String nombre) {
        Visualizaciones v = new Visualizaciones();
        this.nombre = nombre;
        this.sesion = Boolean.FALSE;
        this.restriccionDeEdad = Boolean.FALSE;
        this.idioma = Idiomas.SPANISH;
        visualizaciones = v;
    }

    public Perfil() {
        Visualizaciones v = new Visualizaciones();
        visualizaciones = v;
    }

    /*=================
    Getters y Setters    
    ===================*/
    public void setVisualizaciones(Visualizaciones visualizaciones) {
        this.visualizaciones = visualizaciones;
    }

    public Visualizaciones getVisualizaciones() {
        return visualizaciones;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isSesion() {
        return sesion;
    }

    public void setSesion(boolean sesion) {
        this.sesion = sesion;
    }

    public boolean isRestriccionDeEdad() {
        return restriccionDeEdad;
    }

    public void setRestriccionDeEdad(boolean restriccionDeEdad) {
        this.restriccionDeEdad = restriccionDeEdad;
    }

    public Idiomas getIdioma() {
        return idioma;
    }

    public void setIdioma(Idiomas idioma) {
        this.idioma = idioma;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /*=======
      Métodos
    =========*/
    @Override
    public String toString() {
        return nombre;
    }

}
