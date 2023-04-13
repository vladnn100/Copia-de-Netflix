package Controlador;

import Modelo.Archivo;
import Modelo.ArregloDeCuentas;
import Modelo.CustomException;
import Vista.FrmAdministrarCuenta;
import Vista.FrmCambiarDiaFacturacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CtrlCambiarDiaDeFacturacion {

    private ArregloDeCuentas modelo;
    private FrmCambiarDiaFacturacion vista;
    private String auxCorreo;

    public CtrlCambiarDiaDeFacturacion(FrmCambiarDiaFacturacion vista, ArregloDeCuentas modelo) {
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
        this.vista.btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String diaMes = vista.boxDias.getSelectedItem().toString();
                try {
                    //CONDICION ULTIMO DIA DEL MES//
                    if (diaMes.equals(vista.boxDias.getItemAt(27))) {
                        LocalDate actualFecha = LocalDate.now().plusMonths(1);

                        int numberMes = actualFecha.getMonthValue();
                        if (numberMes == 4 || numberMes == 6 || numberMes == 9 || numberMes == 11) {
                            diaMes = "30";

                        } else if (numberMes == 1 || numberMes == 3 || numberMes == 5 || numberMes == 7 || numberMes == 8 || numberMes == 10 || numberMes == 12) {
                            diaMes = "31";
                            int nMes = Integer.parseInt(diaMes);
                        } else if (numberMes == 2) {
                            diaMes = "28";
                        }

                    } // TERMINA CONDICION ULTIMO DIA MES//
                    else {
                        diaMes = (String) vista.boxDias.getSelectedItem();
                        //Archivos.Creador.serializar(Archivos.Archivos.archivoArregloCuentas, ArregloCuentas);
                    }

                    int nMes = Integer.parseInt(diaMes);
                    modelo.cuentaActiva(auxCorreo).getSuscripcion().cambiarFecha(nMes);

                    System.out.println("La fecha nueva es " + modelo.cuentaActiva(auxCorreo).getSuscripcion().getFechaRegistro());
                    JOptionPane.showMessageDialog(vista, "¡La fecha de facturación ha sido cambiada con éxito!");
                    JOptionPane.showMessageDialog(vista, "Su proxima fecha de facturación es " + modelo.cuentaActiva(auxCorreo).getSuscripcion().getFechaFacturacion());

                    //GUARDANDO LOS CAMBIOS EN EL ARCHIVO//
                    Archivo archivo = new Archivo();
                    archivo.serializar(Archivo.archivoArregloCuentas, modelo);
                    // // // // // // // // // // // // // // 

                    vista.dispose();
                    FrmAdministrarCuenta fAdmin = new FrmAdministrarCuenta();
                    CtrlAdministrarCuenta controlador = new CtrlAdministrarCuenta(modelo, fAdmin);
                    controlador.setCorreo(auxCorreo);
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

                FrmAdministrarCuenta fAdmin = new FrmAdministrarCuenta();
                CtrlAdministrarCuenta controlador = new CtrlAdministrarCuenta(modelo, fAdmin);
                controlador.setCorreo(auxCorreo);
                controlador.init();
            }
        });
    }

    public void init() {
        try {
            //ACTUALIZANDO DATOS//
            Archivo archivo = new Archivo();
            modelo = (ArregloDeCuentas) archivo.deserializar(Archivo.archivoArregloCuentas);
            ///////////////////
            System.out.println("El correo de esta cuenta es : " + auxCorreo);
            Calendar cal = Calendar.getInstance();
            int diaMes = modelo.cuentaActiva(auxCorreo).getSuscripcion().getFechaRegistro().getDayOfMonth();
            String diaMesStr = String.valueOf(diaMes);
            this.vista.labDay.setText(diaMesStr);
            this.vista.setLocationRelativeTo(null);
            this.vista.setVisible(true);

            //ESTABLECIENDO VALORES MODELO//
            vista.boxDias.setModel(new DefaultComboBoxModel<>(new String[]{
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
                "Ultimo Día del Mes",}));

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
