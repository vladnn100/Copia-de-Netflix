package Modelo;

/**
 *
 * @author Ernesto Silva
 */
public class ArregloDeUsuarios {

    private Usuario x[];

    public ArregloDeUsuarios() {
        x = new Usuario[0];
    }

    /*
    public boolean add(String cuenta,String contra){
        if(indice < user.length){
            user[indice] = new Usuario(cuenta,contra);
            indice++;
            return true;
        }
        return false;
    }
    
    public boolean validar(String cuenta,String contra){
        if(cuenta.equalsIgnoreCase("") && contra.equalsIgnoreCase("")){
            return false;
        }
        for(int i = 0; i < this.indice + 1 ; i++){
            if(user[i].getLogin().equals(cuenta) && user[i].getContraseña().equals(contra)){
                return true;
            }
        }
        return false;
    }
    
    public boolean validarCuenta(String cuenta){
        for(int i = 0; i<this.indice + 1; i++){
            if(user[i].getLogin().equals(cuenta)){
                return true;
            }
        }
        return false;
    }
     */
}
