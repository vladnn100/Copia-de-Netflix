package Controlador;

import Modelo.ArregloDeCuentas;
import Modelo.Membresia;
import Modelo.Suscripcion;
import Vista.FrmMetodoDePago;
import Vista.FrmPlanes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrlPlanes {

    private ArregloDeCuentas modelo;
    private FrmPlanes vista;
    private String auxCorreo;

    public CtrlPlanes(ArregloDeCuentas modelo, FrmPlanes vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.auxCorreo = getCorreo();
        this.vista.btnBasico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    vista.dispose();
                    modelo.cuentaActiva(auxCorreo).setSuscripcion(new Suscripcion(Membresia.BASIC));
                    FrmMetodoDePago frmMetodoPago = new FrmMetodoDePago();
                    CtrlMetodoDePago ctrlMetodoPago = new CtrlMetodoDePago(modelo, frmMetodoPago);
                    ctrlMetodoPago.setCorreo(auxCorreo);
                    ctrlMetodoPago.init();
                } catch (Exception e) {
                    System.out.println("EXCEPCION: " + e.getMessage());
                }
            }
        });

        this.vista.btnStandard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                try {
                    modelo.cuentaActiva(auxCorreo).setSuscripcion(new Suscripcion(Membresia.STANDARD));
                    vista.dispose();
                    System.out.println(modelo.cuentaActiva(auxCorreo).getUsuario().getCorreo());
                    FrmMetodoDePago frmMetodoPago = new FrmMetodoDePago();
                    CtrlMetodoDePago ctrlMetodoPago = new CtrlMetodoDePago(modelo, frmMetodoPago);
                    ctrlMetodoPago.setCorreo(auxCorreo);
                    ctrlMetodoPago.init();
                } catch (Exception e) {
                    System.out.println("EXCEPCION: " + e.getMessage());
                }
            }
        });

        this.vista.btnPremium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                try {
                    modelo.cuentaActiva(auxCorreo).setSuscripcion(new Suscripcion(Membresia.PREMIUM));
                    vista.dispose();
                    FrmMetodoDePago frmMetodoPago = new FrmMetodoDePago();
                    CtrlMetodoDePago ctrlMetodoPago = new CtrlMetodoDePago(modelo, frmMetodoPago);
                    ctrlMetodoPago.setCorreo(auxCorreo);
                    ctrlMetodoPago.init();
                } catch (Exception e) {
                    System.out.println("EXCEPCION: " + e.getMessage());
                }

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
