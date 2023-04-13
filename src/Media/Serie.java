package Media;

import java.io.Serializable;

/**
 *
 * @author Luiggi Pasache
 *
 *
 *
 *
 */
public class Serie extends Video implements Serializable {

    private int nroCapitulos;
    private int nroTemporadas;

    public Serie() {
    }

    public Serie(int nroCapitulos, int nroTemporadas, String Titulo, String Director, String Genero, int Anio, int Edad_Apta, String Sinopsis, String[] elenco, boolean Visto, int duracion, int TiempoVisto, int codigo, String dirImg, String dirVid) {
        super(Titulo, Director, Genero, Anio, Edad_Apta, Sinopsis, elenco, Visto, duracion, TiempoVisto, codigo, dirImg, dirVid);
        this.nroCapitulos = nroCapitulos;
        this.nroTemporadas = nroTemporadas;
    }

    public int getNroTemporadas() {
        return nroTemporadas;
    }

    public void setNroTemporadas(int nroTemporadas) {
        this.nroTemporadas = nroTemporadas;
    }
}
