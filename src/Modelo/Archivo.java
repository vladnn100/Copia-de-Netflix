package Modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Archivo {

    //NOMBRE DEL ARCHIVO
    public static final String archivoArregloCuentas = "ArregloCuentas";

    public void serializar(String sNombreArchivo, Object obj) {
        try {
            ObjectOutputStream escritor
                    = new ObjectOutputStream(
                            new FileOutputStream(sNombreArchivo));
            escritor.writeObject(obj);
            escritor.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Object deserializar(String sNombreArchivo) {
        Object obj = new Object();
        try {
            ObjectInputStream lector
                    = new ObjectInputStream(
                            new FileInputStream(sNombreArchivo));
            obj = (Object) lector.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
}
