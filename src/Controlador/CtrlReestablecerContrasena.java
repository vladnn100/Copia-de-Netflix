package Controlador;

import App.Main;
import Modelo.ArregloDeCuentas;
import Modelo.CustomException;

import Vista.FrmReestablecerContrasena;
import Vista.FrmInicio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CtrlReestablecerContrasena {

    private ArregloDeCuentas modelo;
    private FrmReestablecerContrasena vista;
    private String auxCorreo;

    public CtrlReestablecerContrasena(ArregloDeCuentas modelo, FrmReestablecerContrasena vista) {
        this.modelo = modelo;
        this.vista = vista;

        this.vista.btnFinalizarEdicion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String correo = vista.txtCorreo.getText();
                String contraseña = vista.txtNuevaContra1.getText().trim();
                String contraseña2 = vista.txtNuevaContra2.getText().trim();

                if (contraseña.isEmpty() || contraseña2.isEmpty()) {
                    JOptionPane.showMessageDialog(vista, "Ingrese una contraseña correcta", "Reestablecer contraseña", 0);
                    vista.txtNuevaContra1.requestFocus();

                    return;
                }

                if (!contraseña.equals(contraseña2)) {
                    JOptionPane.showMessageDialog(vista, "¡Las contraseñas no coinciden!", "Reestablecer contraseña", 0);
                    vista.txtNuevaContra1.requestFocus();
                    vista.txtNuevaContra1.setSelectionStart(0);
                    vista.txtNuevaContra1.setSelectionEnd(vista.txtNuevaContra1.getText().length());

                    return;
                }
                try {
                    modelo.cuentaActiva(auxCorreo).getUsuario().setContraseña(contraseña);

                    JOptionPane.showMessageDialog(vista, "!Contraseña reestablecida con éxito!", "Reestablecer contraseña", 1);

                    vista.dispose();

                    FrmInicio fInicio = new FrmInicio();
                    fInicio.txtUsuario.setText(correo);
                    CtrlInicio ctrlInicio = new CtrlInicio(modelo, fInicio);

                    ctrlInicio.init();
                } catch (CustomException e) {
                    System.out.println("EXCEPCION: " + e.getMessage());
                }

            }
        });

        this.vista.btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vista.dispose();

                FrmInicio fInicio = new FrmInicio();
                CtrlInicio ctrlInicio = new CtrlInicio(modelo, fInicio);

                ctrlInicio.init();
            }
        });
    }

    public void init() {
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
