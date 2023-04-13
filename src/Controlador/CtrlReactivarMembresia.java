package Controlador;

import Modelo.Archivo;
import Modelo.ArregloDeCuentas;
import Modelo.CustomException;
import Modelo.Membresia;
import Vista.FrmInicio;
import Vista.FrmPaquetes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrlReactivarMembresia {

    private ArregloDeCuentas modelo;
    private FrmPaquetes vista;
    private String auxCorreo;

    public CtrlReactivarMembresia(FrmPaquetes vista, ArregloDeCuentas modelo) {
        this.modelo = modelo;
        this.vista = vista;
        this.auxCorreo = getCorreo();

        this.vista.btnBasico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Archivo archivo = new Archivo();
                    //Asignación del tipo de membresia   
                    modelo.cuentaActiva(auxCorreo).getSuscripcion().setMembresia(Membresia.BASIC);

                    //Cambiando estado de la membresia
                    modelo.cuentaActiva(auxCorreo).getSuscripcion().setEstadoMembresia(Boolean.TRUE);

                    //Convirtiendo a usuario inactivo
                    modelo.cambiarEstadoSesion(modelo.cuentaActiva(auxCorreo).getUsuario().getCorreo());
                    archivo.serializar(Archivo.archivoArregloCuentas, modelo);
                    vista.dispose();
                    FrmInicio fInicio = new FrmInicio();
                    CtrlInicio controlador = new CtrlInicio(modelo, fInicio);
                    controlador.init();
                } catch (CustomException e) {
                    System.out.println("EXCEPCION: " + e.getMessage());
                }

            }
        });

        this.vista.btnStandard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Archivo archivo = new Archivo();
                    //Asignación del tipo de membresia
                    modelo.cuentaActiva(auxCorreo).getSuscripcion().setMembresia(Membresia.STANDARD);

                    //Cambiando estado de la membresia
                    modelo.cuentaActiva(auxCorreo).getSuscripcion().setEstadoMembresia(Boolean.TRUE);

                    //Convirtiendo a usuario inactivo
                    modelo.cambiarEstadoSesion(modelo.cuentaActiva(auxCorreo).getUsuario().getCorreo());
                    archivo.serializar(Archivo.archivoArregloCuentas, modelo);
                    vista.dispose();
                    FrmInicio fInicio = new FrmInicio();
                    CtrlInicio controlador = new CtrlInicio(modelo, fInicio);
                    controlador.init();
                } catch (CustomException e) {
                    System.out.println("EXCEPCION: " + e.getMessage());
                }
            }
        });

        this.vista.btnPremium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Archivo archivo = new Archivo();
                    //Asignación del tipo de membresia
                    modelo.cuentaActiva(auxCorreo).getSuscripcion().setMembresia(Membresia.PREMIUM);

                    //Cambiando estado de la membresia
                    modelo.cuentaActiva(auxCorreo).getSuscripcion().setEstadoMembresia(Boolean.TRUE);

                    //Convirtiendo a usuario inactivo
                    modelo.cambiarEstadoSesion(modelo.cuentaActiva(auxCorreo).getUsuario().getCorreo());
                    archivo.serializar(Archivo.archivoArregloCuentas, modelo);
                    vista.dispose();
                    FrmInicio fInicio = new FrmInicio();
                    CtrlInicio controlador = new CtrlInicio(modelo, fInicio);
                    controlador.init();
                } catch (CustomException e) {
                    System.out.println("EXCEPCION: " + e.getMessage());
                }

            }
        });

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

    public String getCorreo() {
        return this.auxCorreo;
    }

    public void setCorreo(String Correo) {
        this.auxCorreo = Correo;

    }
}
