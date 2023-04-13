/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Archivo;
import Modelo.ArregloDeCuentas;
import Modelo.Cuenta;
import Modelo.CustomException;
import Vista.FrmAdministrarCuenta;
import Vista.FrmEditarContrasena;
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
public class CtrlCambiarContraseña {

    private FrmEditarContrasena vista;
    private ArregloDeCuentas modelo;
    private String auxCorreo;

    public CtrlCambiarContraseña(FrmEditarContrasena vista, ArregloDeCuentas modelo) {
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
        //BOTÓN GUARDAR//
        this.vista.btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String contrasenaActual = vista.txtContrasena.getText().trim();
                String contrasena1 = vista.txtNewContrasena.getText().trim();
                String contrasena2 = vista.txtConfirmContrasena.getText().trim();

                try {
                    Cuenta cuenta = modelo.cuentaActiva(auxCorreo);
                    if (!contrasena1.equals(contrasena2)) {
                        JOptionPane.showMessageDialog(vista, "Las contraseñas no coinciden. Intente nuevamente", "Editar contraseña", JOptionPane.ERROR_MESSAGE);
                    } else if (!cuenta.getUsuario().getContraseña().equals(contrasenaActual)) {
                        JOptionPane.showMessageDialog(vista, "Las contraseña que ha digitado no coincide con su contraseña actual. Ingresela de nuevo", "Editar contraseña", JOptionPane.ERROR_MESSAGE);
                    } else if (cuenta.getUsuario().getContraseña().equals(contrasena1) || cuenta.getUsuario().getContraseña().equals(contrasena2)) {
                        JOptionPane.showMessageDialog(vista, "La contraseña nueva debe ser diferente a la actual.", "Editar contraseña", JOptionPane.ERROR_MESSAGE);
                    } else {
                        cuenta.getUsuario().setContraseña(contrasena2);
                        JOptionPane.showMessageDialog(vista, "¡La contraseña ha sido modificada con éxito!", "Editar contraseña", JOptionPane.INFORMATION_MESSAGE);

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

                } catch (Exception e) {
                    System.out.println("EXCEPCION: " + e.getMessage());
                }
            }
        });

        //BOTÓN CANCELAR//
        this.vista.btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vista.dispose();

                FrmAdministrarCuenta fAdmin = new FrmAdministrarCuenta();
                CtrlAdministrarCuenta ctrlAdmin = new CtrlAdministrarCuenta(modelo, fAdmin);
                ctrlAdmin.setCorreo(auxCorreo);
                ctrlAdmin.init();
            }

        });
    }

    public void init() {
        //ACTUALIZANDO DATOS//
        Archivo archivo = new Archivo();
        modelo = (ArregloDeCuentas) archivo.deserializar(Archivo.archivoArregloCuentas);
        ///////////////////
        System.out.println("El correo de esta cuenta es : " + auxCorreo);;
        this.vista.setVisible(true);

        this.vista.txtContrasena.requestFocus();
    }

    public String getCorreo() {
        return this.auxCorreo;
    }

    public void setCorreo(String Correo) {
        this.auxCorreo = Correo;

    }
}
