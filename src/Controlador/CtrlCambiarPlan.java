/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Archivo;
import Modelo.ArregloDeCuentas;
import Modelo.CustomException;
import Modelo.Membresia;
import Modelo.Suscripcion;
import Vista.FrmAdministrarCuenta;
import Vista.FrmCambiarPlan;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Julian
 */
public class CtrlCambiarPlan {

    private FrmCambiarPlan vista;
    private ArregloDeCuentas modelo;
    private String auxCorreo;

    public CtrlCambiarPlan(FrmCambiarPlan vista, ArregloDeCuentas modelo) {
        this.vista = vista;
        this.modelo = modelo;
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
        this.vista.btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vista.dispose();

                FrmAdministrarCuenta fAdmin = new FrmAdministrarCuenta();
                CtrlAdministrarCuenta controlador = new CtrlAdministrarCuenta(modelo, fAdmin);
                controlador.setCorreo(auxCorreo);
                controlador.init();
            }
        });
        this.vista.btnBasico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Suscripcion suscripcion = modelo.cuentaActiva(auxCorreo).getSuscripcion();

                    int input = JOptionPane.showConfirmDialog(null, "¿Está seguro de cambiar el plan?", "Seleccione una opción...",
                            JOptionPane.YES_NO_CANCEL_OPTION);
                    if (input == 0) {
                        suscripcion.setMembresia(Membresia.BASIC);
                        System.out.println(input);
                        vista.dispose();
                        //GUARDANDO LOS CAMBIOS EN EL ARCHIVO//
                        Archivo archivo = new Archivo();
                        archivo.serializar(Archivo.archivoArregloCuentas, modelo);
                        // // // // // // // // // // // // // // 
                        FrmAdministrarCuenta fradmin = new FrmAdministrarCuenta();
                        CtrlAdministrarCuenta control = new CtrlAdministrarCuenta(modelo, fradmin);
                        control.setCorreo(auxCorreo);
                        control.init();
                    } else if (input == 1) {
                        System.out.println(input);
                    } else if (input == 2) {
                        System.out.println(input);
                        vista.dispose();
                        //GUARDANDO LOS CAMBIOS EN EL ARCHIVO//
                        Archivo archivo = new Archivo();
                        archivo.serializar(Archivo.archivoArregloCuentas, modelo);
                        // // // // // // // // // // // // // // 
                        FrmAdministrarCuenta fradmin = new FrmAdministrarCuenta();
                        CtrlAdministrarCuenta control = new CtrlAdministrarCuenta(modelo, fradmin);
                        control.setCorreo(auxCorreo);
                        control.init();
                    }

                } catch (CustomException e) {
                    System.out.println("EXCEPCION: " + e.getMessage());
                }

            }
        });

        this.vista.btnEstandar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Suscripcion suscripcion = modelo.cuentaActiva(auxCorreo).getSuscripcion();
                    int input = JOptionPane.showConfirmDialog(null, "¿Está seguro de cambiar el plan?", "Seleccione una opción...",
                            JOptionPane.YES_NO_CANCEL_OPTION);
                    if (input == 0) {
                        suscripcion.setMembresia(Membresia.STANDARD);
                        System.out.println(input);
                        vista.dispose();
                        //GUARDANDO LOS CAMBIOS EN EL ARCHIVO//
                        Archivo archivo = new Archivo();
                        archivo.serializar(Archivo.archivoArregloCuentas, modelo);
                        // // // // // // // // // // // // // //
                        FrmAdministrarCuenta fradmin = new FrmAdministrarCuenta();
                        CtrlAdministrarCuenta control = new CtrlAdministrarCuenta(modelo, fradmin);
                        control.setCorreo(auxCorreo);
                        control.init();
                    } else if (input == 1) {
                        System.out.println(input);
                    } else if (input == 2) {
                        System.out.println(input);
                        vista.dispose();
                        //GUARDANDO LOS CAMBIOS EN EL ARCHIVO//
                        Archivo archivo = new Archivo();
                        archivo.serializar(Archivo.archivoArregloCuentas, modelo);
                        // // // // // // // // // // // // // //
                        FrmAdministrarCuenta fradmin = new FrmAdministrarCuenta();
                        CtrlAdministrarCuenta control = new CtrlAdministrarCuenta(modelo, fradmin);
                        control.setCorreo(auxCorreo);
                        control.init();
                    }

                } catch (CustomException e) {
                    System.out.println("EXCEPCION: " + e.getMessage());
                }
            }
        });

        this.vista.btnPremium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Suscripcion suscripcion = modelo.cuentaActiva(auxCorreo).getSuscripcion();
                    int input = JOptionPane.showConfirmDialog(null, "¿Está seguro de cambiar el plan?", "Seleccione una opción...",
                            JOptionPane.YES_NO_CANCEL_OPTION);
                    if (input == 0) {
                        suscripcion.setMembresia(Membresia.PREMIUM);
                        System.out.println(input);
                        vista.dispose();
                        //GUARDANDO LOS CAMBIOS EN EL ARCHIVO//
                        Archivo archivo = new Archivo();
                        archivo.serializar(Archivo.archivoArregloCuentas, modelo);
                        // // // // // // // // // // // // // // 
                        FrmAdministrarCuenta fradmin = new FrmAdministrarCuenta();
                        CtrlAdministrarCuenta control = new CtrlAdministrarCuenta(modelo, fradmin);
                        control.setCorreo(auxCorreo);
                        control.init();
                    } else if (input == 1) {
                        System.out.println(input);
                    } else if (input == 2) {
                        System.out.println(input);
                        vista.dispose();
                        //GUARDANDO LOS CAMBIOS EN EL ARCHIVO//
                        Archivo archivo = new Archivo();
                        archivo.serializar(Archivo.archivoArregloCuentas, modelo);
                        // // // // // // // // // // // // // // 
                        FrmAdministrarCuenta fradmin = new FrmAdministrarCuenta();
                        CtrlAdministrarCuenta control = new CtrlAdministrarCuenta(modelo, fradmin);
                        control.setCorreo(auxCorreo);
                        control.init();
                    }

                } catch (CustomException e) {
                    System.out.println("EXCEPCION: " + e.getMessage());
                }

            }
        });

    }

    public void init() {
        //ACTUALIZANDO DATOS//
        Archivo archivo = new Archivo();
        modelo = (ArregloDeCuentas) archivo.deserializar(Archivo.archivoArregloCuentas);
        ///////////////////
        System.out.println("El correo de esta cuenta es : " + auxCorreo);
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
    }

    public String getCorreo() {
        return this.auxCorreo;
    }

    public void setCorreo(String Correo) {
        this.auxCorreo = Correo;

    }
}
