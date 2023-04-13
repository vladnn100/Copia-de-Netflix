/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Media;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author User
 */
public class Video implements Serializable,Comparable<Video> {

    private String Titulo;
    private String Director;
    private String Genero;
    private int Anio;
    private int Edad_Apta;
    private String Sinopsis;
    private String elenco[];
    private int Puntuacion;
    private int Cant_Visualizaciones;
    private boolean Visto;
    private int duracion;
    private int TiempoVisto;
    private int codigo;
    private String dirImg;
    private String dirVid;
    private boolean reproduciendo;

    public Video() {
        this.Titulo = "Nuevo video";
        this.Director = "Sin director";
        this.Genero = "Sin genero";
        this.Anio = 0;
        this.Edad_Apta = 0;
        this.Sinopsis = "Sin sinopsis";
        //this.elenco = "Sin elenco";
        this.Puntuacion = 0;
        this.Cant_Visualizaciones = 0;
        this.Visto = false;
        this.duracion = 0;
        this.TiempoVisto = 0;
        this.reproduciendo = false;
    }

    public Video(String Titulo, String Director, String Genero, int Anio, int Edad_Apta, String Sinopsis, String elenco[], boolean Visto, int duracion, int TiempoVisto, int codigo, String dirImg, String dirVid) {
        this.Titulo = Titulo;
        this.Director = Director;
        this.Genero = Genero;
        this.Anio = Anio;
        this.Edad_Apta = Edad_Apta;
        this.Sinopsis = Sinopsis;
        this.elenco = elenco;
        this.Visto = Visto;
        this.duracion = duracion;
        this.TiempoVisto = TiempoVisto;
        this.codigo = codigo;
        this.dirImg = dirImg;
        this.dirVid = dirVid;
        this.reproduciendo = false;
    }

    public Video(String titulo, String director) {
        this.Titulo = titulo;
        this.Director = director;
        this.Genero = "Sin genero";
        this.Anio = 0;
        this.Edad_Apta = 0;
        this.Sinopsis = "Sin sinopsis";
        //this.elenco = "Sin elenco";
        this.Puntuacion = 0;
        this.Cant_Visualizaciones = 0;
        this.Visto = false;
        this.duracion = 0;
        this.TiempoVisto = 0;
        this.reproduciendo = false;
    }

    public Video(String titulo, String director, String genero, int duracion) {
        this.Titulo = titulo;
        this.Director = director;
        this.Genero = genero;
        this.duracion = duracion;
        this.reproduciendo = false;
    }

    public String getDirImg() {
        return dirImg;
    }

    public void setDirImg(String dirImg) {
        this.dirImg = dirImg;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String Director) {
        this.Director = Director;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public int getAnio() {
        return Anio;
    }

    public void setAnio(int Anio) {
        this.Anio = Anio;
    }

    public int getEdad_Apta() {
        return Edad_Apta;
    }

    public void setEdad_Apta(int Edad_Apta) {
        this.Edad_Apta = Edad_Apta;
    }

    public String getSinopsis() {
        return Sinopsis;
    }

    public void setSinopsis(String Sinopsis) {
        this.Sinopsis = Sinopsis;
    }

    public String[] getElenco() {
        return elenco;
    }

    public void setElenco(String[] elenco) {
        this.elenco = elenco;
    }

    public int getPuntuacion() {
        return Puntuacion;
    }

    public void setPuntuacion(int Puntuacion) {
        this.Puntuacion = Puntuacion;
    }

    public int getCant_Visualizaciones() {
        return Cant_Visualizaciones;
    }

    public void setCant_Visualizaciones(int Cant_Visualizaciones) {
        this.Cant_Visualizaciones = Cant_Visualizaciones;
    }

    public boolean isVisto() {
        return Visto;
    }

    public void setVisto(boolean Visto) {
        this.Visto = Visto;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getTiempoVisto() {
        return TiempoVisto;
    }

    public void setTiempoVisto(int TiempoVisto) {
        this.TiempoVisto = TiempoVisto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDirVid() {
        return dirVid;
    }

    public void setDirVid(String dirVid) {
        this.dirVid = dirVid;
    }

    public boolean isReproduciendo() {
        return reproduciendo;
    }

    public void setReproduciendo(boolean reproduciendo) {
        this.reproduciendo = reproduciendo;
    }

    /*
    @Override
    public String toString() {
        return "Video{" +
                "Titulo='" + Titulo + '\'' +
                ", Director='" + Director + '\'' +
                ", Genero='" + Genero + '\'' +
                ", duracion=" + duracion +
                '}';
    }
     */
    @Override
    public String toString() {
        return "Video{" + "Titulo=" + Titulo + ", Director=" + Director + ", "
                + "Genero=" + Genero + ", Anio=" + Anio + ", Edad_Apta=" + Edad_Apta
                + ", Sinopsis=" + Sinopsis + ", elenco=" + elenco + ", Puntuacion=" + Puntuacion
                + ", Cant_Visualizaciones=" + Cant_Visualizaciones + ", Visto=" + Visto + ", duracion=" + duracion
                + ", TiempoVisto=" + TiempoVisto + '}';
    }

    public void marcarVisto(int tiempoVisualizado) {
        this.Visto = true;
        TiempoVisto = tiempoVisualizado;
    }

    public boolean esVisto() {
        return this.Visto;
    }

    public boolean dejarDeVer() {
        boolean result = false;

        if (esVisto()) {
            result = true;
            this.Visto = Boolean.FALSE;
        }
        return result;
    }

    public int tiempoVisto() {
        return this.TiempoVisto;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Video other = (Video) obj;
        if (!Objects.equals(this.Titulo, other.Titulo)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Video t) {
        if (Cant_Visualizaciones < t.getCant_Visualizaciones()) {
                return 1;
            }
            if (Cant_Visualizaciones > t.getCant_Visualizaciones()) {
                return -1;
            }
            return 0;
    }
    
    

}
