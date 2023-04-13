package Controlador;

import Modelo.Archivo;
import Modelo.ArregloDeCuentas;
import Modelo.CustomException;
import Vista.FrmInicio;
import Vista.FrmPaquetes;
import Vista.FrmPerfiles;
import Vista.FrmRegistro;
import Vista.FrmSolicitudReestablecerContrasena;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CtrlInicio {

    private ArregloDeCuentas modelo;
    private FrmInicio vista;

    public CtrlInicio(ArregloDeCuentas modelo, FrmInicio vista) {
        this.modelo = modelo;
        this.vista = vista;

        this.vista.btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //ACTUALIZANDO DATOS//
                Archivo archivo = new Archivo();
                ArregloDeCuentas modelo;
                modelo = (ArregloDeCuentas) archivo.deserializar(Archivo.archivoArregloCuentas);
                ///////////////////
                Boolean error = false;
                //CAPTAMOS EL CORREO Y LA CONTRASEÑA
                String correo = vista.txtUsuario.getText();
                String contraseña = vista.txtContraseña.getText().trim();

                if (correo.isEmpty() || contraseña.isEmpty()) {
                    JOptionPane.showMessageDialog(vista, "Ingresa un usuario y/o contraseña válido(s)", "Iniciar sesión", 0);
                    vista.txtContraseña.requestFocus();
                    return;
                }

                try {
                    boolean r = modelo.verificarEstadoSesion(correo);
                    if (r == false) {

                        modelo.validarInicioSesion(correo, contraseña);
                        modelo.cambiarEstadoSesion(correo);
                        int n = modelo.cuentaActiva(correo).getUsuario().getDispositivosConectados();
                        int max = (int) ((modelo.cuentaActiva(correo).getSuscripcion().getMembresia().getPantallas()) - 1);

                        if (!modelo.cuentaActiva(correo).getSuscripcion().getEstadoMembresia()) {
                            
                            vista.dispose();
                            System.out.println("CORREO: "+correo);
                            JOptionPane.showMessageDialog(vista, "NO CUENTA CON SUSCRIPCION ACTIVA");
                            FrmPaquetes fPlanes = new FrmPaquetes();
                            CtrlReactivarMembresia cRA = new CtrlReactivarMembresia(fPlanes, modelo);
                            cRA.setCorreo(correo);
                            archivo.serializar(Archivo.archivoArregloCuentas, modelo);
                            cRA.init();
                        } else {
                            JOptionPane.showMessageDialog(vista, "¡Bienvenido a Netflix!");
                            vista.dispose();
                            FrmPerfiles fPerfil = new FrmPerfiles();
                            CtrlPerfiles ctrlPerfil = new CtrlPerfiles(modelo, fPerfil);
                            ctrlPerfil.setCorreo(correo);
                            modelo.cuentaActiva(correo).getUsuario().aumentarDispositivo();
                            //GUARDANDO SESIÓN//       
                            archivo.serializar(Archivo.archivoArregloCuentas, modelo);
                            //////
                            ctrlPerfil.init();
                        }
                    } else if (r == true) {
                        int n = modelo.cuentaActiva(correo).getUsuario().getDispositivosConectados();
                        int max = (int) ((modelo.cuentaActiva(correo).getSuscripcion().getMembresia().getPantallas()) - 1);

                        modelo.validarInicioSesion(correo, contraseña);

                        //JOptionPane.showMessageDialog(vista, "¡Bienvenido a Netflix!");
                        if (!modelo.cuentaActiva(correo).getSuscripcion().getEstadoMembresia()) {
                            JOptionPane.showMessageDialog(vista, "NO CUENTA CON SUSCRIPCION ACTIVA");
                            vista.dispose();
                            FrmPaquetes fPlanes = new FrmPaquetes();
                            CtrlReactivarMembresia cRA = new CtrlReactivarMembresia(fPlanes, modelo);
                            cRA.setCorreo(correo);
                            archivo.serializar(Archivo.archivoArregloCuentas, modelo);
                            cRA.init();
                        } else {
                            vista.dispose();
                            FrmPerfiles fPerfil = new FrmPerfiles();

                            CtrlPerfiles ctrlPerfil = new CtrlPerfiles(modelo, fPerfil);
                            ctrlPerfil.setCorreo(correo);
                            ctrlPerfil.init();
                            modelo.cuentaActiva(correo).getUsuario().aumentarDispositivo();
                            //GUARDANDO SESIÓN//       
                            archivo.serializar(Archivo.archivoArregloCuentas, modelo);
                        }
                    }
                    //============================== Envío de email =============================================
                    //Email email = new Email("team.algoritmica@gmail.com", "Mensaje de app Iniciada" , mensaje);
                    //Thread enviar = new Thread(email);
                    //enviar.start();
                    //===========================================================================================
                } catch (CustomException e) {
                    System.out.println("EXCEPCION: " + e.getMessage());
                    JOptionPane.showMessageDialog(vista, e.getMessage());
                }
            }
        });

        this.vista.btnAyuda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                FrmSolicitudReestablecerContrasena frmSoliReesContra = new FrmSolicitudReestablecerContrasena();
                CtrlSolicitudReestablecerContra ctrlReest = new CtrlSolicitudReestablecerContra(modelo, frmSoliReesContra);
                ctrlReest.init();
            }
        });

        this.vista.btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vista.dispose();
                FrmRegistro fRegistro = new FrmRegistro();
                CtrlRegistro ctrlRegistro = new CtrlRegistro(modelo, fRegistro);
                ctrlRegistro.init();
            }
        });

        this.vista.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
    }

    public String getCorreo() {
        String correo = vista.txtUsuario.getText();
        return correo;
    }

    public void init() {
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
    }
}
