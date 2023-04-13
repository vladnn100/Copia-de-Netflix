package Modelo;

import java.io.Serializable;

public class ArregloPerfiles implements Serializable {

    private final int MAX = 4;

    private Perfil[] arregloPerfiles;
    private int index;

    public ArregloPerfiles() {
        arregloPerfiles = new Perfil[MAX];

        for (int i = 0; i < MAX; i++) {
            arregloPerfiles[i] = new Perfil("Perfil" + (i + 1));
        }
    }

    public Perfil[] getPerfiles() {
        return arregloPerfiles;
    }

    public Perfil getPerfil(int index) {
        return arregloPerfiles[index];
    }

    public void setPerfil(int index, Perfil perfil) {
        arregloPerfiles[index] = perfil;
    }

    public boolean add(Perfil perfil) {
        try {
            arregloPerfiles[index] = perfil;
            index++;
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public Perfil buscarPorNombre(String nombre) {
        Perfil aux = new Perfil();
        for (Perfil p : arregloPerfiles) {
            if (p.getNombre().equals(nombre)) {
                aux = p;
            }
        }
        return aux;
    }

    public boolean buscarPorNombre2(String nombre) {
        boolean b = false;
        for (Perfil p : arregloPerfiles) {
            if (p.getNombre().equals(nombre)) {
                b = true;
            }
        }
        return b;
    }

    public int posicionPorNombre(String nombre) {
        int i = 0;
        int a = -1;
        for (Perfil p : arregloPerfiles) {
            if (p.getNombre().equals(nombre)) {
                a = i;
            }
            i++;
        }
        return a;
    }

    public void cambiarActividad(int n) {
        if (n < index) {
            for (int i = 0; i < index; i++) {
                if (n == i) {
                    arregloPerfiles[i].setActivo(true);
                }
            }
        }
    }

    public Perfil perfilActivo() {
        Perfil aux = new Perfil();
        for (Perfil p : arregloPerfiles) {
            if (p.isActivo() == true) {
                aux = p;
            }
        }
        return aux;
    }
}
