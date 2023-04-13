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
import Modelo.Usuario;
import Vista.FrmAdministrarCuenta;
import Vista.FrmCambiarTelefono;
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
public class CtrlCambiarTelefono {

    private FrmCambiarTelefono vista;
    private ArregloDeCuentas modelo;
    private String auxCorreo;

    public CtrlCambiarTelefono(FrmCambiarTelefono vista, ArregloDeCuentas modelo) {
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
        this.vista.btnCambiarNumero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String newph = vista.txtNuevoNumero.getText().trim();
                String contra = vista.txtpass.getText().trim();
                try {
                    if (modelo.cuentaActiva(auxCorreo).getUsuario().getContraseña().equals(contra)) {
                        modelo.cuentaActiva(auxCorreo).getUsuario().setTelefono(newph);
                        JOptionPane.showMessageDialog(vista, "¡Número de telefono cambiado con éxito!");
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

                /*
                else {
                    JOptionPane.showMessageDialog(vista, "Contraseña incorrecta, intentelo nuevamente.");
                }
                 */
            }
        });

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
