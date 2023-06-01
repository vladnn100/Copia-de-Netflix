/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Media;

import Modelo.CustomException;
import java.util.Arrays;

/**
 *
 * @author Luiggi
 */
public class ArregloVideos{

    Video[] videos;

    public ArregloVideos() {
        videos = new Video[0];
    }

    public Video[] getVideos() {
        return videos;
    }

    public void setVideos(Video[] videos) {
        this.videos = videos;
    }

    public void agregarVideo(Video video) {
        int i;
        //Se captura la dimension del vector
        i = videos.length;
        Video a;
        aumentarDimension();
        a = video;
        try {
            videos[i] = a;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Indice fuera de limites: " + e);
        }

    }

    public void agregarArregloVideo(Video[] video) {
        for (Video v : video) {
            agregarVideo(v);
        }
    }

    public int dimension() {
        return videos.length;
    }

    private void aumentarDimension() {
        //int n=x.length;
        int n = videos.length;
        n = n + 1;
        Video y[] = new Video[n];
        for (int i = 0; i < videos.length; i = i + 1) {
            y[i] = videos[i];
        }
        this.videos = y;
    }

    public Video buscarVideo(String nombre) throws CustomException {
        int n;
        n = videos.length;
        Video a = new Video();
        Boolean b = false;

        for (Video p : videos) {
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
        if (n < videos.length) {
            for (int i = 0; i < videos.length; i++) {
                if (n == i) {
                    nombre = videos[i].getTitulo();
                    excepcion = false;
                }
            }
        }
        if (excepcion == true) {
            throw new CustomException("LA POSICION NO EXISTE");
        }
        return nombre;
    }

    public void mostrarVideos() {
        for (Video p : videos) {
            System.out.println("===========================");
            System.out.println("TITULO: " + p.getTitulo() + "\nGENERO: " + p.getGenero());
            System.out.println("===========================\n");
        }
    }

    public Video recomendarVideos(Visualizaciones v) {
        Video peli = new Video();

        String genero = v.generoMasVisto();

        int rand = (int) (Math.random() * 21);
        for (Video p : Multimedia.catalogoVideos.getVideos()) {
            if (p.esVisto() == false && p.getGenero().equals(genero)) {
                peli = p;
            }
        }
        return peli;
    }

    public Video getVideo(int i) {
        try {
            return videos[i];
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
    
    public void ver(int i){
        
        int vistas = videos[i].getCant_Visualizaciones() + 1;
        
        videos[i].setCant_Visualizaciones(vistas);
        
    }
    
    public void mostrarTitulosEnConsola(String descripcion, Video[] videos){
        System.out.println(descripcion);
        System.out.println("----------------------------");
        for(Video v :videos){
            System.out.println(v.getTitulo());
        }
        System.out.println("----------------------------");
    }
    
    public ArregloVideos getDiezMasVistos(){
        ArregloVideos videosMasVistos = new ArregloVideos();
        Video[] videosOrdenados = videos.clone();
        Video aux = null;
        
        mostrarTitulosEnConsola("ARREGLO SIN ORDENAR", videosOrdenados);

        Arrays.sort(videosOrdenados);
        
//        for(int i = 0; i < videosOrdenados.length; i++){
//            for(int j = i; j < videosOrdenados.length - 1; j++){
//                if(videosOrdenados[j].getCant_Visualizaciones()< videosOrdenados[j+1].getCant_Visualizaciones()){
//                    aux = videosOrdenados[j+1];
//                    videosOrdenados[j+1] = videosOrdenados[j];
//                    videosOrdenados[j] = aux;
//                }
//            }
//        }
        
        for(int i = 0; i < 10; i++){
            videosMasVistos.agregarVideo(videosOrdenados[i]);
        }
        
        mostrarTitulosEnConsola("ARREGLO ORDENADO", videosOrdenados);
    
        return videosMasVistos;
    }
    
    public int getIndice(Video video){
        int indice = -1;
        for(Video v: videos){
            indice++;
            if(v.equals(video)){
                break;
            }
        }
        return indice;
    }

}
