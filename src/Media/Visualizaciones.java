package Media;

import java.io.Serializable;
import java.util.Collections;

public class Visualizaciones implements Serializable {

    private Visualizacion v[];

    public Visualizaciones() {
        v = new Visualizacion[0];
    }

    public Visualizacion[] getV() {
        return v;
    }

    public void setV(Visualizacion[] v) {
        this.v = v;
    }

    public void agregarVisualizaciones(Visualizacion[] visualizaciones) {
        for (Visualizacion v : visualizaciones) {
            agregarVisulizacion(v);
        }
    }

    public void agregarVisulizacion(Visualizacion visulizacion) {
        int i;
        //Se captura la dimension del vector
        i = v.length;
        Visualizacion a;
        aumentarDimensionPelicula();
        a = visulizacion;
        try {
            v[i] = a;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Indice fuera de limites: " + e);
        }
    }

    public String mostrarPeliPorPosicion(int i) {
        String nom = "";

        nom = v[i].getVideo().getTitulo();

        return nom;

    }

    public int dimension() {
        return v.length;
    }

    public Video VideoPorPosi(int i) {
        return v[i].getVideo();
    }

    private void aumentarDimensionPelicula() {
        //int n=x.length;
        int n = v.length;
        n = n + 1;
        Visualizacion y[] = new Visualizacion[n];
        for (int i = 0; i < v.length; i = i + 1) {
            y[i] = v[i];
        }
        this.v = y;
    }

    public Visualizacion crearVisualizacion(Visualizacion v) {
        return v;
    }

    public void mostrarVisulizaciones() {
        int i = 0;
        for (Visualizacion a : v) {
            System.out.println("VISULIZACION Nro. " + (i + 1));
            System.out.println("PELICULA" + a.getVideo().getTitulo());
            System.out.println("TIEMPO VISTO: " + a.getVideo().getTiempoVisto());
            i++;
        }
    }

    public boolean BuscarPeliPorNombre(String nomP) {
        boolean a = false;

        for (Visualizacion vs : v) {
            if (vs.getVideo().getTitulo().equals(nomP)) {
                a = true;
            }
        }
        return a;
    }

    public int BuscarPeliPorCodigo(String nomP) {
        int a = -1;
        for (Visualizacion vs : v) {
            if (vs.getVideo().getTitulo().equals(nomP)) {
                a = vs.getVideo().getCodigo();
            }
        }
        return a;
    }

    public Video devolverPeliPorNombre(String nomP) {
        Video a = new Video();
        for (Visualizacion vs : v) {
            if (vs.getVideo().getTitulo().equals(nomP)) {
                a = vs.getVideo();
            }
        }
        return a;
    }

    public Video reproduciendo() {
        Video a = new Video();
        for (Visualizacion vs : v) {
            if (vs.getVideo().isReproduciendo()) {
                a = vs.getVideo();

            }
        }
        return a;
    }
    
    public Boolean buscarPorTitulo(String titulo){
        Boolean a=false;
        for(Visualizacion vs: v){
            if(vs.getVideo().getTitulo().equals(titulo)){
                System.out.println("TITULO: "+vs.getVideo().getTitulo());
                a=true;
            }
        }
        return a;
    }
    
    public String generoMasVisto(){

        String genero = "";
        int t = 0, tV = 0;
        int d = 0, dV = 0;
        int a = 0, aV = 0;
        System.out.println("t=" + t + "d=" + d + "a" + a);
        int n = 0;
        for (Visualizacion vs : v) {
            if (vs.getVideo().getGenero().equals("TERROR")) {
                t++;
            }
            if (vs.getVideo().getGenero().equals("DRAMA")) {
                d++;
            }
            if (vs.getVideo().getGenero().equals("ANIMACIÓN")) {
                a++;
            }
        }
        if (t == 0 && a == 0 && d == 0) {
            return "NO HAY VISULIZACIONES";
        }
        if (t == d || a == t) {
            genero = generoMasVistoPorVisualizaciones();
        } else {
            if (t > d) {
                if (t > a) {
                    genero = "TERROR";
                } else {
                    genero = "ANIMACIÓN";
                }
            } else if (d > a) {
                genero = "DRAMA";
            } else {
                genero = "ANIMACIÓN";
            }
        }
        return genero;
    }

    public String generoMasVistoPorVisualizaciones() {
        String genero = "";
        int tV = 0;
        int dV = 0;
        int aV = 0;

        for (Visualizacion vs : v) {
            if (vs.getVideo().getGenero().equals("TERROR")) {
                tV = tV + vs.getVideo().getTiempoVisto();
            }
            if (vs.getVideo().getGenero().equals("DRAMA")) {
                dV = dV + vs.getVideo().getTiempoVisto();
            }
            if (vs.getVideo().getGenero().equals("ANIMACIÓN")) {
                aV = aV + vs.getVideo().getTiempoVisto();
            }
        }

        if (tV > dV) {
            if (tV > aV) {
                genero = "TERROR";
            } else {
                genero = "ANIMACIÓN";
            }
        } else if (dV > aV) {
            genero = "DRAMA";
        } else {
            genero = "ANIMACIÓN";
        }

        return genero;
    }

    public Video contenidoMásVisto() {
        Video VideoDrama = new Video();
        int mayor = 0;
        for (Visualizacion vs : v) {
            if (vs.getVideo().getTitulo().equals(VideoDrama.getTitulo())) {
                mayor = mayor + VideoDrama.getTiempoVisto();
                System.out.println("MAYOR: " + mayor);
            }
            if (vs.getVideo().getTiempoVisto() > mayor) {
                mayor = vs.getVideo().getTiempoVisto();
                VideoDrama = vs.getVideo();
                System.out.println("MAYOR: " + mayor);
            }
        }
        return VideoDrama;
    }


    public void mostrarContenido() {
        for (Visualizacion vs : v) {
            System.out.println("====================================");
            System.out.println("PELICULA: " + vs.getVideo().getTitulo());
            System.out.println("PELICULA: " + vs.getVideo().getTiempoVisto());
            System.out.println("====================================\n");
        }
    }

    public void ordenarAlfabeticamente() {
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v.length - 1; j++) {
                if (v[j].getVideo().getTitulo().compareToIgnoreCase(v[j + 1].getVideo().getTitulo()) > 0) {
                    Visualizacion aux = v[j];
                    v[j] = v[j + 1];
                    v[j + 1] = aux;
                }
            }
        }
    }
    
    public void ordenaVectorVisualizaciones(){
        for(int i=0;i<v.length;i++){
            for(int j=i+1;j<v.length-1;j++){
                if(v[j].getVideo().getTiempoVisto()<v[j+1].getVideo().getTiempoVisto()){
                    Visualizacion aux = v[j];
                    v[j]=v[j+1];
                    v[j+1]=aux;
                }
            }
        }
    }
    
    
    public String mostrarTop(){
        String top = "";
        //String aux;
        int i=0;
        for(Visualizacion vs: v){
            //aux = vs.getVideo().getTitulo();
            top = "<html>"+"<p>"+top+(i+1)+". "+vs.getVideo().getTitulo()+"\n"+"<p>"+"<html>";
            i++;
        }
        return top;
    }
    
    public Video buscarVideoEnArray(String Titulo){
        Video videoAux = new Video();
        for(Visualizacion vs: v){
            if(vs.getVideo().getTitulo().equals(Titulo)){
                videoAux = vs.getVideo();
            }
        }
        return videoAux;
    }
    
    public int validarNroTop(){
        int i=0;
        String a="";
        for(Visualizacion vs: v){
            if(vs.getVideo().getTitulo().equals(a)){
                a = vs.getVideo().getTitulo();  
            }else{
                i++;
                System.out.println("Valor de i = "+i);
            }
        }
        return i;
    }
}

