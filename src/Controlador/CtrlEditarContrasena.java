package Controlador;

import Modelo.Archivo;
import Modelo.ArregloDeCuentas;
import Modelo.CustomException;
import Modelo.Usuario;

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
 * @author Julian
 */
public class CtrlEditarContrasena {

    private ArregloDeCuentas modelo;
    private FrmEditarContrasena vista;
    private String auxCorreo;

    public CtrlEditarContrasena(FrmEditarContrasena vista, ArregloDeCuentas modelo) {
        this.vista = vista;
        this.modelo = modelo;
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
        this.vista.btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String contrasenaActual = vista.txtContrasena.getText().trim();
                String contrasena1 = vista.txtNewContrasena.getText().trim();
                String contrasena2 = vista.txtConfirmContrasena.getText().trim();
                try {
                    Usuario usuario = modelo.cuentaActiva(auxCorreo).getUsuario();

                    if (!contrasena1.equals(contrasena2)) {
                        JOptionPane.showMessageDialog(vista, "Las contraseñas no coinciden. Intente nuevamente", "Editar contraseña", JOptionPane.ERROR_MESSAGE);
                    } else if (!usuario.getContraseña().equals(contrasenaActual)) {
                        JOptionPane.showMessageDialog(vista, "Las contraseña que ha digitado no coincide con su contraseña actual. Ingresela de nuevo", "Editar contraseña", JOptionPane.ERROR_MESSAGE);
                    } else if (usuario.getContraseña().equals(contrasena1) || usuario.getContraseña().equals(contrasena2)) {
                        JOptionPane.showMessageDialog(vista, "La contraseña nueva debe ser diferente a la actual.", "Editar contraseña", JOptionPane.ERROR_MESSAGE);
                    } else {
                        usuario.setContraseña(contrasena2);
                        JOptionPane.showMessageDialog(vista, "¡La contraseña ha sido modificada con éxito!", "Editar contraseña", JOptionPane.INFORMATION_MESSAGE);

                        vista.dispose();
                        FrmAdministrarCuenta fradmin = new FrmAdministrarCuenta();
                        CtrlAdministrarCuenta control = new CtrlAdministrarCuenta(modelo, fradmin);
                        control.init();
                    }
                    //GUARDANDO LOS CAMBIOS EN EL ARCHIVO//
                    Archivo archivo = new Archivo();
                    archivo.serializar(Archivo.archivoArregloCuentas, modelo);
                    // // // // // // // // // // // // // // 
                } catch (CustomException e) {
                    System.out.println("EXCEPCION: " + e.getMessage());
                }finally {
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

                ctrlAdmin.init();
            }
        });
    }

    public void init() {
        //ACTUALIZANDO DATOS//
        Archivo archivo = new Archivo();
        modelo = (ArregloDeCuentas) archivo.deserializar(Archivo.archivoArregloCuentas);
        ///////////////////
        this.vista.setLocationRelativeTo(null);
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
