/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Archivo;
import Modelo.ArregloDeCuentas;
import Modelo.CustomException;
import Modelo.Membresia;
import Vista.FrmAdministrarCuenta;
import Vista.FrmInfoFacturacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Julian
 */
public class CtrlInformacionDeFacturacion {

    private FrmInfoFacturacion vista;
    private ArregloDeCuentas modelo;
    private String auxCorreo;

    public CtrlInformacionDeFacturacion(FrmInfoFacturacion vista, ArregloDeCuentas modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.auxCorreo = getCorreo();
        //PERSONALIZANDO EL CIERRE DEL PROGRAMA//
        vista.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        vista.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                JFrame frame = (JFrame) e.getSource();

                try {
                    modelo.cuentaActiva(auxCorreo).getUsuario().disminuirDispositivo();
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
        this.vista.btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.dispose();
                FrmAdministrarCuenta fAdministrarCuenta = new FrmAdministrarCuenta();
                CtrlAdministrarCuenta cAdministrarCuenta = new CtrlAdministrarCuenta(modelo, fAdministrarCuenta);
                cAdministrarCuenta.setCorreo(auxCorreo);
                cAdministrarCuenta.init();
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
        try {
            Membresia membresia = modelo.cuentaActiva(auxCorreo).getSuscripcion().getMembresia();
            DateTimeFormatter fNew = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate FechaPagos = modelo.cuentaActiva(auxCorreo).getSuscripcion().getFechaRegistro().plusMonths(1);
            this.vista.lblPrecio.setText(" por S/." + String.valueOf(membresia.getPrecio()));
            this.vista.lblPlan.setText(membresia.getDescripcionMembresia());
            this.vista.lblPantallas.setText("por defecto " + modelo.cuentaActiva(auxCorreo).getSuscripcion().getMembresia().getPantallas() + " pantallas");
            this.vista.lblProximaFactura.setText(fNew.format(FechaPagos));
        } catch (CustomException e) {
            System.out.println("EXCEPCION: " + e.getMessage());
        }

    }

    public String getCorreo() {
        return this.auxCorreo;
    }

    public void setCorreo(String Correo) {
        this.auxCorreo = Correo;

    }
}
