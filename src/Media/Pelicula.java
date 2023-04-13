package Media;

import java.io.Serializable;

/**
 *
 * @author Luiggi Pasache
 */
public class Pelicula extends Video implements Serializable {

    public Pelicula() {
        super();
    }

    public Pelicula(String Titulo, String Director, String Genero, int Anio, int Edad_Apta, String Sinopsis, String[] elenco, boolean Visto, int duracion, int TiempoVisto, int codigo, String dirImg, String dirVid) {
        super(Titulo, Director, Genero, Anio, Edad_Apta, Sinopsis, elenco, Visto, duracion, TiempoVisto, codigo, dirImg, dirVid);
    }

}
