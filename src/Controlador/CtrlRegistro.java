package Controlador;

import Modelo.ArregloDeCuentas;
import Modelo.Cuenta;
import Modelo.Usuario;
import Vista.FrmInicio;
import Vista.FrmPlanes;
import Vista.FrmRegistro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CtrlRegistro {

    private ArregloDeCuentas modelo;
    private FrmRegistro vista;

    public CtrlRegistro(ArregloDeCuentas modelo, FrmRegistro vista) {
        this.modelo = modelo;
        this.vista = vista;

        this.vista.btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                String correo = vista.txtCorreo.getText();
                String contra = vista.txtContra1.getText();
                String contra2 = vista.txtContra2.getText();
                String telefono = vista.txtTelefono.getText();

                if (!contra.equals(contra2) || !contra2.equals(contra)) {
                    JOptionPane.showMessageDialog(vista, "LAS CONTRASEÑAS SON DISTINTAS");
                    return;
                }
                if (vista.txtCorreo.getText().isEmpty() || vista.txtContra1.getText().isEmpty() || vista.txtContra2.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(vista, "ALGUNO DE LOS CAMPOS SE ENCUENTRA VACÍO");
                    return;
                }

                System.out.println(correo.contains("@"));
                if (!correo.contains("@") || !(correo.endsWith(".com") || !correo.endsWith(".edu.pe")) || modelo.validarPorCorreo1(correo)) {
                    JOptionPane.showMessageDialog(vista, "El correo ingresado es incorrecto o se encuentra registrado con anterioridad!", "Registro", 0);
                    vista.txtCorreo.requestFocus();
                    vista.txtCorreo.setSelectionStart(0);
                    vista.txtCorreo.setSelectionEnd(vista.txtCorreo.getText().length());
                    return;
                }

                Cuenta cuentaAux = new Cuenta();
                cuentaAux.setUsuario(new Usuario(correo, contra, telefono));
                modelo.agregarCuenta(cuentaAux);

                modelo.cambiarEstadoSesion(correo);
                vista.dispose();
                FrmPlanes frmPlanes = new FrmPlanes();
                CtrlPlanes ctrlPlanes = new CtrlPlanes(modelo, frmPlanes);
                ctrlPlanes.setCorreo(correo);
                ctrlPlanes.init();
            }
        });

        this.vista.btnSalir.addActionListener(new ActionListener() {
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
}
