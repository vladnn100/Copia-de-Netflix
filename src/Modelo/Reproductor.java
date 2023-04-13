package Modelo;

import java.awt.BorderLayout;
import java.io.File;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Julian
 */
public class Reproductor {

    private JFXPanel jfxpanel;
    private JPanel jpanel;
    private MediaPlayer mediaPlayer;
    private Media media;
    private MediaView mediaView;
    private Scene escena;
    private File file;
    private String ruta;

    public Reproductor() {
        this.jfxpanel = new JFXPanel();

    }

    public void setJpanel(JPanel jpanel) {
        this.jpanel = jpanel;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public void reproducir() {
        try {
            getMediaPlayer().play();
        } catch (MediaException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void pausar() {
        try {
            getMediaPlayer().pause();
        } catch (MediaException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void detener() {
        try {
            getMediaPlayer().stop();
        } catch (MediaException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void controlVolumen(double nivel) {
        try {
            getMediaPlayer().setVolume(nivel);
        } catch (MediaException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void mostrarVideo() {
        try {
            int x = this.jpanel.getHeight(); // ALTO DEL VIDEO
            int y = this.jpanel.getWidth(); // ANCHO DEL VIDEO

            this.file = new File(this.ruta);

            this.media = new Media(this.file.toURI().toString());

            this.mediaPlayer = new MediaPlayer(this.media);

            this.mediaView = new MediaView(this.mediaPlayer);

            this.mediaView.setFitHeight(x);

            this.mediaView.setFitWidth(y);

            this.escena = new Scene(new Group(this.mediaView));

            this.jfxpanel.setSize(this.jpanel.getSize());

            this.jfxpanel.setScene(this.escena);

            this.getMediaPlayer().setCycleCount(MediaPlayer.INDEFINITE);

            this.jpanel.add(this.jfxpanel, BorderLayout.CENTER);

        } catch (MediaException e) {
            JOptionPane.showMessageDialog(jpanel, "Solo reproduzco videos MP4 :(");
        }

    }

}
