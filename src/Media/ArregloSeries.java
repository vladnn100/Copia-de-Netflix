/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Media;

import Modelo.CustomException;

/**
 *
 * @author Luiggi
 */
public class ArregloSeries {

    Serie[] serie;

    public ArregloSeries() {
        serie = new Serie[0];
    }

    public Serie[] getSeries() {
        return serie;
    }

    public void setSeries(Serie[] series) {
        this.serie = series;
    }

    public void agregarSerie(Serie pelicula) {
        int i;
        //Se captura la dimension del vector
        i = serie.length;
        Serie a;
        aumentarDimension();
        a = pelicula;
        try {
            serie[i] = a;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Indice fuera de limites: " + e);
        }

    }

    public void agregarArregloSerie(Serie[] series) {
        for (Serie s : series) {
            agregarSerie(s);
        }
    }

    public int dimension() {
        return serie.length;
    }

    private void aumentarDimension() {
        //int n=x.length;
        int n = serie.length;
        n = n + 1;
        Serie y[] = new Serie[n];
        for (int i = 0; i < serie.length; i = i + 1) {
            y[i] = serie[i];
        }
        this.serie = y;
    }

    public Video buscarSerie(String nombre) throws CustomException {
        int n;
        n = serie.length;
        Serie a = new Serie();
        Boolean b = false;

        for (Serie p : serie) {
            if (p.getTitulo().equals(nombre)) {
                b = true;
                a = p;
                break;
            }
        }
        if (b == false) {
            throw new CustomException("NO SE ENCONTRÓ LA PELICULA");
        }
        return a;
    }

    public String devolverNombrePorPosicion(int n) throws CustomException {
        String nombre = "";
        boolean excepcion = true;
        if (n < serie.length) {
            for (int i = 0; i < serie.length; i++) {
                if (n == i) {
                    nombre = serie[i].getTitulo();
                    excepcion = false;
                }
            }
        }
        if (excepcion == true) {
            throw new CustomException("LA POSICION NO EXISTE");
        }
        return nombre;
    }

    public void mostrarSeries() {
        for (Video p : serie) {
            System.out.println("===========================");
            System.out.println("TITULO: " + p.getTitulo() + "\nGENERO: " + p.getGenero());
            System.out.println("===========================\n");
        }
    }

    public Serie recomendarSeries(Visualizaciones v) {
        Serie peli = new Serie();

        String genero = v.generoMasVisto();

        int rand = (int) (Math.random() * 21);
        for (Serie p : Multimedia.catalogoSeries.getSeries()) {
            if (p.esVisto() == false && p.getGenero().equals(genero)) {
                peli = p;
            }
        }
        return peli;
    }

    public Serie getSerie(int i) {
        try {
            return serie[i];
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
    
    public void ver(int i){
        
        int vistas = serie[i].getCant_Visualizaciones() + 1;
        
        serie[i].setCant_Visualizaciones(vistas);
    }

}
