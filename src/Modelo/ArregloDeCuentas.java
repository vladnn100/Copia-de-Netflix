package Modelo;

import java.io.Serializable;

/**
 *
 * @author ErnestoSilva
 */
public class ArregloDeCuentas implements Serializable {

    private Cuenta cuentas[];

    public ArregloDeCuentas() {
        cuentas = new Cuenta[0];
    }

    //===================
    //      METODOS
    //===================
    public void agregarCuenta(Cuenta cuenta) {
        int i;
        //Se captura la dimension del vector
        i = cuentas.length;
        Cuenta a;
        aumentarDimension();
        a = cuenta;
        try {
            cuentas[i] = a;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Indice fuera de limites: " + e);
        }

    }

    private void aumentarDimension() {
        //int n=x.length;
        int n = cuentas.length;
        n = n + 1;
        Cuenta y[] = new Cuenta[n];
        for (int i = 0; i < cuentas.length; i = i + 1) {
            y[i] = cuentas[i];
        }
        this.cuentas = y;
    }

    private void disminuirDimension() {
        int n = cuentas.length;
        n = n - 1;
        Cuenta aux[] = new Cuenta[n];

        for (int i = 0; i < n; i++) {
            aux[i] = cuentas[i];
        }
        cuentas = aux;
    }

    public void cambiarEstadoSesion(String Correo) {
        int n;
        n = cuentas.length;
        for (Cuenta c : cuentas) {
            if (c.getUsuario().getCorreo().equals(Correo)) {
                c.setSesion(!c.getSesion());
                break;
            }
        }
    }

    public boolean validarPorCorreo1(String Correo) {
        int n;
        n = cuentas.length;
        Boolean a = false;
        for (Cuenta c : cuentas) {
            if (c.getUsuario().getCorreo().equals(Correo)) {
                a = true;
                break;
            }
        }
        return a;
    }

    public Cuenta buscarCuentaPorCorreo(String Correo) throws CustomException {
        int n;
        n = cuentas.length;
        Cuenta a = new Cuenta();
        Boolean b = false;
        for (Cuenta c : cuentas) {
            if (c.getUsuario().getCorreo().equals(Correo)) {
                b = true;
                a = c;
                break;
            }
        }
        if (b == false) {
            throw new CustomException("NO SE ENCONTRÓ AL USUARIO");
        }
        return a;
    }

    public boolean validarPorCorreo(String Correo) throws CustomException {
        Boolean a = false;
        for (Cuenta c : cuentas) {
            System.out.println(c.getUsuario().getCorreo());
            if (c.getUsuario().getCorreo().equals(Correo)) {
                a = true;
                //System.out.println(c.getUsuario().getCorreo());
                break;
            }
        }
        if (a == false) {
            throw new CustomException("EL USUARIO NO HA INICIADO SESION");
        }
        return a;
    }

    public void validarInicioSesion(String Correo, String Contraseña) throws CustomException {
        Boolean correoE = false;
        Boolean contraseñaE = false;
        for (Cuenta c : cuentas) {
            if (c.getUsuario().getCorreo().equals(Correo)) {
                correoE = true;
                if (c.getUsuario().getContraseña().equals(Contraseña)) {
                    //System.out.println("CORREO: " + c.getUsuario().getCorreo());
                    contraseñaE = true;
                    break;
                }
            }
        }
        if (correoE == false || contraseñaE == false) {
            throw new CustomException("EL CORREO NO SE ENCUENTRA REGISTRADO O LA CONTRASÑEA NO COINCIDE");
        }
    }

    public boolean verificarEstadoSesion(String Correo) {
        boolean r = Boolean.FALSE;
        for (Cuenta c : cuentas) {
            if (c.getUsuario().getCorreo().equals(Correo)) {
                if (c.getSesion() == true) {
                    r = true;
                } else if (c.getSesion() == false) {
                    r = false;
                }
            }
        }
        return r;
    }

    public Cuenta cuentaActiva(String correo) throws CustomException{

        Cuenta cuentaAux = new Cuenta();
        Boolean s = true;
        for (Cuenta c : cuentas) {
            if (c.getSesion() && c.getUsuario().getCorreo().equals(correo)) {
                cuentaAux = c;
                s = false;
                break;
            }
        }

        if (s == true) {
            throw new CustomException("NO EXISTE UN USUARIO ACTIVO");
        }
        return cuentaAux;
    }

    public void inactivarSesión(Cuenta cuenta) {
        cuenta.setSesion(!cuenta.getSesion());

    }

    public void dimensionArreglo() throws Exception {
        int i;
        try {
            i = cuentas.length;
            System.out.println("El tamaño actual de el arreglo de cuentas es: " + i + ".");
        } catch (Exception e) {
            System.out.println("EXPCECION: " + e.getMessage());
        }

    }

    public int posicionPorCorreo(String Correo) {
        int posicionBuscada = -1;
        int n;
        n = cuentas.length;
        for (int i = 0; i < n; i++) {
            if (cuentas[i].getUsuario().getCorreo().equals(Correo)) {
                posicionBuscada = i;
                break;
            }
        }
        return posicionBuscada;
    }

    public void eliminarPorCorreo(String Correo) {
        int nElemento = posicionPorCorreo(Correo);
        if (nElemento < cuentas.length) {
            while (nElemento < (cuentas.length - 1)) {
                cuentas[nElemento] = cuentas[nElemento + 1];
                nElemento++;
            }
            disminuirDimension();
        } else {
            System.out.println("POSICION MAYOR AL TAMAÑO DEL VECTOR...!!");
        }
    }

    public boolean editarCorreo(String correo, String nuevoCorreo) throws CustomException {
        int n = cuentas.length;
        Boolean error = true;
        for (int i = 0; i <= n; i++) {
            if (cuentas[i].getUsuario().getCorreo().equals(correo)) {
                if (!validarPorCorreo1(nuevoCorreo)) {
                    error = false;
                    cuentas[i].getUsuario().setCorreo(nuevoCorreo);
                }
                break;
            }
        }
        if (error == true) {
            throw new CustomException("EL NUEVO YA SE ENCUENTRA REGISTRADO");
        }
        return error;
    }

    public void mostrarCuentas() {

        for (int i = 0; i < cuentas.length; i = i + 1) {
            System.out.println("\n\n=====================================================");
            System.out.println("    CANTIDAD DE USUARIOS: " + cuentas.length + " - USUARIO Nro. " + (i + 1));
            System.out.println("=====================================================");
            System.out.println("CORREO: " + cuentas[i].getUsuario().getCorreo() + "\n"
                    + "CONTRASEÑA: " + cuentas[i].getUsuario().getContraseña() + "\n"
                    + "TELEFONO: " + cuentas[i].getUsuario().getTelefono());
            System.out.println("=====================================================");
            System.out.println("DATOS DE LA SUSCRIPCIÓN");
            System.out.println("FECHA DE REGISTRO " + cuentas[i].getSuscripcion().getFechaRegistro() + "\n"
                    + "ESTADO DE LA MEMBRESÍA: " + cuentas[i].getSuscripcion().getEstadoMembresia() + "\n"
                    + "MEMBRESÍA: " + cuentas[i].getSuscripcion().getMembresia());
            System.out.println("=====================================================");
            System.out.println("DATOS DE LA TARJETA");
            System.out.println("NOMBRE: " + cuentas[i].getTarjeta().getNombre() + "\n"
                    + "APELLIDO: " + cuentas[i].getTarjeta().getApellido() + "\n"
                    + "NUMERO DE TARJETA: " + cuentas[i].getTarjeta().getNroTarjeta() + "\n"
                    + "FECHA DE VENCIMIENTO: " + cuentas[i].getTarjeta().getFechaVencimiento().toString() + "\n"
                    + "CVV: " + cuentas[i].getTarjeta().getCvv() + "\n"
                    + "SALDO: " + cuentas[i].getTarjeta().getSaldo());
            System.out.println("=====================================================");
        }
    }

    public void mostrarCuentasActivas() {
        System.out.println("----CUENTAS ACTIVAS-------");
        for (Cuenta c : cuentas) {
            if (c.getUsuario().getDispositivosConectados() > 0) {
                System.out.println(c.getUsuario().getCorreo() + "\n");
            }

        }
        System.out.println("-----------------");
    }

    public Cuenta crearCuenta(Usuario user, Tarjeta tarjeta, Suscripcion suscripcion) {
        Cuenta cuentaNueva = new Cuenta();
        cuentaNueva.setUsuario(user);
        cuentaNueva.setTarjeta(tarjeta);
        cuentaNueva.setSuscripcion(suscripcion);
        return cuentaNueva;
    }

    public Perfil tomarPerfilPorNombre(String nombre) {
        Perfil aux = new Perfil();

        for (Cuenta c : cuentas) {
            for (Perfil p : c.getPerfiles1()) {
                if (p.getNombre().equals(nombre)) {
                    aux = p;
                }
            }
        }
        return aux;
    }
}
