package Controlador;

import Modelo.Archivo;
import Modelo.ArregloDeCuentas;
import Modelo.CustomException;
import Modelo.Idiomas;
import Modelo.Perfil;

import Vista.FrmEditarPerfiles;
import Vista.FrmPerfiles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CtrlEditarPerfiles {

    private ArregloDeCuentas modelo;
    private FrmEditarPerfiles vista;
    private String auxCorreo;

    public CtrlEditarPerfiles(FrmEditarPerfiles vista, ArregloDeCuentas modelo) {
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
                int dialogResult = JOptionPane.showConfirmDialog(vista, "¿Desea guardar los cambios realizados en este perfil?", "", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {

                    try {
                        Perfil[] perfiles = modelo.cuentaActiva(auxCorreo).getPerfiles1();
                        modelo.cuentaActiva(auxCorreo).getPerfil(vista.indexPerfilAux).setNombre(vista.txtNombre.getText());

                        for (Idiomas idioma : Idiomas.values()) {
                            if (idioma.ordinal() == vista.cboIdiomas.getSelectedIndex()) {
                                modelo.cuentaActiva(auxCorreo).getPerfil(vista.indexPerfilAux).setIdioma(idioma);
                                break;
                            }
                        }

                        modelo.cuentaActiva(auxCorreo).getPerfil(vista.indexPerfilAux).setRestriccionDeEdad(vista.rdoRestriccion.isSelected());

                        JOptionPane.showMessageDialog(vista, "Los cambios se guardaron correctamente", "Editar perfil", JOptionPane.INFORMATION_MESSAGE);

                        vista.dispose();

                        FrmPerfiles fPerfiles = new FrmPerfiles();

                        fPerfiles.btnPerfil1.setText(perfiles[0].getNombre());
                        fPerfiles.btnPerfil2.setText(perfiles[1].getNombre());
                        fPerfiles.btnPerfil3.setText(perfiles[2].getNombre());
                        fPerfiles.btnPerfil4.setText(perfiles[3].getNombre());
//GUARDANDO LOS CAMBIOS EN EL ARCHIVO//
                        Archivo archivo = new Archivo();
                        archivo.serializar(Archivo.archivoArregloCuentas, modelo);
                        // // // // // // // // // // // // // // 
                        CtrlPerfiles ctrlinicio = new CtrlPerfiles(modelo, fPerfiles);
                        ctrlinicio.setCorreo(auxCorreo);
                        ctrlinicio.init();

                    } catch (CustomException e) {
                        System.out.println("EXCPECION: " + e.getMessage());
                    }

                } else {
                    vista.txtNombre.setSelectionStart(0);
                    vista.txtNombre.setSelectionEnd(vista.txtNombre.getText().length());
                    vista.txtNombre.requestFocus();
                }
            }
        });

        this.vista.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                try {
                    Perfil[] perfiles = modelo.cuentaActiva(auxCorreo).getPerfiles1();
                    FrmPerfiles fPerfiles = new FrmPerfiles();
                    vista.dispose();
                    fPerfiles.btnPerfil1.setText(perfiles[0].getNombre());
                    fPerfiles.btnPerfil2.setText(perfiles[1].getNombre());
                    fPerfiles.btnPerfil3.setText(perfiles[2].getNombre());
                    fPerfiles.btnPerfil4.setText(perfiles[3].getNombre());

                    CtrlPerfiles ctrlinicio = new CtrlPerfiles(modelo, fPerfiles);
                    ctrlinicio.setCorreo(auxCorreo);
                    ctrlinicio.init();
                } catch (CustomException e) {
                    System.out.println("EXCEPCION: " + e.getMessage());
                }

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

        this.vista.cboIdiomas.removeAllItems();
        System.out.println("El correo de esta cuenta es : " + auxCorreo);
        for (Idiomas idioma : Modelo.Idiomas.values()) {
            this.vista.cboIdiomas.addItem(idioma.toString());
        }
        try {
            this.vista.cboIdiomas.setSelectedItem(modelo.cuentaActiva(auxCorreo).getPerfil(vista.indexPerfilAux).getIdioma().toString());
            this.vista.rdoTodas.setSelected(!modelo.cuentaActiva(auxCorreo).getPerfil(vista.indexPerfilAux).isRestriccionDeEdad());
            this.vista.rdoRestriccion.setSelected(modelo.cuentaActiva(auxCorreo).getPerfil(vista.indexPerfilAux).isRestriccionDeEdad());

            this.vista.txtNombre.setText(modelo.cuentaActiva(auxCorreo).getPerfil(vista.indexPerfilAux).getNombre());

            this.vista.txtNombre.requestFocus();
            this.vista.txtNombre.setSelectionStart(0);
            this.vista.txtNombre.setSelectionEnd(modelo.cuentaActiva(auxCorreo).getPerfil(vista.indexPerfilAux).getNombre().length());
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
