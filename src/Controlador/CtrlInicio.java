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

    private final ArregloDeCuentas modelo;
    private final FrmInicio vista;

    public CtrlInicio(ArregloDeCuentas modelo, FrmInicio vista) {
        this.modelo = modelo;
        this.vista = vista;

        this.vista.btnIniciar.addActionListener(this.accionIniciar());

        this.vista.btnAyuda.addActionListener(this.accionInvocarAyuda());

        this.vista.btnRegistrar.addActionListener(this.accionInvocarRegistro());

        this.vista.btnSalir.addActionListener(this.accionSalir());
    }

    public String getCorreo() {
        String correo = vista.txtUsuario.getText();
        return correo;
    }

    public void init() {
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
    }
    
        
    private ActionListener accionInvocarAyuda() {
        return (ActionEvent ae) -> {
            FrmSolicitudReestablecerContrasena frmSoliReesContra = new FrmSolicitudReestablecerContrasena();
            CtrlSolicitudReestablecerContra ctrlReest = new CtrlSolicitudReestablecerContra(modelo, frmSoliReesContra);
            ctrlReest.init();
        };
    }
    
    private ActionListener accionInvocarRegistro() {
        return (ActionEvent ae) -> {
            vista.dispose();
            FrmRegistro fRegistro = new FrmRegistro();
            CtrlRegistro ctrlRegistro = new CtrlRegistro(modelo, fRegistro);
            ctrlRegistro.init();
        };
    }
    
    private ActionListener accionSalir() {
        return (ActionEvent ae) -> {
            System.exit(0);
        };
    }

    private ActionListener accionIniciar() {
        return (ActionEvent ae) -> {
            //ACTUALIZANDO DATOS//
            Archivo archivo = new Archivo();
            ArregloDeCuentas modelo1;
            modelo1 = (ArregloDeCuentas) archivo.deserializar(Archivo.archivoArregloCuentas);
            ///////////////////

            //CAPTAMOS EL CORREO Y LA CONTRASEÑA
            String correo = vista.txtUsuario.getText();
            String contraseña = vista.txtContraseña.getText().trim();
            if (correo.isEmpty() || contraseña.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Ingresa un usuario y/o contraseña válido(s)", "Iniciar sesión", 0);
                vista.txtContraseña.requestFocus();
                return;
            }
            try {
                boolean estaVerificado = modelo1.verificarEstadoSesion(correo);
                if (!estaVerificado) {
                    modelo1.validarInicioSesion(correo, contraseña);
                    modelo1.cambiarEstadoSesion(correo);
                    int n = modelo1.cuentaActiva(correo).getUsuario().getDispositivosConectados();
                    int max = (int) ((modelo1.cuentaActiva(correo).getSuscripcion().getMembresia().getPantallas()) - 1);
                    if (!modelo1.cuentaActiva(correo).getSuscripcion().getEstadoMembresia()) {
                        vista.dispose();
                        System.out.println("CORREO: "+correo);
                        JOptionPane.showMessageDialog(vista, "NO CUENTA CON SUSCRIPCION ACTIVA");
                        FrmPaquetes fPlanes = new FrmPaquetes();
                        CtrlReactivarMembresia cRA = new CtrlReactivarMembresia(fPlanes, modelo1);
                        cRA.setCorreo(correo);
                        archivo.serializar(Archivo.archivoArregloCuentas, modelo1);
                        cRA.init();
                    } else {
                        JOptionPane.showMessageDialog(vista, "¡Bienvenido a Netflix!");
                        vista.dispose();
                        FrmPerfiles fPerfil = new FrmPerfiles();
                        CtrlPerfiles ctrlPerfil = new CtrlPerfiles(modelo1, fPerfil);
                        ctrlPerfil.setCorreo(correo);
                        modelo1.cuentaActiva(correo).getUsuario().aumentarDispositivo();
                        //GUARDANDO SESIÓN//
                        archivo.serializar(Archivo.archivoArregloCuentas, modelo1);
                        //////
                        ctrlPerfil.init();
                    }
                } else {
                    int n = modelo1.cuentaActiva(correo).getUsuario().getDispositivosConectados();
                    int max = (int) ((modelo1.cuentaActiva(correo).getSuscripcion().getMembresia().getPantallas()) - 1);
                    modelo1.validarInicioSesion(correo, contraseña);
                    //JOptionPane.showMessageDialog(vista, "¡Bienvenido a Netflix!");
                    if (!modelo1.cuentaActiva(correo).getSuscripcion().getEstadoMembresia()) {
                        JOptionPane.showMessageDialog(vista, "NO CUENTA CON SUSCRIPCION ACTIVA");
                        vista.dispose();
                        FrmPaquetes fPlanes = new FrmPaquetes();
                        CtrlReactivarMembresia cRA = new CtrlReactivarMembresia(fPlanes, modelo1);
                        cRA.setCorreo(correo);
                        archivo.serializar(Archivo.archivoArregloCuentas, modelo1);
                        cRA.init();
                    } else {
                        vista.dispose();
                        FrmPerfiles fPerfil = new FrmPerfiles();
                        CtrlPerfiles ctrlPerfil = new CtrlPerfiles(modelo1, fPerfil);
                        ctrlPerfil.setCorreo(correo);
                        ctrlPerfil.init();
                        modelo1.cuentaActiva(correo).getUsuario().aumentarDispositivo();
                        //GUARDANDO SESIÓN//
                        archivo.serializar(Archivo.archivoArregloCuentas, modelo1);
                    }
                }
                //============================== Envío de email =============================================
                //Email email = new Email("team.algoritmica@gmail.com", "Mensaje de app Iniciada" , mensaje);
                //Thread enviar = new Thread(email);
                //enviar.start();
                //===========================================================================================
            }catch (CustomException e) {
                System.out.println("EXCEPCION: " + e.getMessage());
                JOptionPane.showMessageDialog(vista, e.getMessage());
            }
        };
    }
}
