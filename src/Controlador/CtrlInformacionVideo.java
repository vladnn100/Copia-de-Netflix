/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Media.Video;
import Modelo.Archivo;
import Modelo.ArregloDeCuentas;
import Modelo.CustomException;
import Modelo.Reproductor;
import Vista.FrmCatalogo;
import Vista.FrmInformacionVideo;
import Vista.FrmReproductor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 *
 * @author Luiggi
 */
public class CtrlInformacionVideo {

    private FrmInformacionVideo vista;
    private ArregloDeCuentas modelo;
    private Video video;
    private String correo;

    public CtrlInformacionVideo(FrmInformacionVideo vista, ArregloDeCuentas modelo) {
        this.vista = vista;
        this.modelo = modelo;

        this.vista.btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vista.dispose();
                FrmCatalogo fCatalogo = new FrmCatalogo();
                CtrlCatalogo ctrlCatalogo = new CtrlCatalogo(modelo, fCatalogo);
                ctrlCatalogo.setCorreo(correo);
                ctrlCatalogo.init();
            }
        });

        this.vista.btnImagen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Reproductor rep = new Reproductor();
                FrmReproductor fRep = new FrmReproductor();
                CtrlReproductor cRep;

                int n;
                try {
                    n = modelo.cuentaActiva(correo).getUsuario().getPantallasActivas();

                    int max = (int) ((modelo.cuentaActiva(correo).getSuscripcion().getMembresia().getPantallas()) - 1);

                    if (n <= max) {
                        modelo.cuentaActiva(correo).getUsuario().aumentarPantalla();
                        Archivo archivo = new Archivo();
                        vista.dispose();
                        archivo.serializar(Archivo.archivoArregloCuentas, modelo);
                        cRep = new CtrlReproductor(rep, fRep, video, modelo, modelo.cuentaActiva(correo));
                        cRep.init();
                    } else {
                        JOptionPane.showMessageDialog(vista, "Ha excedido el límite de dispositivos conectados. \n Cierre sesión en al menos uno para poder iniciar en este dipositivo");
                        System.out.println("Sesiones activas : " + modelo.cuentaActiva(correo).getUsuario().getPantallasActivas());
                    }
                } catch (CustomException ex) {
                    Logger.getLogger(CtrlInformacionVideo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    public void init() {
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
        this.vista.setResizable(false);

        String elenco = "";

        for (String l : video.getElenco()) {
            elenco = elenco + l + ", ";
        }

        elenco = "<html><body>Elenco: " + elenco + "</body><html>";

        String titulo = "<html> "
                + "<h1>" + video.getTitulo() + "</h1> "
                + "</html>";

        String director = "<html><body><p>Director: " + video.getDirector() + "</p></body><html>";

        String genero = "Genero: " + video.getGenero();

        String sinopsis = "<html><body>" + video.getSinopsis() + "</body></html>";

        this.vista.lblElenco.setText(elenco);
        this.vista.lblGenero.setText(genero);
        this.vista.lblSinopsis.setText(sinopsis);
        this.vista.lblDirector.setText(director);
        this.vista.lblTituloPelicula.setHorizontalAlignment(SwingConstants.CENTER);
        this.vista.lblTituloPelicula.setText(titulo);
        this.vista.btnImagen.setIcon(new ImageIcon(video.getDirImg()));
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
