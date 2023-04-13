package Controlador;

import Modelo.ArregloDeCuentas;

import Modelo.Membresia;

import Vista.FrmMetodoDePago;
import Vista.FrmPaquetes;
import Vista.FrmRegistro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrlPaquetes {

    private ArregloDeCuentas modelo;
    private FrmPaquetes vista;

    public CtrlPaquetes(FrmPaquetes vista, ArregloDeCuentas modelo) {
        this.modelo = modelo;
        this.vista = vista;

        this.vista.btnBasico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vista.dispose();

                FrmMetodoDePago fMetodoPago = new FrmMetodoDePago();
                fMetodoPago.correo = vista.correo;
                fMetodoPago.contrasena = vista.contrasena;
                fMetodoPago.membresia = Membresia.BASIC;

                CtrlMetodoDePago controlador = new CtrlMetodoDePago(modelo, fMetodoPago);

                controlador.init();
            }
        });

        this.vista.btnStandard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vista.dispose();

                FrmMetodoDePago fMetodoPago = new FrmMetodoDePago();
                fMetodoPago.correo = vista.correo;
                fMetodoPago.contrasena = vista.contrasena;
                fMetodoPago.membresia = Membresia.STANDARD;

                CtrlMetodoDePago controlador = new CtrlMetodoDePago(modelo, fMetodoPago);

                controlador.init();
            }
        });

        this.vista.btnPremium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vista.dispose();

                FrmMetodoDePago fMetodoPago = new FrmMetodoDePago();
                fMetodoPago.correo = vista.correo;
                fMetodoPago.contrasena = vista.contrasena;
                fMetodoPago.membresia = Membresia.PREMIUM;

                CtrlMetodoDePago controlador = new CtrlMetodoDePago(modelo, fMetodoPago);

                controlador.init();
            }
        });

        this.vista.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vista.dispose();

                FrmRegistro fRegistro = new FrmRegistro();
                fRegistro.txtCorreo.setText(vista.correo);
                fRegistro.txtCorreo.setSelectionStart(0);
                fRegistro.txtCorreo.setSelectionEnd(fRegistro.txtCorreo.getText().length());

                CtrlRegistro controlador = new CtrlRegistro(modelo, fRegistro);

                controlador.init();
            }
        });

    }

    public void init() {
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
    }
}
