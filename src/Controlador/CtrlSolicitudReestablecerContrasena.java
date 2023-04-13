package Controlador;

import Modelo.ArregloDeCuentas;
import Modelo.CustomException;

import Vista.FrmSolicitudReestablecerContrasena;
import Vista.FrmInicio;
import Vista.FrmReestablecerContrasena;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

public class CtrlSolicitudReestablecerContrasena {

    private ArregloDeCuentas modelo;
    private FrmSolicitudReestablecerContrasena vista;

    public CtrlSolicitudReestablecerContrasena(FrmSolicitudReestablecerContrasena vista, ArregloDeCuentas modelo) {
        this.modelo = modelo;
        this.vista = vista;

        Action accion;
        accion = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String correo = vista.txtCorreoPorEditar.getText().trim();

                if (correo.isEmpty()) {
                    JOptionPane.showMessageDialog(vista, "Ingresa un correo válido para continuar con la recuperación de tu cuenta", "Recuperar contraseña", 0);
                    vista.txtCorreoPorEditar.requestFocus();
                    return;
                }

                /*
                if(modelo.validarPorCorreo(correo)){
                JOptionPane.showMessageDialog(vista, "El usuario específicado no se encuentra registrado", "Recuperar contraseña", 0);
                vista.txtCorreoPorEditar.requestFocus();
                vista.txtCorreoPorEditar.setSelectionStart(0);
                vista.txtCorreoPorEditar.setSelectionEnd(vista.txtCorreoPorEditar.getText().length());
                
                return;
                }
                 */
 /*==================================
                AQUÍ TIENE QUE ENVIAR UN CORREO XD
                ====================================*/
                try {
                    modelo.validarPorCorreo(correo);
                } catch (CustomException e) {
                    System.out.println("Excepción: " + e.getMessage());
                    JOptionPane.showMessageDialog(vista, e.getMessage());
                }

                JOptionPane.showMessageDialog(vista, "Hemos enviado un mensaje a tu correo, revisa tu bandeja de entrada y confirma tu indentidad", "Recuperar contraseña", 1);

                vista.dispose();

                FrmReestablecerContrasena fReestablecerContrasena = new FrmReestablecerContrasena();
                fReestablecerContrasena.txtCorreo.setText(correo);

                CtrlReestablecerContrasena ctrlEdtContrasena = new CtrlReestablecerContrasena(modelo, fReestablecerContrasena);

                ctrlEdtContrasena.init();
            }
        };

        this.vista.txtCorreoPorEditar.addActionListener(accion);
        this.vista.btnRecuperar.addActionListener(accion);

        this.vista.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vista.dispose();

                FrmInicio fInicio = new FrmInicio();
                CtrlInicio controlador = new CtrlInicio(modelo, fInicio);

                controlador.init();
            }
        });
    }

    public void init() {
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
    }
}
