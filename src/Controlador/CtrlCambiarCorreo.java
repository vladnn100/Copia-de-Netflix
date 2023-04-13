/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Archivo;
import Modelo.ArregloDeCuentas;
import Modelo.CustomException;
import Vista.FrmAdministrarCuenta;
import Vista.FrmCambiarCorreo;
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
public class CtrlCambiarCorreo {

    private ArregloDeCuentas modelo;
    private FrmCambiarCorreo vista;
    private String auxCorreo;

    public CtrlCambiarCorreo(ArregloDeCuentas modelo, FrmCambiarCorreo vista) {
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
                    vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        this.vista.btnFinalizarEdicion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    modelo.validarInicioSesion(vista.txtCorreoActual.getText(), vista.txtContrasena.getText());
                    modelo.editarCorreo(vista.txtCorreoActual.getText(), vista.txtCorreoNuevo.getText());
                    setCorreo(vista.txtCorreoNuevo.getText());
                    JOptionPane.showMessageDialog(vista, "¡Se modificó el correo con éxito!", "Modificar correo", 1);
                    //GUARDANDO LOS CAMBIOS EN EL ARCHIVO//
                    Archivo archivo = new Archivo();
                    archivo.serializar(Archivo.archivoArregloCuentas, modelo);
                    // // // // // // // // // // // // // // 

                    vista.dispose();
                    FrmAdministrarCuenta fAdmin = new FrmAdministrarCuenta();
                    CtrlAdministrarCuenta ctrlAdmin = new CtrlAdministrarCuenta(modelo, fAdmin);
                    ctrlAdmin.setCorreo(vista.txtCorreoNuevo.getText());
                    ctrlAdmin.init();
                } catch (Exception e) {
                    System.out.println("Excepción: " + e.getMessage());
                    JOptionPane.showMessageDialog(vista, e.getMessage());
                } finally {
                    vista.txtContrasena.setSelectionStart(0);
                    vista.txtContrasena.setSelectionEnd(vista.txtContrasena.getText().length());
                    return;
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

    public void init() throws CustomException {
        try {
            //ACTUALIZANDO DATOS//
            Archivo archivo = new Archivo();
            modelo = (ArregloDeCuentas) archivo.deserializar(Archivo.archivoArregloCuentas);
            ///////////////////
            System.out.println("El correo de esta cuenta es : " + auxCorreo);
            this.vista.setLocationRelativeTo(null);
            this.vista.setVisible(true);
            this.vista.txtCorreoActual.setText(modelo.cuentaActiva(auxCorreo).getUsuario().getCorreo());
            this.vista.txtCorreoNuevo.setEditable(true);
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getCorreo() {
        return this.auxCorreo;
    }

    public void setCorreo(String Correo) {
        this.auxCorreo = Correo;

    }
}
