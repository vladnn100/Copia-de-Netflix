package Controlador;

import Modelo.ArregloDeCuentas;
import Vista.FrmSolicitudReestablecerContrasena;

public class CtrlSolicitudReestablecerContra {

    private ArregloDeCuentas modelo;
    private FrmSolicitudReestablecerContrasena vista;

    public CtrlSolicitudReestablecerContra(ArregloDeCuentas modelo, FrmSolicitudReestablecerContrasena vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void init() {
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
    }
}
