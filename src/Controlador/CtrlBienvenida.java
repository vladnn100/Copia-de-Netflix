/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ArregloDeCuentas;
import Vista.FrmBienvenida;
import Vista.FrmInicio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Julian
 */
public class CtrlBienvenida {

    private FrmBienvenida vista;
    private ArregloDeCuentas modelo;

    public CtrlBienvenida(FrmBienvenida vista) {
        this.vista = vista;
    }

    Timer timer = new Timer(1000, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent ae) {

            vista.dispose();
            System.out.println("PROBANDO SIRVE TIMER");
            FrmInicio frmInicio = new FrmInicio();
            CtrlInicio ctrlInicio = new CtrlInicio(modelo, frmInicio);
            ctrlInicio.init();
        }

    });

    public void init() {
        timer.start();
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);

        timer.stop();
    }
}
