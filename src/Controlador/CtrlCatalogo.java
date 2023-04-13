package Controlador;

import Media.ArregloPeliculas;
import Media.ArregloSeries;
import Media.ArregloVideos;
import Media.Multimedia;
import Media.Pelicula;
import Media.Serie;
import Media.Video;
import Media.Visualizacion;
import Media.Visualizaciones;
import Modelo.Archivo;
import Modelo.ArregloDeCuentas;
import Modelo.ArregloPerfiles;
import Modelo.CustomException;
import Modelo.Reproductor;
import Vista.FrmCatalogo;
import Vista.FrmInformacionVideo;
import Vista.FrmPerfiles;
import Vista.FrmReproductor;
import Vista.FrmTopDiezPeliculas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class CtrlCatalogo {

    private ArregloDeCuentas modelo;
    private FrmCatalogo vista;
    private String auxCorreo;
    private ArregloPeliculas peliculas;
    private ArregloVideos videos;
    private ArregloSeries series;

    private ArregloVideos videosRecomendados;

    private int btnSeleccionado;
    //0 inicio 1 peliculas 2 series

    int indexPrincipal;
    int indexSecDer;
    int indexSecIzq;
    int indexTerDer;
    int indexTerIzq;

    int indexRecoPrincipal;
    int indexRecoSecDer;
    int indexRecoSecIzq;
    int indexRecoTerDer;
    int indexRecoTerIzq;
    int idPerfil;

    public CtrlCatalogo(ArregloDeCuentas modelo, FrmCatalogo vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.auxCorreo = getCorreo();
        series = Multimedia.catalogoSeries;
        peliculas = Multimedia.catalogoPeliculas;
        this.videos = Multimedia.catalogoVideos;
        this.idPerfil = getIdP();
        indexPrincipal = 2;
        indexSecDer = indexPrincipal + 1;
        indexSecIzq = indexPrincipal - 1;
        indexTerDer = indexSecDer + 1;
        indexTerIzq = indexSecIzq - 1;
        indexRecoPrincipal = 2;
        indexRecoSecDer = indexRecoPrincipal + 1;
        indexRecoSecIzq = indexRecoPrincipal - 1;
        indexRecoTerDer = indexRecoSecDer + 1;
        indexRecoTerIzq = indexRecoSecIzq - 1;
        videosRecomendados = Multimedia.catalogoVideos.getDiezMasVistos();
        if (videosRecomendados == null) {
            videosRecomendados = new ArregloVideos();
            for (int i = 0; i < 10; i++) {
                videosRecomendados.agregarVideo(videos.getVideo(i));
            }
        }

        ActionListener actualizar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                if (btnSeleccionado == 0) {
                    mostrarVideos();
                } else if (btnSeleccionado == 1) {
                    mostrarPeliculas();
                } else if (btnSeleccionado == 2) {
                    mostrarSeries();
                } else {
                    JOptionPane.showMessageDialog(vista, "Inserte un mensaje de error aqui");
                }

            }
        };
        ActionListener moverDer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                int parametro = 0;

                if (btnSeleccionado == 0) {
                    parametro = videos.dimension();
                } else if (btnSeleccionado == 1) {
                    parametro = peliculas.dimension();
                } else if (btnSeleccionado == 2) {
                    parametro = series.dimension();
                } else {
                    JOptionPane.showMessageDialog(vista, "Inserte un mensaje de error aqui");
                }

                indexPrincipal = indexPrincipal + 1;
                indexSecDer = indexSecDer + 1;
                indexSecIzq = indexSecIzq + 1;
                indexTerDer = indexTerDer + 1;
                indexTerIzq = indexTerIzq + 1;

                if (indexPrincipal > parametro - 1) {
                    indexPrincipal = 0;
                }
                if (indexSecDer > parametro - 1) {
                    indexSecDer = 0;
                }
                if (indexSecIzq > parametro - 1) {
                    indexSecIzq = 0;
                }
                if (indexTerDer > parametro - 1) {
                    indexTerDer = 0;
                }
                if (indexTerIzq > parametro - 1) {
                    indexTerIzq = 0;
                }

                actualizar.actionPerformed(ae);

            }
        };
        ActionListener moverIzq = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int parametro = 0;

                if (btnSeleccionado == 0) {
                    parametro = videos.dimension();
                } else if (btnSeleccionado == 1) {
                    parametro = peliculas.dimension();
                } else if (btnSeleccionado == 2) {
                    parametro = series.dimension();
                } else {
                    JOptionPane.showMessageDialog(vista, "Inserte un mensaje de error aqui");
                }

                indexPrincipal = indexPrincipal - 1;
                indexSecDer = indexSecDer - 1;
                indexSecIzq = indexSecIzq - 1;
                indexTerDer = indexTerDer - 1;
                indexTerIzq = indexTerIzq - 1;

                if (indexPrincipal < 0) {
                    indexPrincipal = parametro - 1;
                }
                if (indexSecDer < 0) {
                    indexSecDer = parametro - 1;
                }
                if (indexSecIzq < 0) {
                    indexSecIzq = parametro - 1;
                }
                if (indexTerDer < 0) {
                    indexTerDer = parametro - 1;
                }
                if (indexTerIzq < 0) {
                    indexTerIzq = parametro - 1;
                }
                actualizar.actionPerformed(e);

            }
        };
        ActionListener moverRecoDer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

//                int parametro = videosRecomendados.dimension();
                int parametro = 10;

                indexRecoPrincipal = indexRecoPrincipal + 1;
                indexRecoSecDer = indexRecoSecDer + 1;
                indexRecoSecIzq = indexRecoSecIzq + 1;
                indexRecoTerDer = indexRecoTerDer + 1;
                indexRecoTerIzq = indexRecoTerIzq + 1;

                if (indexRecoPrincipal > parametro - 1) {
                    indexRecoPrincipal = 0;
                }
                if (indexRecoSecDer > parametro - 1) {
                    indexRecoSecDer = 0;
                }
                if (indexRecoSecIzq > parametro - 1) {
                    indexRecoSecIzq = 0;
                }
                if (indexRecoTerDer > parametro - 1) {
                    indexRecoTerDer = 0;
                }
                if (indexRecoTerIzq > parametro - 1) {
                    indexRecoTerIzq = 0;
                }

                mostrarVideosRecomendados();

            }
        };
        ActionListener moverRecoIzq = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

//                int parametro = videosRecomendados.dimension();
                int parametro = 10;

                indexRecoPrincipal = indexRecoPrincipal - 1;
                indexRecoSecDer = indexRecoSecDer - 1;
                indexRecoSecIzq = indexRecoSecIzq - 1;
                indexRecoTerDer = indexRecoTerDer - 1;
                indexRecoTerIzq = indexRecoTerIzq - 1;

                if (indexRecoPrincipal < 0) {
                    indexRecoPrincipal = parametro - 1;
                }
                if (indexRecoSecDer < 0) {
                    indexRecoSecDer = parametro - 1;
                }
                if (indexRecoSecIzq < 0) {
                    indexRecoSecIzq = parametro - 1;
                }
                if (indexRecoTerDer < 0) {
                    indexRecoTerDer = parametro - 1;
                }
                if (indexRecoTerIzq < 0) {
                    indexRecoTerIzq = parametro - 1;
                }

                mostrarVideosRecomendados();

            }
        };
        this.vista.btnDerecha.addActionListener(moverDer);
        this.vista.btnIzquierda.addActionListener(moverIzq);
        this.vista.btnRecoDerecha.addActionListener(moverRecoDer);
        this.vista.btnRecoIzquierda.addActionListener(moverRecoIzq);
        this.vista.btnInicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnSeleccionado = 0;

                indexPrincipal = 2;
                indexSecDer = indexPrincipal + 1;
                indexSecIzq = indexPrincipal - 1;
                indexTerDer = indexSecDer + 1;
                indexTerIzq = indexSecIzq - 1;
                actualizar.actionPerformed(ae);
            }
        });
        this.vista.btnPeliculas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                indexPrincipal = 2;
                indexSecDer = indexPrincipal + 1;
                indexSecIzq = indexPrincipal - 1;
                indexTerDer = indexSecDer + 1;
                indexTerIzq = indexSecIzq - 1;

                btnSeleccionado = 1;

                actualizar.actionPerformed(ae);
            }
        });
        this.vista.btnSeries.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnSeleccionado = 2;

                indexPrincipal = 2;
                indexSecDer = indexPrincipal + 1;
                indexSecIzq = indexPrincipal - 1;
                indexTerDer = indexSecDer + 1;
                indexTerIzq = indexSecIzq - 1;

                actualizar.actionPerformed(ae);
            }
        });
        try {
            Visualizaciones v = modelo.cuentaActiva(auxCorreo).getPerfiles().perfilActivo().getVisualizaciones();
            if (v == null) {
                modelo.cuentaActiva(auxCorreo).getPerfiles().perfilActivo().setVisualizaciones(new Visualizaciones());
            }
        } catch (Exception e) {

        }
        //PERSONALIZANDO EL CIERRE DEL PROGRAMA//
        vista.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        vista.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                JFrame frame = (JFrame) e.getSource();
                try {
                    modelo.cuentaActiva(auxCorreo).getUsuario().disminuirDispositivo();
                    //GUARDANDO LOS CAMBIOS EN EL ARCHIVO//
                    Archivo archivo = new Archivo();
                    archivo.serializar(Archivo.archivoArregloCuentas, modelo);
                    // // // // // // // // // // // // // //
                    vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } catch (CustomException ex) {
                    Logger.getLogger(CtrlAdministrarCuenta.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        ////////////////
        vista.btnInformación.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                Video videoSeleccionado = null;

                if (btnSeleccionado == 0) {
                    videoSeleccionado = videos.getVideo(indexPrincipal);
                } else if (btnSeleccionado == 1) {
                    videoSeleccionado = peliculas.getPelicula(indexPrincipal);
                } else if (btnSeleccionado == 2) {
                    videoSeleccionado = series.getSerie(indexPrincipal);
                }

                vista.dispose();
                FrmInformacionVideo fInfo = new FrmInformacionVideo();
                CtrlInformacionVideo cInfo = new CtrlInformacionVideo(fInfo, modelo);
                cInfo.setVideo(videoSeleccionado);
                cInfo.setCorreo(auxCorreo);
                cInfo.init();
            }
        });
        this.vista.btnPrincipal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int indiceEnArregloVideos = 0;
                try {
                    Video v = null;
                    if (btnSeleccionado == 0) {
                        v = Multimedia.catalogoVideos.getVideo(indexPrincipal);
                        indiceEnArregloVideos = indexPrincipal;
                    } else if (btnSeleccionado == 1) {
                        v = Multimedia.catalogoPeliculas.getPelicula(indexPrincipal);
                        indiceEnArregloVideos = Multimedia.catalogoSeries.dimension() - 1 + indexPrincipal;
                    } else if (btnSeleccionado == 2) {
                        v = Multimedia.catalogoSeries.getSerie(indexPrincipal);
                        indiceEnArregloVideos = indexPrincipal;
                    }

                    Reproductor rep = new Reproductor();
                    FrmReproductor fRep = new FrmReproductor();
                    CtrlReproductor cRep;

                    int n = modelo.cuentaActiva(auxCorreo).getUsuario().getPantallasActivas();
                    int max = (int) ((modelo.cuentaActiva(auxCorreo).getSuscripcion().getMembresia().getPantallas()) - 1);

                    if (n <= max) {

                        Pelicula p = new Pelicula();
                        p = Multimedia.catalogoPeliculas.getPelicula(indexPrincipal);
                        System.out.println("DURACION PELICULA A AGREGAR: " + p.getDuracion());
                        p.setReproduciendo(true);
                        //Multimedia.catalogoPeliculas.getPelicula(iSelect).setReproduciendo(true);

                        if (modelo.cuentaActiva(auxCorreo).getPerfiles().perfilActivo().getVisualizaciones().buscarPorTitulo(p.getTitulo()) == false) {
                            p.setCant_Visualizaciones(1);
                            modelo.cuentaActiva(auxCorreo).getPerfiles().perfilActivo().getVisualizaciones().agregarVisulizacion(new Visualizacion(p));
                        } else {
                            Video vAux = modelo.cuentaActiva(auxCorreo).getPerfiles().perfilActivo().getVisualizaciones().buscarVideoEnArray(p.getTitulo());
                            int cv = vAux.getCant_Visualizaciones();
                            cv++;
                            vAux.setCant_Visualizaciones(cv);
                            modelo.cuentaActiva(auxCorreo).getPerfiles().perfilActivo().getVisualizaciones().buscarVideoEnArray(p.getTitulo()).setCant_Visualizaciones(cv);
                        }

                        modelo.cuentaActiva(auxCorreo).getUsuario().aumentarPantalla();

                        Archivo archivo = new Archivo();
                        vista.dispose();
                        archivo.serializar(Archivo.archivoArregloCuentas, modelo);

                        cRep = new CtrlReproductor(rep, fRep, v, modelo, modelo.cuentaActiva(auxCorreo));
                        Multimedia.catalogoVideos.ver(indiceEnArregloVideos);
                        cRep.init();
                        vista.dispose();
                    } else {
                        JOptionPane.showMessageDialog(vista, "Ha excedido el límite de dispositivos conectados. \n Cierre sesión en al menos uno para poder iniciar en este dipositivo");
                        System.out.println("Sesiones activas : " + modelo.cuentaActiva(auxCorreo).getUsuario().getPantallasActivas());
                        vista.dispose();
                        FrmCatalogo fCatalogo = new FrmCatalogo();
                        CtrlCatalogo ctrlCatalogo = new CtrlCatalogo(modelo, fCatalogo);
                        ctrlCatalogo.setCorreo(auxCorreo);
                        ctrlCatalogo.init();
                    }
                } catch (CustomException ex) {
                    Logger.getLogger(CtrlCatalogo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.vista.btnTerIzq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int indiceEnArregloVideos = 0;
                try {
                    Video v = null;
                    if (btnSeleccionado == 0) {
                        v = Multimedia.catalogoVideos.getVideo(indexTerIzq);
                        indiceEnArregloVideos = indexTerIzq;
                    } else if (btnSeleccionado == 1) {
                        v = Multimedia.catalogoPeliculas.getPelicula(indexTerIzq);
                        indiceEnArregloVideos = Multimedia.catalogoSeries.dimension() - 1 + indexTerIzq;
                    } else if (btnSeleccionado == 2) {
                        v = Multimedia.catalogoSeries.getSerie(indexTerIzq);
                        indiceEnArregloVideos = indexTerIzq;
                    }

                    Reproductor rep = new Reproductor();
                    FrmReproductor fRep = new FrmReproductor();
                    CtrlReproductor cRep;
                    int n = modelo.cuentaActiva(auxCorreo).getUsuario().getPantallasActivas();
                    int max = (int) ((modelo.cuentaActiva(auxCorreo).getSuscripcion().getMembresia().getPantallas()) - 1);

                    if (n <= max) {
                        modelo.cuentaActiva(auxCorreo).getUsuario().aumentarPantalla();
                        Archivo archivo = new Archivo();
                        archivo.serializar(Archivo.archivoArregloCuentas, modelo);
                        cRep = new CtrlReproductor(rep, fRep, v, modelo, modelo.cuentaActiva(auxCorreo));
                        Multimedia.catalogoVideos.ver(indiceEnArregloVideos);
                        cRep.init();
                        vista.dispose();
                    } else {
                        JOptionPane.showMessageDialog(vista, "Ha excedido el límite de dispositivos conectados. \n Cierre sesión en al menos uno para poder iniciar en este dipositivo");
                        System.out.println("Sesiones activas : " + modelo.cuentaActiva(auxCorreo).getUsuario().getPantallasActivas());
                        vista.dispose();
                        FrmCatalogo fCatalogo = new FrmCatalogo();
                        CtrlCatalogo ctrlCatalogo = new CtrlCatalogo(modelo, fCatalogo);
                        ctrlCatalogo.setCorreo(auxCorreo);
                        ctrlCatalogo.init();
                    }
                } catch (CustomException ex) {
                    Logger.getLogger(CtrlCatalogo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.vista.btnSecIzq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int indiceEnArregloVideos = 0;
                try {
                    Video v = null;
                    if (btnSeleccionado == 0) {
                        v = Multimedia.catalogoVideos.getVideo(indexSecIzq);
                        indiceEnArregloVideos = indexSecIzq;
                    } else if (btnSeleccionado == 1) {
                        v = Multimedia.catalogoPeliculas.getPelicula(indexSecIzq);
                        indiceEnArregloVideos = Multimedia.catalogoSeries.dimension() - 1 + indexSecIzq;
                    } else if (btnSeleccionado == 2) {
                        v = Multimedia.catalogoSeries.getSerie(indexSecIzq);
                        indiceEnArregloVideos = indexSecIzq;
                    }

                    Reproductor rep = new Reproductor();
                    FrmReproductor fRep = new FrmReproductor();
                    CtrlReproductor cRep;
                    int n = modelo.cuentaActiva(auxCorreo).getUsuario().getPantallasActivas();
                    int max = (int) ((modelo.cuentaActiva(auxCorreo).getSuscripcion().getMembresia().getPantallas()) - 1);

                    if (n <= max) {
                        modelo.cuentaActiva(auxCorreo).getUsuario().aumentarPantalla();
                        Archivo archivo = new Archivo();
                        archivo.serializar(Archivo.archivoArregloCuentas, modelo);
                        cRep = new CtrlReproductor(rep, fRep, v, modelo, modelo.cuentaActiva(auxCorreo));
                        Multimedia.catalogoVideos.ver(indiceEnArregloVideos);
                        cRep.init();
                        vista.dispose();
                    } else {
                        JOptionPane.showMessageDialog(vista, "Ha excedido el límite de dispositivos conectados. \n Cierre sesión en al menos uno para poder iniciar en este dipositivo");
                        System.out.println("Sesiones activas : " + modelo.cuentaActiva(auxCorreo).getUsuario().getPantallasActivas());
                        vista.dispose();
                        FrmCatalogo fCatalogo = new FrmCatalogo();
                        CtrlCatalogo ctrlCatalogo = new CtrlCatalogo(modelo, fCatalogo);
                        ctrlCatalogo.setCorreo(auxCorreo);
                        ctrlCatalogo.init();
                    }
                } catch (CustomException ex) {
                    Logger.getLogger(CtrlCatalogo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.vista.btnSecDer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int indiceEnArregloVideos = 0;
                try {
                    Video v = null;
                    if (btnSeleccionado == 0) {
                        v = Multimedia.catalogoVideos.getVideo(indexSecDer);
                        indiceEnArregloVideos = indexSecDer;
                    } else if (btnSeleccionado == 1) {
                        v = Multimedia.catalogoPeliculas.getPelicula(indexSecDer);
                        indiceEnArregloVideos = Multimedia.catalogoSeries.dimension() - 1 + indexSecDer;
                    } else if (btnSeleccionado == 2) {
                        v = Multimedia.catalogoSeries.getSerie(indexSecDer);
                        indiceEnArregloVideos = indexSecDer;
                    }

                    Reproductor rep = new Reproductor();
                    FrmReproductor fRep = new FrmReproductor();
                    CtrlReproductor cRep;
                    int n = modelo.cuentaActiva(auxCorreo).getUsuario().getPantallasActivas();
                    int max = (int) ((modelo.cuentaActiva(auxCorreo).getSuscripcion().getMembresia().getPantallas()) - 1);

                    if (n <= max) {
                        modelo.cuentaActiva(auxCorreo).getUsuario().aumentarPantalla();
                        Archivo archivo = new Archivo();
                        archivo.serializar(Archivo.archivoArregloCuentas, modelo);
                        Multimedia.catalogoVideos.ver(indiceEnArregloVideos);
                        cRep = new CtrlReproductor(rep, fRep, v, modelo, modelo.cuentaActiva(auxCorreo));
                        cRep.init();
                        vista.dispose();
                    } else {
                        JOptionPane.showMessageDialog(vista, "Ha excedido el límite de dispositivos conectados. \n Cierre sesión en al menos uno para poder iniciar en este dipositivo");
                        System.out.println("Sesiones activas : " + modelo.cuentaActiva(auxCorreo).getUsuario().getPantallasActivas());
                        vista.dispose();
                        FrmCatalogo fCatalogo = new FrmCatalogo();
                        CtrlCatalogo ctrlCatalogo = new CtrlCatalogo(modelo, fCatalogo);
                        ctrlCatalogo.setCorreo(auxCorreo);
                        ctrlCatalogo.init();
                    }
                } catch (CustomException ex) {
                    Logger.getLogger(CtrlCatalogo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.vista.btnTerDer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int indiceEnArregloVideos = 0;
                try {
                    Video v = null;
                    if (btnSeleccionado == 0) {
                        v = Multimedia.catalogoVideos.getVideo(indexTerDer);
                        indiceEnArregloVideos = indexTerDer;
                    } else if (btnSeleccionado == 1) {
                        v = Multimedia.catalogoPeliculas.getPelicula(indexTerDer);
                        indiceEnArregloVideos = Multimedia.catalogoSeries.dimension() - 1 + indexTerDer;
                    } else if (btnSeleccionado == 2) {
                        v = Multimedia.catalogoSeries.getSerie(indexTerDer);
                        indiceEnArregloVideos = indexTerDer;
                    }

                    Reproductor rep = new Reproductor();
                    FrmReproductor fRep = new FrmReproductor();
                    CtrlReproductor cRep;
                    int n = modelo.cuentaActiva(auxCorreo).getUsuario().getPantallasActivas();
                    int max = (int) ((modelo.cuentaActiva(auxCorreo).getSuscripcion().getMembresia().getPantallas()) - 1);

                    if (n <= max) {
                        modelo.cuentaActiva(auxCorreo).getUsuario().aumentarPantalla();
                        Archivo archivo = new Archivo();
                        archivo.serializar(Archivo.archivoArregloCuentas, modelo);
                        Multimedia.catalogoVideos.ver(indiceEnArregloVideos);
                        cRep = new CtrlReproductor(rep, fRep, v, modelo, modelo.cuentaActiva(auxCorreo));
                        cRep.init();
                        vista.dispose();
                    } else {
                        JOptionPane.showMessageDialog(vista, "Ha excedido el límite de dispositivos conectados. \n Cierre sesión en al menos uno para poder iniciar en este dipositivo");
                        System.out.println("Sesiones activas : " + modelo.cuentaActiva(auxCorreo).getUsuario().getPantallasActivas());
                        vista.dispose();
                        FrmCatalogo fCatalogo = new FrmCatalogo();
                        CtrlCatalogo ctrlCatalogo = new CtrlCatalogo(modelo, fCatalogo);
                        ctrlCatalogo.setCorreo(auxCorreo);
                        ctrlCatalogo.init();
                    }
                } catch (CustomException ex) {
                    Logger.getLogger(CtrlCatalogo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.vista.btnRecoPrincipal1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int indiceEnArregloVideos = 0;
                try {
                    Video v = videosRecomendados.getVideo(indexRecoPrincipal);
                    indiceEnArregloVideos = Multimedia.catalogoVideos.getIndice(v);

                    Reproductor rep = new Reproductor();
                    FrmReproductor fRep = new FrmReproductor();
                    CtrlReproductor cRep;

                    int n = modelo.cuentaActiva(auxCorreo).getUsuario().getPantallasActivas();
                    int max = (int) ((modelo.cuentaActiva(auxCorreo).getSuscripcion().getMembresia().getPantallas()) - 1);

                    if (n <= max) {

                        Pelicula p = new Pelicula();
                        p = Multimedia.catalogoPeliculas.getPelicula(indexPrincipal);
                        System.out.println("DURACION PELICULA A AGREGAR: " + p.getDuracion());
                        p.setReproduciendo(true);
                        //Multimedia.catalogoPeliculas.getPelicula(iSelect).setReproduciendo(true);

                        if (modelo.cuentaActiva(auxCorreo).getPerfiles().perfilActivo().getVisualizaciones().buscarPorTitulo(p.getTitulo()) == false) {
                            p.setCant_Visualizaciones(1);
                            modelo.cuentaActiva(auxCorreo).getPerfiles().perfilActivo().getVisualizaciones().agregarVisulizacion(new Visualizacion(p));
                        } else {
                            Video vAux = modelo.cuentaActiva(auxCorreo).getPerfiles().perfilActivo().getVisualizaciones().buscarVideoEnArray(p.getTitulo());
                            int cv = vAux.getCant_Visualizaciones();
                            cv++;
                            vAux.setCant_Visualizaciones(cv);
                            modelo.cuentaActiva(auxCorreo).getPerfiles().perfilActivo().getVisualizaciones().buscarVideoEnArray(p.getTitulo()).setCant_Visualizaciones(cv);
                        }

                        modelo.cuentaActiva(auxCorreo).getUsuario().aumentarPantalla();

                        Archivo archivo = new Archivo();
                        vista.dispose();
                        archivo.serializar(Archivo.archivoArregloCuentas, modelo);

                        cRep = new CtrlReproductor(rep, fRep, v, modelo, modelo.cuentaActiva(auxCorreo));
                        Multimedia.catalogoVideos.ver(indiceEnArregloVideos);
                        cRep.init();
                        vista.dispose();
                    } else {
                        JOptionPane.showMessageDialog(vista, "Ha excedido el límite de dispositivos conectados. \n Cierre sesión en al menos uno para poder iniciar en este dipositivo");
                        System.out.println("Sesiones activas : " + modelo.cuentaActiva(auxCorreo).getUsuario().getPantallasActivas());
                        vista.dispose();
                        FrmCatalogo fCatalogo = new FrmCatalogo();
                        CtrlCatalogo ctrlCatalogo = new CtrlCatalogo(modelo, fCatalogo);
                        ctrlCatalogo.setCorreo(auxCorreo);
                        ctrlCatalogo.init();
                    }
                } catch (CustomException ex) {
                    Logger.getLogger(CtrlCatalogo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.vista.btnRecoTerIzq1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int indiceEnArregloVideos = 0;
                try {

                    Video v = videosRecomendados.getVideo(indexRecoTerIzq);
                    indiceEnArregloVideos = Multimedia.catalogoVideos.getIndice(v);

                    Reproductor rep = new Reproductor();
                    FrmReproductor fRep = new FrmReproductor();
                    CtrlReproductor cRep;
                    int n = modelo.cuentaActiva(auxCorreo).getUsuario().getPantallasActivas();
                    int max = (int) ((modelo.cuentaActiva(auxCorreo).getSuscripcion().getMembresia().getPantallas()) - 1);

                    if (n <= max) {
                        modelo.cuentaActiva(auxCorreo).getUsuario().aumentarPantalla();
                        Archivo archivo = new Archivo();
                        archivo.serializar(Archivo.archivoArregloCuentas, modelo);
                        cRep = new CtrlReproductor(rep, fRep, v, modelo, modelo.cuentaActiva(auxCorreo));
                        Multimedia.catalogoVideos.ver(indiceEnArregloVideos);
                        cRep.init();
                        vista.dispose();
                    } else {
                        JOptionPane.showMessageDialog(vista, "Ha excedido el límite de dispositivos conectados. \n Cierre sesión en al menos uno para poder iniciar en este dipositivo");
                        System.out.println("Sesiones activas : " + modelo.cuentaActiva(auxCorreo).getUsuario().getPantallasActivas());
                        vista.dispose();
                        FrmCatalogo fCatalogo = new FrmCatalogo();
                        CtrlCatalogo ctrlCatalogo = new CtrlCatalogo(modelo, fCatalogo);
                        ctrlCatalogo.setCorreo(auxCorreo);
                        ctrlCatalogo.init();
                    }
                } catch (CustomException ex) {
                    Logger.getLogger(CtrlCatalogo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.vista.btnRecoSecIzq1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int indiceEnArregloVideos = 0;
                try {

                    Video v = videosRecomendados.getVideo(indexRecoSecIzq);
                    indiceEnArregloVideos = Multimedia.catalogoVideos.getIndice(v);

                    Reproductor rep = new Reproductor();
                    FrmReproductor fRep = new FrmReproductor();
                    CtrlReproductor cRep;
                    int n = modelo.cuentaActiva(auxCorreo).getUsuario().getPantallasActivas();
                    int max = (int) ((modelo.cuentaActiva(auxCorreo).getSuscripcion().getMembresia().getPantallas()) - 1);

                    if (n <= max) {
                        modelo.cuentaActiva(auxCorreo).getUsuario().aumentarPantalla();
                        Archivo archivo = new Archivo();
                        archivo.serializar(Archivo.archivoArregloCuentas, modelo);
                        cRep = new CtrlReproductor(rep, fRep, v, modelo, modelo.cuentaActiva(auxCorreo));
                        Multimedia.catalogoVideos.ver(indiceEnArregloVideos);
                        cRep.init();
                        vista.dispose();
                    } else {
                        JOptionPane.showMessageDialog(vista, "Ha excedido el límite de dispositivos conectados. \n Cierre sesión en al menos uno para poder iniciar en este dipositivo");
                        System.out.println("Sesiones activas : " + modelo.cuentaActiva(auxCorreo).getUsuario().getPantallasActivas());
                        vista.dispose();
                        FrmCatalogo fCatalogo = new FrmCatalogo();
                        CtrlCatalogo ctrlCatalogo = new CtrlCatalogo(modelo, fCatalogo);
                        ctrlCatalogo.setCorreo(auxCorreo);
                        ctrlCatalogo.init();
                    }
                } catch (CustomException ex) {
                    Logger.getLogger(CtrlCatalogo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.vista.btnRecoSecDer1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int indiceEnArregloVideos = 0;
                try {

                    Video v = videosRecomendados.getVideo(indexRecoSecDer);
                    indiceEnArregloVideos = Multimedia.catalogoVideos.getIndice(v);

                    Reproductor rep = new Reproductor();
                    FrmReproductor fRep = new FrmReproductor();
                    CtrlReproductor cRep;
                    int n = modelo.cuentaActiva(auxCorreo).getUsuario().getPantallasActivas();
                    int max = (int) ((modelo.cuentaActiva(auxCorreo).getSuscripcion().getMembresia().getPantallas()) - 1);

                    if (n <= max) {
                        modelo.cuentaActiva(auxCorreo).getUsuario().aumentarPantalla();
                        Archivo archivo = new Archivo();
                        archivo.serializar(Archivo.archivoArregloCuentas, modelo);
                        Multimedia.catalogoVideos.ver(indiceEnArregloVideos);
                        cRep = new CtrlReproductor(rep, fRep, v, modelo, modelo.cuentaActiva(auxCorreo));
                        cRep.init();
                        vista.dispose();
                    } else {
                        JOptionPane.showMessageDialog(vista, "Ha excedido el límite de dispositivos conectados. \n Cierre sesión en al menos uno para poder iniciar en este dipositivo");
                        System.out.println("Sesiones activas : " + modelo.cuentaActiva(auxCorreo).getUsuario().getPantallasActivas());
                        vista.dispose();
                        FrmCatalogo fCatalogo = new FrmCatalogo();
                        CtrlCatalogo ctrlCatalogo = new CtrlCatalogo(modelo, fCatalogo);
                        ctrlCatalogo.setCorreo(auxCorreo);
                        ctrlCatalogo.init();
                    }
                } catch (CustomException ex) {
                    Logger.getLogger(CtrlCatalogo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.vista.btnRecoTerDer1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int indiceEnArregloVideos = 0;
                try {

                    Video v = videosRecomendados.getVideo(indexRecoTerDer);
                    indiceEnArregloVideos = Multimedia.catalogoVideos.getIndice(v);

                    Reproductor rep = new Reproductor();
                    FrmReproductor fRep = new FrmReproductor();
                    CtrlReproductor cRep;
                    int n = modelo.cuentaActiva(auxCorreo).getUsuario().getPantallasActivas();
                    int max = (int) ((modelo.cuentaActiva(auxCorreo).getSuscripcion().getMembresia().getPantallas()) - 1);

                    if (n <= max) {
                        modelo.cuentaActiva(auxCorreo).getUsuario().aumentarPantalla();
                        Archivo archivo = new Archivo();
                        archivo.serializar(Archivo.archivoArregloCuentas, modelo);
                        Multimedia.catalogoVideos.ver(indiceEnArregloVideos);
                        cRep = new CtrlReproductor(rep, fRep, v, modelo, modelo.cuentaActiva(auxCorreo));
                        cRep.init();
                        vista.dispose();
                    } else {
                        JOptionPane.showMessageDialog(vista, "Ha excedido el límite de dispositivos conectados. \n Cierre sesión en al menos uno para poder iniciar en este dipositivo");
                        System.out.println("Sesiones activas : " + modelo.cuentaActiva(auxCorreo).getUsuario().getPantallasActivas());
                        vista.dispose();
                        FrmCatalogo fCatalogo = new FrmCatalogo();
                        CtrlCatalogo ctrlCatalogo = new CtrlCatalogo(modelo, fCatalogo);
                        ctrlCatalogo.setCorreo(auxCorreo);
                        ctrlCatalogo.init();
                    }
                } catch (CustomException ex) {
                    Logger.getLogger(CtrlCatalogo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.vista.btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vista.dispose();
                FrmPerfiles fPerfil = new FrmPerfiles();
                CtrlPerfiles ctrlPerfil = new CtrlPerfiles(modelo, fPerfil);
                ctrlPerfil.setCorreo(auxCorreo);
                ctrlPerfil.init();
            }
        });
        this.vista.btnRecomendar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Pelicula p = Multimedia.catalogoPeliculas.recomendarPelicula(modelo.cuentaActiva(auxCorreo).getPerfiles().perfilActivo().getVisualizaciones());
                    if (p.getTitulo().equalsIgnoreCase("Nuevo video")) {
                        JOptionPane.showMessageDialog(vista, "AUN NO SE REGISTRAN VISUALIZACIONES");
                    } else {
                        JOptionPane.showMessageDialog(vista, "Se le recomienda la siguiente pelicula: " + p.getTitulo());
                    }
                } catch (CustomException ex) {
                    System.out.println("EXCEPCION: " + ex.getMessage());
                }
            }
        });
        this.vista.btnTop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                try {
                    //if(modelo.cuentaActiva(auxCorreo).getPerfiles().perfilActivo().getVisualizaciones().validarNroTop()==2){
                    System.out.println("VALIDAR TOP: " + modelo.cuentaActiva(auxCorreo).getPerfiles().perfilActivo().getVisualizaciones().validarNroTop());
                    vista.dispose();
                    FrmTopDiezPeliculas fTop = new FrmTopDiezPeliculas();
                    CtrlTop ctrlTop = new CtrlTop(modelo, fTop);
                    ctrlTop.setAuxCorreo(auxCorreo);
                    ctrlTop.init();
                    /*}
                    else{
                    JOptionPane.showMessageDialog(vista, "NO CUENTA CON LA SUFICIENTES VISULIZACIONES");
                    }*/
                } catch ( CustomException ex) {
                    System.out.println("EXCEPCION: " + ex.getMessage());
                    }
                }
            }
            );
         

            this.vista.btnInformación1.addActionListener ( 
                new ActionListener() {
                @Override
                public void actionPerformed
                (ActionEvent ae
                
                    ) {
                Video videoSeleccionado = null;
                    videoSeleccionado = videosRecomendados.getVideo(indexRecoPrincipal);

                    vista.dispose();
                    FrmInformacionVideo fInfo = new FrmInformacionVideo();
                    CtrlInformacionVideo cInfo = new CtrlInformacionVideo(fInfo, modelo);
                    cInfo.setVideo(videoSeleccionado);
                    cInfo.setCorreo(auxCorreo);
                    cInfo.init();
                }
            }
        );

    }

    public void init() {
        try {
            rellenarComb();
        } catch (CustomException ex) {
            Logger.getLogger(CtrlCatalogo.class.getName()).log(Level.SEVERE, null, ex);
        }
        //ACTUALIZANDO DATOS//
        Archivo archivo = new Archivo();
        modelo = (ArregloDeCuentas) archivo.deserializar(Archivo.archivoArregloCuentas);
        archivo.serializar(Archivo.archivoArregloCuentas, modelo);

        try {
            System.out.println("El correo de mi cuenta es : " + modelo.cuentaActiva(auxCorreo).getUsuario().getCorreo());
        } catch (CustomException ex) {
            System.out.println("Inserte mensaje de error aqui");
        }
        ///////////////////
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);

        if (btnSeleccionado == 0) {
            mostrarVideos();
        } else if (btnSeleccionado == 1) {
            mostrarPeliculas();
        } else if (btnSeleccionado == 2) {
            mostrarSeries();
        } else {
            JOptionPane.showMessageDialog(vista, "Inserte un mensaje de error aqui");
        }

        mostrarVideosRecomendados();

    }

    public void initBack() throws CustomException {
        modelo.cuentaActiva(auxCorreo).getUsuario().disminuirPantalla();
        //GUARDANDO LOS CAMBIOS EN EL ARCHIVO//
        Archivo archivo = new Archivo();
        archivo.serializar(Archivo.archivoArregloCuentas, modelo);
        // // // // // // // // // // // // // // 
        init();

    }

    public String getCorreo() {
        return this.auxCorreo;
    }

    public void setCorreo(String Correo) {
        this.auxCorreo = Correo;
    }

    private void mostrarPeliculas() {
        Pelicula peliculaMostradaIzq1 = peliculas.getPelicula(indexTerIzq);
        Pelicula peliculaMostradaIzq = peliculas.getPelicula(indexSecIzq);
        Pelicula peliculaMostrada = peliculas.getPelicula(indexPrincipal);
        Pelicula peliculaMostradaDer = peliculas.getPelicula(indexSecDer);
        Pelicula peliculaMostradaDer1 = peliculas.getPelicula(indexTerDer);

        vista.btnTerIzq.setText(String.valueOf(indexSecIzq));
        vista.btnPrincipal.setText(String.valueOf(indexPrincipal));
        vista.btnTerDer.setText(String.valueOf(indexSecDer));

        vista.btnTerIzq.setIcon(new ImageIcon(peliculaMostradaIzq1.getDirImg()));
        vista.btnSecIzq.setIcon(new ImageIcon(peliculaMostradaIzq.getDirImg()));
        vista.btnPrincipal.setIcon(new ImageIcon(peliculaMostrada.getDirImg()));
        vista.btnSecDer.setIcon(new ImageIcon(peliculaMostradaDer.getDirImg()));
        vista.btnTerDer.setIcon(new ImageIcon(peliculaMostradaDer1.getDirImg()));

        String lblTexto
                = "<html><h1>"
                + peliculaMostrada.getTitulo()
                + "</h1></html>";

        vista.lblPelicula.setHorizontalAlignment(SwingConstants.CENTER);
        vista.lblPelicula.setText(lblTexto);
    }

    private void mostrarSeries() {
        Serie serieMostradaIzq1 = series.getSerie(indexTerIzq);
        Serie serieMostradaIzq = series.getSerie(indexSecIzq);
        Serie serieMostrada = series.getSerie(indexPrincipal);
        Serie serieMostradaDer = series.getSerie(indexSecDer);
        Serie serieMostradaDer1 = series.getSerie(indexTerDer);

        vista.btnTerIzq.setText(String.valueOf(indexSecIzq));
        vista.btnPrincipal.setText(String.valueOf(indexPrincipal));
        vista.btnTerDer.setText(String.valueOf(indexSecDer));

        vista.btnTerIzq.setIcon(new ImageIcon(serieMostradaIzq1.getDirImg()));
        vista.btnSecIzq.setIcon(new ImageIcon(serieMostradaIzq.getDirImg()));
        vista.btnPrincipal.setIcon(new ImageIcon(serieMostrada.getDirImg()));
        vista.btnSecDer.setIcon(new ImageIcon(serieMostradaDer.getDirImg()));
        vista.btnTerDer.setIcon(new ImageIcon(serieMostradaDer1.getDirImg()));

        String lblTexto
                = "<html><h1>"
                + serieMostrada.getTitulo()
                + "</h1></html>";

        vista.lblPelicula.setHorizontalAlignment(SwingConstants.CENTER);
        vista.lblPelicula.setText(lblTexto);
    }

    private void mostrarVideos() {
        Video videoMostradaAdIzq = videos.getVideo(indexTerIzq);
        Video videoMostradaIzq = videos.getVideo(indexSecIzq);
        Video videoMostrada = videos.getVideo(indexPrincipal);
        Video videoMostradaDer = videos.getVideo(indexSecDer);
        Video videoMostradaAdDer = videos.getVideo(indexTerDer);

        vista.btnTerIzq.setText(String.valueOf(indexTerIzq));
        vista.btnSecIzq.setText(String.valueOf(indexSecIzq));
        vista.btnPrincipal.setText(String.valueOf(indexPrincipal));
        vista.btnSecDer.setText(String.valueOf(indexSecDer));
        vista.btnTerDer.setText(String.valueOf(indexTerDer));

        vista.btnTerIzq.setIcon(new ImageIcon(videoMostradaAdIzq.getDirImg()));
        vista.btnSecIzq.setIcon(new ImageIcon(videoMostradaIzq.getDirImg()));
        vista.btnPrincipal.setIcon(new ImageIcon(videoMostrada.getDirImg()));
        vista.btnSecDer.setIcon(new ImageIcon(videoMostradaDer.getDirImg()));
        vista.btnTerDer.setIcon(new ImageIcon(videoMostradaAdDer.getDirImg()));

        String lblTexto
                = "<html><h1>"
                + videoMostrada.getTitulo()
                + "</h1></html>";

        vista.lblPelicula.setHorizontalAlignment(SwingConstants.CENTER);
        vista.lblPelicula.setText(lblTexto);
    }

    private void mostrarVideosRecomendados() {
        Video videoRecoMostradaAdIzq = videosRecomendados.getVideo(indexRecoTerIzq);
        Video videoRecoMostradaIzq = videosRecomendados.getVideo(indexRecoSecIzq);
        Video videoRecoMostrada = videosRecomendados.getVideo(indexRecoPrincipal);
        Video videoRecoMostradaDer = videosRecomendados.getVideo(indexRecoSecDer);
        Video videoRecoMostradaAdDer = videosRecomendados.getVideo(indexRecoTerDer);

        vista.btnRecoTerIzq1.setText(String.valueOf(indexRecoTerIzq));
        vista.btnRecoSecIzq1.setText(String.valueOf(indexRecoSecIzq));
        vista.btnRecoPrincipal1.setText(String.valueOf(indexRecoPrincipal));
        vista.btnRecoSecDer1.setText(String.valueOf(indexRecoSecDer));
        vista.btnRecoTerDer1.setText(String.valueOf(indexRecoTerDer));

        vista.btnRecoTerIzq1.setIcon(new ImageIcon(videoRecoMostradaAdIzq.getDirImg()));
        vista.btnRecoSecIzq1.setIcon(new ImageIcon(videoRecoMostradaIzq.getDirImg()));
        vista.btnRecoPrincipal1.setIcon(new ImageIcon(videoRecoMostrada.getDirImg()));
        vista.btnRecoSecDer1.setIcon(new ImageIcon(videoRecoMostradaDer.getDirImg()));
        vista.btnRecoTerDer1.setIcon(new ImageIcon(videoRecoMostradaAdDer.getDirImg()));

//        String lblTexto
//                = "<html><h1>"
//                + videoRecoMostrada.getTitulo()
//                + "</h1></html>";
//
//        vista.lblPelicula.setHorizontalAlignment(SwingConstants.CENTER);
//        vista.lblPelicula.setText(lblTexto);
    }

    public void rellenarComb() throws CustomException {
        DefaultComboBoxModel<String> comboPerfiles = (DefaultComboBoxModel) this.vista.comboPerfiles.getModel();

        for (int i = 0; i < 4; i++) {
            String nombre = modelo.cuentaActiva(auxCorreo).getPerfil(i).getNombre();

            comboPerfiles.addElement(nombre);
        }

        String nombrePerfil = modelo.cuentaActiva(auxCorreo).getPerfil(idPerfil).getNombre();
        comboPerfiles.setSelectedItem(nombrePerfil);
        this.vista.comboPerfiles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArregloPerfiles modeloperfil = new ArregloPerfiles();
                    String nombrePerfil = comboPerfiles.getSelectedItem().toString();
                    int i = modeloperfil.posicionPorNombre(nombrePerfil);

                    modelo.cuentaActiva(auxCorreo).getPerfil(i).setActivo(true);

                    FrmCatalogo fCatalogo = new FrmCatalogo();
                    CtrlCatalogo ctrlCatalogo = new CtrlCatalogo(modelo, fCatalogo);
                    ctrlCatalogo.setCorreo(auxCorreo);
                    ctrlCatalogo.setIdP(i);
                    ctrlCatalogo.init();
                    vista.dispose();

                } catch (CustomException ex) {
                    Logger.getLogger(CtrlCatalogo.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }

    public void setIdP(int i) {

        this.idPerfil = i;
    }

    public int getIdP() {
        return this.idPerfil;
    }
}
