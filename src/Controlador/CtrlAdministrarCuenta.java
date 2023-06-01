package Controlador;

import Modelo.Archivo;
import Modelo.ArregloDeCuentas;
import Modelo.CustomException;
import Vista.FrmAdministrarCuenta;
import Vista.FrmAdministrarInfoPago;
import Vista.FrmCambiarCorreo;
import Vista.FrmCambiarDiaFacturacion;
import Vista.FrmCambiarPlan;
import Vista.FrmCambiarTelefono;
import Vista.FrmEditarContrasena;
import Vista.FrmInfoFacturacion;
import Vista.FrmInicio;
import Vista.FrmPerfiles;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CtrlAdministrarCuenta {

    private ArregloDeCuentas modelo;
    private FrmAdministrarCuenta vista;
    private String auxCorreo;

    public CtrlAdministrarCuenta(ArregloDeCuentas modelo, FrmAdministrarCuenta vista) {
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
        this.vista.btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vista.dispose();
                FrmPerfiles fPerfil = new FrmPerfiles();
                CtrlPerfiles ctrlPerfil = new CtrlPerfiles(modelo, fPerfil);
                ctrlPerfil.setCorreo(auxCorreo);
                ctrlPerfil.init();
            }
        });

        this.vista.btnCancelarMembresia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int rpta = JOptionPane.showConfirmDialog(null, "¿Seguro que desea anular su membresia?", "Anular membresia", JOptionPane.YES_NO_OPTION);
                if (rpta == JOptionPane.YES_OPTION) {

                    //Captamos el usuario que se encuentra en linea para tomar su estado de membresia
                    //System.out.println(modelo.cuentaActiva(auxCorreo).getSuscripcion().getEstadoMembresia());
                    JOptionPane.showMessageDialog(vista, "Suscripción anulada");
//                    FrmInicio fInicio = new FrmInicio();
//                    CtrlInicio ctrlInicio = new CtrlInicio(modelo, fInicio);
//                    vista.dispose();
//                    ctrlInicio.init();

                    //Cambio del estado de sesion del usuario activo
                    try {
                        modelo.cuentaActiva(auxCorreo).getSuscripcion().setEstadoMembresia(Boolean.FALSE);
                        //GUARDANDO LOS CAMBIOS EN EL ARCHIVO//
                        String CorreoActivo = modelo.cuentaActiva(auxCorreo).getUsuario().getCorreo();
                        modelo.inactivarSesión(modelo.cuentaActiva(auxCorreo));
                        modelo.cambiarEstadoSesion(CorreoActivo);
                        JOptionPane.showMessageDialog(vista, "Suscripción anulada\nESTADO SUSCRIPCION: " + modelo.cuentaActiva(auxCorreo).getSuscripcion().getEstadoMembresia());

                        Archivo archivo = new Archivo();
                        archivo.serializar(Archivo.archivoArregloCuentas, modelo);

                        modelo.cambiarEstadoSesion(modelo.cuentaActiva(auxCorreo).getUsuario().getCorreo());
                        FrmInicio fInicio = new FrmInicio();
                        CtrlInicio ctrlInicio = new CtrlInicio(modelo, fInicio);
                        vista.dispose();
                        ctrlInicio.init();

                    } catch (CustomException e) {
                        System.out.println("Excepción: " + e.getMessage());
                        JOptionPane.showMessageDialog(vista, e.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(vista, "No se cancelo la suscripción");
                }
            }
        });

        //CAMBIAR CORREO
        this.vista.txtCambiarCorreo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vista.dispose();

                FrmCambiarCorreo fCambiarEmail = new FrmCambiarCorreo();

                CtrlCambiarCorreo controlador = new CtrlCambiarCorreo(modelo, fCambiarEmail);
                controlador.setCorreo(auxCorreo);
                try {
                    controlador.init();
                } catch (CustomException ex) {
                    Logger.getLogger(CtrlAdministrarCuenta.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //CAMBIAR CONTRASEÑA
        this.vista.txrtCambiarContrasena.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vista.dispose();

                FrmEditarContrasena fEditPass = new FrmEditarContrasena();
                CtrlCambiarContrasena controlador = new CtrlCambiarContrasena(fEditPass, modelo);
                controlador.setCorreo(auxCorreo);
                controlador.init();
            }
        });

        //ADMINISTRAR INFO PAGO
        this.vista.btnAdminInfoPago.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vista.dispose();

                FrmAdministrarInfoPago fInfoPago = new FrmAdministrarInfoPago();
                CtrlAdministrarInfoPago ctrlInfoPago = new CtrlAdministrarInfoPago(modelo, fInfoPago);
                ctrlInfoPago.setCorreo(auxCorreo);
                ctrlInfoPago.init();

            }
        });

        // INFO PAGO
        this.vista.btnInfoFactu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vista.dispose();

                FrmInfoFacturacion fIFacturacion = new FrmInfoFacturacion();
                CtrlInformacionDeFacturacion cIFacturacion = new CtrlInformacionDeFacturacion(fIFacturacion, modelo);
                cIFacturacion.setCorreo(auxCorreo);
                cIFacturacion.init();

            }
        });

        //CAMBIAR PLAN
        this.vista.btnCambiarPlan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vista.dispose();

                FrmCambiarPlan fCambiarPlan = new FrmCambiarPlan();

                CtrlCambiarPlan controlador = new CtrlCambiarPlan(fCambiarPlan, modelo);
                controlador.setCorreo(auxCorreo);
                controlador.init();
            }
        });

        //CAMBIAR FECHA FACTURACION
        this.vista.btnCambiarFechaFact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vista.dispose();

                FrmCambiarDiaFacturacion framCambiarDiaFact = new FrmCambiarDiaFacturacion();
                CtrlCambiarDiaDeFacturacion ctrlCambiarDiaFact = new CtrlCambiarDiaDeFacturacion(framCambiarDiaFact, modelo);
                ctrlCambiarDiaFact.setCorreo(auxCorreo);
                ctrlCambiarDiaFact.init();
            }
        });

        //CAMBIAR TELEFONO
        this.vista.txtCambiarTelefono.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vista.dispose();
                FrmCambiarTelefono frmCamTel = new FrmCambiarTelefono();
                CtrlCambiarTelefono ctrlCambiarTel = new CtrlCambiarTelefono(frmCamTel, modelo);
                ctrlCambiarTel.setCorreo(auxCorreo);
                ctrlCambiarTel.init();
            }
        });

        //REFRESCAR PAGINA
        this.vista.btnVolver1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vista.dispose();
                FrmAdministrarCuenta fAdmin = new FrmAdministrarCuenta();
                CtrlAdministrarCuenta ctrlAdminCuenta = new CtrlAdministrarCuenta(modelo, fAdmin);
                ctrlAdminCuenta.setCorreo(auxCorreo);
                ctrlAdminCuenta.init();

            }
        });
    }

    public void init() {

        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
        try {

            //ACTUALIZANDO DATOS//
            Archivo archivo = new Archivo();
            modelo = (ArregloDeCuentas) archivo.deserializar(Archivo.archivoArregloCuentas);
            ///////////////////

            System.out.println("El correo de esta cuenta es : " + auxCorreo + "\n"
                    + "Numero de dispositivos activos: " + modelo.cuentaActiva(auxCorreo).getUsuario().getDispositivosConectados());
            this.vista.lblCorreo.setText(modelo.cuentaActiva(auxCorreo).getUsuario().getCorreo());
            this.vista.lblTelefono.setText("Teléfono: " + modelo.cuentaActiva(auxCorreo).getUsuario().getTelefono());

            this.vista.lblContrasena.setText(this.vista.lblContrasena.getText() + modelo.cuentaActiva(auxCorreo).getUsuario().getContraseñaOculta());

            //DEBE AGREGAR 30 o 31 días
//            this.vista.lblFechaFacturacion.setText("Fecha de Facturacion: " + modelo.cuentaActiva(auxCorreo).getSuscripcion().getFechaRegistro().plusMonths(1).toString());
            this.vista.lblFechaFacturacion.setText("Fecha de Facturacion: " + modelo.cuentaActiva(auxCorreo).getSuscripcion().getFechaFacturacion().toString());
            String tarjeta = modelo.cuentaActiva(auxCorreo).getTarjeta().getNroTarjeta().substring(12);
            String numbTarjeta = modelo.cuentaActiva(auxCorreo).getTarjeta().getNroTarjeta();
            if (numbTarjeta.startsWith("4")) {
                this.vista.jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/tarjeta_chikita.JPG")));
            } else if (numbTarjeta.startsWith("5")) {
                this.vista.jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/mastercard_1.PNG")));
            } else if (numbTarjeta.startsWith("3")) {
                this.vista.jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/american.PNG")));
            } else if (numbTarjeta.startsWith("2")) {
                this.vista.jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/diners.PNG")));
            }
            this.vista.lblTarjeta.setText(this.vista.lblTarjeta.getText() + tarjeta);

            this.vista.lblMembresia.setText(modelo.cuentaActiva(auxCorreo).getSuscripcion().getMembresia().getDescripcionMembresia());
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
