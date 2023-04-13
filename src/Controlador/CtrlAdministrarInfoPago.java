/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Archivo;
import Modelo.ArregloDeCuentas;
import Modelo.CustomException;
import Modelo.Tarjeta;
import Vista.FrmAdministrarCuenta;
import Vista.FrmAdministrarInfoPago;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 *
 * @author Julian
 */
public class CtrlAdministrarInfoPago {

    private ArregloDeCuentas modelo;
    private FrmAdministrarInfoPago vista;
    private String auxCorreo;

    public CtrlAdministrarInfoPago(ArregloDeCuentas modelo, FrmAdministrarInfoPago vista) {
        this.modelo = modelo;
        this.vista = vista;
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
        this.vista.btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                String nombre = vista.txtNombre.getText();
                String apellido = vista.txtApellido.getText();
                String nroTarjeta = vista.txtNroTarjeta.getText().replaceAll("-", "");
                String fechaVencimiento = vista.txtFechaVencimiento.getText();
                Integer cvv = Integer.parseInt(vista.txtCVV.getText().trim());

                Tarjeta auxTarjeta = new Tarjeta(nombre, apellido, nroTarjeta, fechaVencimiento, cvv);

                if (nombre.isEmpty() || apellido.isEmpty() || fechaVencimiento.isEmpty()) {
                    JOptionPane.showMessageDialog(vista, "!Complete todos los campos para validar su compra!", "Tarjeta", 0);
                    return;
                }

                try {
                    if (auxTarjeta.validar()) {

                        modelo.cuentaActiva(auxCorreo).setTarjeta(auxTarjeta);

                        //GUARDANDO LOS CAMBIOS EN EL ARCHIVO//
                        Archivo archivo = new Archivo();
                        archivo.serializar(Archivo.archivoArregloCuentas, modelo);
                        // // // // // // // // // // // // // //  
                        JOptionPane.showMessageDialog(vista, "Tu información de pago ha sido actualizada con éxito.");
                        vista.dispose();

                        FrmAdministrarCuenta fAdmin = new FrmAdministrarCuenta();
                        CtrlAdministrarCuenta ctrlAdmin = new CtrlAdministrarCuenta(modelo, fAdmin);
                        ctrlAdmin.setCorreo(auxCorreo);
                        ctrlAdmin.init();
                    } else {
                        JOptionPane.showMessageDialog(vista, "Debe ingresar una tarjeta válida.");
                        vista.txtNroTarjeta.setText("");
                        vista.txtCVV.setText("");
                    }
                } catch (CustomException e) {
                    System.out.println("EXCEPCION: " + e.getMessage());
                }
            }
        });

        this.vista.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vista.dispose();
                FrmAdministrarCuenta fAdminCuenta = new FrmAdministrarCuenta();
                CtrlAdministrarCuenta ctrlAdminCuenta = new CtrlAdministrarCuenta(modelo, fAdminCuenta);
                ctrlAdminCuenta.setCorreo(auxCorreo);
                ctrlAdminCuenta.init();
            }
        });

    }

    public void init() {
        //ACTUALIZANDO DATOS//
        Archivo archivo = new Archivo();
        modelo = (ArregloDeCuentas) archivo.deserializar(Archivo.archivoArregloCuentas);
        ///////////////////
        System.out.println("El correo de esta cuenta es : " + auxCorreo);
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
        try {
            String nombreAux = modelo.cuentaActiva(auxCorreo).getTarjeta().getNombre();
            String apellidoAux = modelo.cuentaActiva(auxCorreo).getTarjeta().getApellido();

            String fechaVencimientoAux = modelo.cuentaActiva(auxCorreo).getTarjeta().getFechaVencimiento();
            vista.txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
            vista.txtApellido.setHorizontalAlignment(SwingConstants.CENTER);
            vista.txtNombre.setText(nombreAux);
            vista.txtApellido.setText(apellidoAux);

            vista.txtFechaVencimiento.setText(fechaVencimientoAux);
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
