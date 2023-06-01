package Controlador;

import Modelo.Archivo;
import Modelo.ArregloDeCuentas;
import Modelo.Cuenta;
import Modelo.CustomException;
import Vista.FrmAdministrarCuenta;
import Vista.FrmCatalogo;
import Vista.FrmEditarPerfiles;
import Vista.FrmInicio;
import Vista.FrmPerfiles;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CtrlPerfiles {

    private ArregloDeCuentas modelo;
    private FrmPerfiles vista;
    private String auxCorreo;

    public CtrlPerfiles(ArregloDeCuentas modelo, FrmPerfiles vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.auxCorreo = getCorreo();
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
        this.vista.btnAdministrarCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vista.dispose();
                FrmAdministrarCuenta fAdminCuenta = new FrmAdministrarCuenta();
                CtrlAdministrarCuenta ctrlAdminCuenta = new CtrlAdministrarCuenta(modelo, fAdminCuenta);
                ctrlAdminCuenta.setCorreo(auxCorreo);
                ctrlAdminCuenta.init();
            }
        });
        this.vista.btnEditar1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                vista.dispose();

                FrmEditarPerfiles fEditarPerfiles = new FrmEditarPerfiles();

                fEditarPerfiles.indexPerfilAux = 0;

                CtrlEditarPerfiles controlador = new CtrlEditarPerfiles(fEditarPerfiles, modelo);
                controlador.setCorreo(auxCorreo);
                controlador.init();
            }
        });

        this.vista.btnEditar2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                vista.dispose();

                FrmEditarPerfiles fEditarPerfiles = new FrmEditarPerfiles();

                fEditarPerfiles.indexPerfilAux = 1;

                CtrlEditarPerfiles controlador = new CtrlEditarPerfiles(fEditarPerfiles, modelo);
                controlador.setCorreo(auxCorreo);
                controlador.init();
            }
        });

        this.vista.btnEditar3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                vista.dispose();

                FrmEditarPerfiles fEditarPerfiles = new FrmEditarPerfiles();

                fEditarPerfiles.indexPerfilAux = 2;

                CtrlEditarPerfiles cEditarPerfiles = new CtrlEditarPerfiles(fEditarPerfiles, modelo);
                cEditarPerfiles.setCorreo(auxCorreo);
                cEditarPerfiles.init();
            }
        });

        this.vista.btnEditar4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.dispose();

                FrmEditarPerfiles fEditarPerfiles = new FrmEditarPerfiles();

                fEditarPerfiles.indexPerfilAux = 3;

                CtrlEditarPerfiles controlador = new CtrlEditarPerfiles(fEditarPerfiles, modelo);
                controlador.setCorreo(auxCorreo);
                controlador.init();
            }
        });

        this.vista.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    vista.dispose();
                    Cuenta a = modelo.cuentaActiva(auxCorreo);
                    String Correo = a.getUsuario().getCorreo();
                    System.out.println(Correo);
                    modelo.cuentaActiva(auxCorreo).getUsuario().disminuirDispositivo();
                    // modelo.cambiarEstadoSesion(Correo);
                    //GUARDANDO LOS CAMBIOS EN EL ARCHIVO//
                    Archivo archivo = new Archivo();
                    archivo.serializar(Archivo.archivoArregloCuentas, modelo);
                    // // // // // // // // // // // // // // 

                    FrmInicio fInicio = new FrmInicio();
                    CtrlInicio ctrlInicio = new CtrlInicio(modelo, fInicio);

                    ctrlInicio.init();
                } catch (CustomException e) {
                    System.out.println("EXCEPCION: " + e.getMessage());
                }
            }
        });

        this.vista.btnPerfil1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Cuenta c = null;

                    try {
                        c = modelo.cuentaActiva(auxCorreo);
                    } catch (CustomException ex) {
                        Logger.getLogger(CtrlPerfiles.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (c.debeCobrar()) {
                        if (!c.cobrar()) {
                            JOptionPane.showMessageDialog(vista, "No cuenta con dinero suficiente para continuar");
                            return;
                        }
                    }

                    modelo.cuentaActiva(auxCorreo).getPerfil(0).setActivo(true);
                    vista.dispose();
                    FrmCatalogo fCatalogo = new FrmCatalogo();
                    CtrlCatalogo ctrlCatalogo = new CtrlCatalogo(modelo, fCatalogo);
                    ctrlCatalogo.setCorreo(auxCorreo);
                    ctrlCatalogo.init();
                } catch (CustomException e) {
                    JOptionPane.showMessageDialog(vista, e.getMessage());
                }
            }
        });

        this.vista.btnPerfil2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {

                    Cuenta c = null;

                    try {
                        c = modelo.cuentaActiva(auxCorreo);
                    } catch (CustomException ex) {
                        Logger.getLogger(CtrlPerfiles.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (c.debeCobrar()) {
                        if (!c.cobrar()) {
                            JOptionPane.showMessageDialog(vista, "No cuenta con dinero suficiente para continuar");
                            return;
                        }
                    }

                    modelo.cuentaActiva(auxCorreo).getPerfil(1).setActivo(true);
                    vista.dispose();
                    FrmCatalogo fCatalogo = new FrmCatalogo();
                    CtrlCatalogo ctrlCatalogo = new CtrlCatalogo(modelo, fCatalogo);
                    ctrlCatalogo.setCorreo(auxCorreo);
                    ctrlCatalogo.init();
                } catch (CustomException e) {
                    JOptionPane.showMessageDialog(vista, e.getMessage());
                }
            }
        });

        this.vista.btnPerfil3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {

                    Cuenta c = null;

                    try {
                        c = modelo.cuentaActiva(auxCorreo);
                    } catch (CustomException ex) {
                        Logger.getLogger(CtrlPerfiles.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (c.debeCobrar()) {
                        if (!c.cobrar()) {
                            JOptionPane.showMessageDialog(vista, "No cuenta con dinero suficiente para continuar");
                            return;
                        }
                    }

                    modelo.cuentaActiva(auxCorreo).getPerfil(3).setActivo(true);
                    vista.dispose();
                    FrmCatalogo fCatalogo = new FrmCatalogo();
                    CtrlCatalogo ctrlCatalogo = new CtrlCatalogo(modelo, fCatalogo);
                    ctrlCatalogo.setCorreo(auxCorreo);
                    ctrlCatalogo.init();
                } catch (CustomException e) {
                    JOptionPane.showMessageDialog(vista, e.getMessage());
                }
            }
        });

        this.vista.btnPerfil4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {

                    Cuenta c = null;

                    try {
                        c = modelo.cuentaActiva(auxCorreo);
                    } catch (CustomException ex) {
                        Logger.getLogger(CtrlPerfiles.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (c.debeCobrar()) {
                        if (!c.cobrar()) {
                            JOptionPane.showMessageDialog(vista, "No cuenta con dinero suficiente para continuar");
                            return;
                        }
                    }

                    modelo.cuentaActiva(auxCorreo).getPerfil(3).setActivo(true);
                    vista.dispose();
                    FrmCatalogo fCatalogo = new FrmCatalogo();
                    CtrlCatalogo ctrlCatalogo = new CtrlCatalogo(modelo, fCatalogo);
                    ctrlCatalogo.setCorreo(auxCorreo);
                    ctrlCatalogo.init();
                } catch (CustomException e) {
                    JOptionPane.showMessageDialog(vista, e.getMessage());
                }
            }
        });
    }

    public void init() {

        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);

        try {
            //ACTUALIZANDO DATOS//
            Archivo archivo = new Archivo();
            modelo = (ArregloDeCuentas) archivo.deserializar(Archivo.archivoArregloCuentas);
            ///////////////////

            System.out.println("El correo de esta cuenta es : " + auxCorreo);
            this.vista.btnPerfil1.setText(modelo.cuentaActiva(auxCorreo).getPerfil(0).toString());
            this.vista.btnPerfil2.setText(modelo.cuentaActiva(auxCorreo).getPerfil(1).toString());
            this.vista.btnPerfil3.setText(modelo.cuentaActiva(auxCorreo).getPerfil(2).toString());
            this.vista.btnPerfil4.setText(modelo.cuentaActiva(auxCorreo).getPerfil(3).toString());

            modelo.mostrarCuentasActivas();
        } catch (CustomException e) {
            System.out.println("excepcion: " + e.getMessage());
        }
    }

    public String getCorreo() {
        return this.auxCorreo;
    }

    public void setCorreo(String Correo) {
        this.auxCorreo = Correo;
    }

}