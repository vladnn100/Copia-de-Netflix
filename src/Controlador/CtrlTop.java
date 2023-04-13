
package Controlador;

import Media.Video;
import Modelo.Archivo;
import Modelo.ArregloDeCuentas;
import Modelo.CustomException;
import Vista.FrmCatalogo;
import Vista.FrmTopDiezPeliculas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrlTop {
    ArregloDeCuentas modelo;
    FrmTopDiezPeliculas vista;
    String auxCorreo;

    public CtrlTop(ArregloDeCuentas modelo, FrmTopDiezPeliculas vista) {
        this.modelo = modelo;
        this.vista = vista;
        
        this.vista.btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vista.dispose();
                FrmCatalogo fCatalogo= new FrmCatalogo();
                CtrlCatalogo cCatalogo = new CtrlCatalogo(modelo, fCatalogo);
                cCatalogo.setCorreo(auxCorreo);
                cCatalogo.init();
            }
        });
    }
    
    public void init(){
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
        
        try {
            modelo.cuentaActiva(auxCorreo).getPerfiles().perfilActivo().getVisualizaciones().ordenaVectorVisualizaciones();
            modelo.cuentaActiva(auxCorreo).getPerfiles().perfilActivo().getVisualizaciones().mostrarContenido();
            this.vista.txtTop.setText(modelo.cuentaActiva(auxCorreo).getPerfiles().perfilActivo().getVisualizaciones().mostrarTop());
            //modelo.cuentaActiva(auxCorreo).getPerfiles().perfilActivo().getVisualizaciones().mostrarContenido();
            //Video v = modelo.cuentaActiva(auxCorreo).getPerfiles().perfilActivo().getVisualizaciones().contenidoMásVisto();
            //this.vista.lblTop.setText(modelo.cuentaActiva(auxCorreo).getPerfiles().perfilActivo().getVisualizaciones().mostrarTop());
            
        } catch (CustomException e) {
            System.out.println("EXCEPCION: "+e.getMessage());
        }    
    }
    
    public String getAuxCorreo() {
        return auxCorreo;
    }

    public void setAuxCorreo(String auxCorreo) {
        this.auxCorreo = auxCorreo;
    }
    
    
}

