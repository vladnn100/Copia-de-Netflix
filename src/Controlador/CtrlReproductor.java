package Controlador;

import Media.ArregloVideos;
import Media.Video;
import Media.Visualizacion;
import Modelo.ArregloDeCuentas;
import Modelo.Cuenta;
import Modelo.CustomException;
import Modelo.Reproductor;
import Vista.FrmCatalogo;
import Vista.FrmReproductor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.MediaPlayer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Julian
 */
public class CtrlReproductor {

    Reproductor modelo;
    FrmReproductor vista;
    private Video mov;
    private Cuenta cuenta;
    private int state;
    private ArregloVideos videos;
    private int indiceVideoSeleccionado;

    public CtrlReproductor(Reproductor modelo, FrmReproductor vista, Video mov, ArregloDeCuentas aCuenta, Cuenta cuenta) {
        this.modelo = modelo;
        this.vista = vista;
        this.mov = mov;
        this.cuenta = cuenta;
        this.state = 0;

        //PERSONALIZANDO EL CIERRE DEL PROGRAMA//
        vista.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        vista.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                JFrame frame = (JFrame) e.getSource();
                vista.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                modelo.detener();
                //VOLVEMOS AL CATALOGO//
                FrmCatalogo fCatalogo = new FrmCatalogo();
                CtrlCatalogo ctrlCatalogo = new CtrlCatalogo(aCuenta, fCatalogo);
                String auxCorreo = cuenta.getUsuario().getCorreo();
                ctrlCatalogo.setCorreo(auxCorreo);
                //                    ctrlCatalogo.setVideosRecomendados(videosRecomendados);
                
                try {
                    ctrlCatalogo.initBack();
                } catch (CustomException ex) {
                    Logger.getLogger(CtrlReproductor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        ////////////////
        //       if (state==0) { 
        //                vista.btnPause.setIcon(new ImageIcon("src\\Botones\\Player\\pause.png"));
        //                
        //                
        //                }
        //                
        //                else if (state==1) {vista.btnPause.setIcon(new ImageIcon("src\\Botones\\Player\\play.png")); 
        //                state=0;
        //               
        //                
        //      }
//         if (state==0) { 
//                vista.btnPause.setIcon(new ImageIcon("src\\Botones\\Player\\pause.png"));
//                
//                
//                }
//                
//                else if (state==1) {vista.btnPause.setIcon(new ImageIcon("src\\Botones\\Player\\play.png")); 
//                state=0;
//               
//                
//                }
        vista.btnPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                click(state);

            }
        });

        vista.btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                modelo.detener();
            }
        });
        vista.btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                modelo.detener();
                vista.hide();
                FrmCatalogo fCatalogo = new FrmCatalogo();
                CtrlCatalogo ctrlCatalogo = new CtrlCatalogo(aCuenta, fCatalogo);
                String auxCorreo = cuenta.getUsuario().getCorreo();
                ctrlCatalogo.setCorreo(auxCorreo);
                //                    ctrlCatalogo.setVideosRecomendados(videosRecomendados);
                
                //CAPTAMOS LA PELICULA QUE SE ESTA REPRODUCIENDO
                try {
                    
                    Video p = aCuenta.cuentaActiva(auxCorreo).getPerfiles().perfilActivo().getVisualizaciones().reproduciendo();
                    
                    int duracion = p.getDuracion();
                    int rand = (int) (Math.random() * duracion);
                    if (aCuenta.cuentaActiva(auxCorreo).getPerfiles().perfilActivo().getVisualizaciones().buscarVideoEnArray(p.getTitulo()).getTiempoVisto() == 0) {
                        aCuenta.cuentaActiva(auxCorreo).getPerfiles().perfilActivo().getVisualizaciones().reproduciendo().setVisto(true);
                        aCuenta.cuentaActiva(auxCorreo).getPerfiles().perfilActivo().getVisualizaciones().reproduciendo().setTiempoVisto(rand);
                        aCuenta.cuentaActiva(auxCorreo).getPerfiles().perfilActivo().getVisualizaciones().reproduciendo().setReproduciendo(false);
                    } else {
                        Video vAux = aCuenta.cuentaActiva(auxCorreo).getPerfiles().perfilActivo().getVisualizaciones().reproduciendo();
                        int tv = rand + vAux.getTiempoVisto();
                        aCuenta.cuentaActiva(auxCorreo).getPerfiles().perfilActivo().getVisualizaciones().reproduciendo().setTiempoVisto(tv);
                        aCuenta.cuentaActiva(auxCorreo).getPerfiles().perfilActivo().getVisualizaciones().reproduciendo().setReproduciendo(false);
                    }
                    
                    aCuenta.cuentaActiva(auxCorreo).getPerfiles().perfilActivo().getVisualizaciones().mostrarContenido();
                    
                    
                    
                    ctrlCatalogo.initBack();
                    
                } catch (CustomException ex) {
                    Logger.getLogger(CtrlReproductor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        vista.sldVolumen.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                modelo.controlVolumen((double) vista.sldVolumen.getValue() / 100);
            }
        });

    }

    public void click(int state) {
        JPanel a = this.vista.jBoxButtons;
        a.setVisible(true);
        vista.btnPause.setIcon(new ImageIcon("src\\Botones\\Player\\pause.png"));
        if (state == 0) {
            state = 1;
            vista.btnPause.setIcon(new ImageIcon("src\\Botones\\Player\\play.png"));
            a.setVisible(true);

            modelo.pausar();
            changeState(state);

        } else if (state == 1) {
            state = 0;
            vista.btnPause.setIcon(new ImageIcon("src\\Botones\\Player\\pause.png"));

            a.setVisible(true);

            modelo.reproducir();
            changeState(state);
        }
    }

    public void initButton() {
        JPanel a = this.vista.jBoxButtons;
        a.setVisible(true);
        vista.btnPause.setIcon(new ImageIcon("src\\Botones\\Player\\pause.png"));
        vista.btnPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                modelo.pausar();
            }
        });
    }

    public void init() {
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        MediaPlayer a = modelo.getMediaPlayer();
        System.out.println("Pantallas activas: " + cuenta.getUsuario().getPantallasActivas());
        ////INICIANDO VIDEO/////
        try {
            if (modelo.getMediaPlayer() != null) {
                modelo.detener();
            }
            String ruta = mov.getDirVid();

            if (!ruta.isEmpty()) {
                String nombre = mov.getTitulo();
                vista.lblTitulo.setText(nombre);

                modelo.setRuta(ruta);
                modelo.setJpanel(vista.jContenedor);
                modelo.mostrarVideo();

                initButton();
                modelo.reproducir();
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(vista, "No hay video asignado");

        }
    }

    public Video getPeli() {
        return mov;
    }

    public void setPeli(Video mov) {
        this.mov = mov;
    }

    public void changeState(int state) {
        this.state = state;
    }

    public int getIndiceVideoSeleccionado() {
        return indiceVideoSeleccionado;
    }

    public void setIndiceVideoSeleccionado(int indiceVideoSeleccionado) {
        this.indiceVideoSeleccionado = indiceVideoSeleccionado;
    }
    
}
