package Media;

import java.io.Serializable;

public class Visualizacion implements Serializable {

    Video video;

    //CONSTRUCTORES
    public Visualizacion(Video videos) {
        this.video = videos;
    }

    //GETTERS Y SETTERS
    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;

    }

}
