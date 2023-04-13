package Media;

import Modelo.CustomException;
import java.io.Serializable;

public class ArregloPeliculas implements Serializable {

    Pelicula peliculas[];

    public ArregloPeliculas() {
        peliculas = new Pelicula[0];
    }

    public Pelicula[] getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(Pelicula[] peliculas) {
        this.peliculas = peliculas;
    }

    public void agregarArregloPelicula(Pelicula[] pelicula) {
        for (Pelicula p : pelicula) {
            agregarPelicula(p);
        }
    }

    public void agregarPelicula(Pelicula pelicula) {
        int i;
        //Se captura la dimension del vector
        i = peliculas.length;
        Pelicula a;
        aumentarDimension();
        a = pelicula;
        try {
            peliculas[i] = a;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Indice fuera de limites: " + e);
        }

    }

    public int dimension() {
        return peliculas.length;
    }

    private void aumentarDimension() {
        //int n=x.length;
        int n = peliculas.length;
        n = n + 1;
        Pelicula y[] = new Pelicula[n];
        for (int i = 0; i < peliculas.length; i = i + 1) {
            y[i] = peliculas[i];
        }
        this.peliculas = y;
    }

    public Pelicula buscarPelicula(String nombre) throws CustomException {
        int n;
        n = peliculas.length;
        Pelicula a = new Pelicula();
        Boolean b = false;

        for (Pelicula p : peliculas) {
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
        if (n < peliculas.length) {
            for (int i = 0; i < peliculas.length; i++) {
                if (n == i) {
                    nombre = peliculas[i].getTitulo();
                    excepcion = false;
                }
            }
        }
        if (excepcion == true) {
            throw new CustomException("LA POSICION NO EXISTE");
        }
        return nombre;
    }

    public void mostrarPeliculas() {
        for (Pelicula p : peliculas) {
            System.out.println("===========================");
            System.out.println("TITULO: " + p.getTitulo() + "\nGENERO: " + p.getGenero());
            System.out.println("===========================\n");
        }
    }

    public Pelicula recomendarPelicula(Visualizaciones v) {
        Pelicula peli = new Pelicula();

        String genero = v.generoMasVisto();
        int rand = (int) (Math.random() * 21);
        for (Pelicula p : Multimedia.catalogoPeliculas.getPeliculas()) {
            if (p.esVisto() == false && p.getGenero().equals(genero)) {
                peli = p;
            }
        }
        return peli;
    }

    public Pelicula getPelicula(int i) {
        try {
            return peliculas[i];
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
    
    public boolean buscarReproducido(String Titulo){
        boolean a = false;
        for(Pelicula p: peliculas){
            if(p.isReproduciendo()==true&&p.getTitulo().equals(Titulo)){
                a = true;
            }
        }
        return a;
    }
    
    public void ver(int i){
        
        int vistas = peliculas[i].getCant_Visualizaciones() + 1;
        
        peliculas[i].setCant_Visualizaciones(vistas);
    }
    
}
