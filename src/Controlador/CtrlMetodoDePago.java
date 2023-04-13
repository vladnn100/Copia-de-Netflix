package Controlador;

import Modelo.ArregloDeCuentas;
import Vista.FrmMetodoDePago;
import Vista.FrmTarjeta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrlMetodoDePago {

    private ArregloDeCuentas modelo;
    private FrmMetodoDePago vista;
    private String auxCorreo;

    public CtrlMetodoDePago(ArregloDeCuentas modelo, FrmMetodoDePago vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.auxCorreo = getCorreo();

        this.vista.btnTarjeta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vista.dispose();
                FrmTarjeta frmTarjeta = new FrmTarjeta();
                CtrlTarjeta ctrlTarjea = new CtrlTarjeta(modelo, frmTarjeta);
                ctrlTarjea.setCorreo(auxCorreo);
                ctrlTarjea.init();
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
