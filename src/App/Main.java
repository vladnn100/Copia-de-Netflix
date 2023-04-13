package App;

import Controlador.CtrlInicio;
import Media.Multimedia;
import Media.Pelicula;
import Media.Serie;
import Media.Video;
import Modelo.Archivo;
import Modelo.ArregloDeCuentas;
import Modelo.Membresia;
import Modelo.Suscripcion;
import Modelo.Tarjeta;
import Modelo.Usuario;
import Vista.FrmInicio;
import java.io.File;

public class Main {

    public static void main(String[] args) {

        //INSTANCIAMOS UNA VARIABLE DEL TIPO ARREGLO CUENTAS
        ArregloDeCuentas Cuentas = new ArregloDeCuentas();

        //crearCuenta: Generamos una nueva cuenta
        //para poder areglarla a la variable del tipo Cuentas.
        Cuentas.agregarCuenta(Cuentas.crearCuenta(new Usuario("franco@gmail.com", "franco@gmail.com", "994421210"),
                new Tarjeta("Fran", "Co", "4895045575702316", "25/12", 123),
                new Suscripcion(Membresia.PREMIUM)));

        Cuentas.agregarCuenta(Cuentas.crearCuenta(new Usuario("asd@gmail.com", "asd@gmail.com", "984421210"),
                new Tarjeta("El pepe", "cech", "1234567891234567", "25/12", 123),
                new Suscripcion(Membresia.PREMIUM)));

        Cuentas.agregarCuenta(Cuentas.crearCuenta(new Usuario("qwe@gmail.com", "qwe@gmail.com", "984421210"),
                new Tarjeta("El pepe", "cech", "1234567891234567", "25/12", 123),
                new Suscripcion(Membresia.PREMIUM)));

        Cuentas.agregarCuenta(Cuentas.crearCuenta(new Usuario("romario@gmail.com", "romario@gmail.com", "984421210"),
                new Tarjeta("El pepe", "cech", "1234567891234567", "25/12", 123),
                new Suscripcion(Membresia.PREMIUM)));

        Cuentas.agregarCuenta(Cuentas.crearCuenta(new Usuario("dimitri@gmail.com", "dimitri@gmail.com", "984421210"),
                new Tarjeta("El pepe", "cech", "1234567891234567", "25/12", 123),
                new Suscripcion(Membresia.PREMIUM)));

        Cuentas.agregarCuenta(Cuentas.crearCuenta(new Usuario("elbicho@gmail.com", "elbicho@gmail.com", "984421210"),
                new Tarjeta("El pepe", "cech", "1234567891234567", "25/12", 123),
                new Suscripcion(Membresia.PREMIUM)));

        Cuentas.agregarCuenta(Cuentas.crearCuenta(new Usuario("1", "1", "984421210"),
                new Tarjeta("El pepe", "cech", "5895045648213405", "25/12", 123),
                new Suscripcion(Membresia.PREMIUM)));

        //INSTANCIAMOS LA PELICULA "Whiplash: Música & Obsesión"
        String[] elenco1 = {"Miles Teller", "J. K. Simmons", "Melissa Benoist", "Austin Stowell", "Jayson Blair",
            "Kavita Patil", "Michael Cohen", "Kofi Siriboe", "Paul Reiser"};

        Pelicula p1 = new Pelicula( /*TITULO*/"Whiplash",
                /*DIRECTOR*/ "Damien Chazelle",
                /*GENERO*/ "DRAMA",
                /*AÑO DE ESTRENO*/ 2014,
                /*EDAD APTA*/ 15,
                /*SINOPSIS*/ "GA Andrew Neiman es un joven y ambicioso baterista de jazz. Marcado por el fracaso de la carrera literaria de su padre, "
                + "\nestá obsesionado con alcanzar la cima dentro del elitista conservatorio de música de la Costa Este en el que estudia.",
                /*CAST*/ elenco1,
                /*ESTADO DE VISUALIZACION*/ false,
                /*DURACION*/ 106,
                /*MINUTOS REPRODUCIDOS*/ 0,
                /*CODIGO*/ 1, "src\\Imagenes\\Peliculas\\whiplash.png", "src\\Video\\Peliculas\\whiplash.mp4");
//        Media.Multimedia.catalogoPeliculas.agregarPelicula(p1);

        Pelicula p2 = new Pelicula( /*TITULO*/"Deadpool",
                /*DIRECTOR*/ "Damien Chazelle",
                /*GENERO*/ "DRAMA",
                /*AÑO DE ESTRENO*/ 2014,
                /*EDAD APTA*/ 15,
                /*SINOPSIS*/ "Sinopsis de Deadpool",
                /*CAST*/ elenco1,
                /*ESTADO DE VISUALIZACION*/ false,
                /*DURACION*/ 106,
                /*MINUTOS REPRODUCIDOS*/ 0,
                /*CODIGO*/ 2, "src\\Imagenes\\Peliculas\\deadpool.png", "src\\Video\\Peliculas\\deadpool.mp4");

//        Media.Multimedia.catalogoPeliculas.agregarPelicula(p2);

        /*
        Serie s1 = new Serie();
        Serie s2 = new Serie();
        Serie s3 = new Serie();
        Serie s4 = new Serie();

        Media.Multimedia.catalogoSeries.agregarSerie(s1);
        Media.Multimedia.catalogoSeries.agregarSerie(s2);
        Media.Multimedia.catalogoSeries.agregarSerie(s3);
        Media.Multimedia.catalogoSeries.agregarSerie(s4);
         */
        //Pelicula p3 = new Pelicula( /*TITULO*/"Pulp Fiction", 
        //                            /*DIRECTOR*/"Damien Chazelle", 
        //                            /*GENERO*/"DRAMA", 
        //                           /*AÑO DE ESTRENO*/2014, 
        //                            /*EDAD APTA*/15, 
        //                            /*SINOPSIS*/"Sinopsis de Pulp Fiction", 
        //                            /*CAST*/elenco1, 
        //                            /*ESTADO DE VISUALIZACION*/false, 
        //                            /*DURACION*/106, 
        //                            /*MINUTOS REPRODUCIDOS*/0,
        //                            /*CODIGO*/1,"src\\Imagenes\\Peliculas\\PulpFiction.png");
        //Media.Multimedia.catalogoPeliculas.agregarPelicula(p3);
        //INSTANCIAMOS LA PELICULA "El viaje de Chihiro"
        String[] elenco3 = {"Rumi Hiiragi", "Miyu Irino", "Mari Natsuki", "Yumi Tamai", "Bunta Sugawara", "Akio Nakamura"};
        Pelicula p3 = new Pelicula( /*TITULO*/"El viaje de Chihiro",
                /*DIRECTOR*/ "Hayao Miyazaki",
                /*GENERO*/ "ANIMACIÓN",
                /*AÑO DE ESTRENO*/ 2001,
                /*EDAD APTA*/ 13,
                /*SINOPSIS*/ "Perdida en el bosque, una niña (Rumi Hîragi) de 10 años conoce animales, fantasmas y criaturas extrañas.",
                /*CAST*/ elenco3,
                /*ESTADO DE VISUALIZACION*/ false,
                /*DURACION*/ 125,
                /*MINUTOS REPRODUCIDOS*/ 0,
                /*CODIGO*/ 3, "src\\Imagenes\\Peliculas\\El viaje de Chihiro.jpg", "src\\Video\\Peliculas\\El viaje de Chihiro.mp4");

//        Media.Multimedia.catalogoPeliculas.agregarPelicula(p3);

        //INSTANCIAMOS LA PELICULA "Fragmentado"
        String[] elenco4 = {"James McAvoy", "Anya Taylor-Joy", "Betty Buckley", "Haley Lu Richardson",
            "Jessica Sula", "Kim Director", "Brad William Henke", "Lyne Renée"};
        Pelicula p4 = new Pelicula( /*TITULO*/"Fragmentado",
                /*DIRECTOR*/ "M. Night Shyamalan",
                /*GENERO*/ "TERROR",
                /*AÑO DE ESTRENO*/ 2016,
                /*EDAD APTA*/ 15,
                /*SINOPSIS*/ "Kevin, un hombre con 23 personalidades, secuestra a 3 chicas jóvenes y las mantiene retenidas en un sótano. "
                + "\nA medida que una de sus personalidades va imponiéndose al resto, la vida de las chicas, y la del propio Kevin, peligra cada vez más.",
                /*CAST*/ elenco4,
                /*ESTADO DE VISUALIZACION*/ false,
                /*DURACION*/ 117,
                /*MINUTOS REPRODUCIDOS*/ 0,
                /*CODIGO*/ 4, "src\\Imagenes\\Peliculas\\fragmentando.jpg", "src\\Video\\Peliculas\\fragmentando.mp4");

//        Media.Multimedia.catalogoPeliculas.agregarPelicula(p4);

        //INSTANCIAMOS LA PELICULA "La tumba de las luciérnagas"
        String[] elenco5 = {"Tsutomo Tatsumi", "Ayano Shiraishi", "Akemi Yamaguchi", "Yoshiko Shinohara", "Hiroshi Kawaguchi"};
        Pelicula p5 = new Pelicula( /*TITULO*/"La tumba de las luciérnagas",
                /*DIRECTOR*/ "Isao Takahata",
                /*GENERO*/ "ANIMACIÓN",
                /*AÑO DE ESTRENO*/ 1988,
                /*EDAD APTA*/ 12,
                /*SINOPSIS*/ "Un adolescente se ve obligado a cuidar a su hermana menor después de que un bombardeo aliado durante la Segunda Guerra Mundial destruye su casa y mata a su madre.",
                /*CAST*/ elenco5,
                /*ESTADO DE VISUALIZACION*/ false,
                /*DURACION*/ 89,
                /*MINUTOS REPRODUCIDOS*/ 0,
                /*CODIGO*/ 5, "src\\Imagenes\\Peliculas\\La tumba de las luciérnagas.jpg", "src\\Video\\Peliculas\\La tumba de las luciérnagas.mp4");

//        Media.Multimedia.catalogoPeliculas.agregarPelicula(p5);

        //INSTANCIAMOS LA PELICULA "El jardín de las palabras"
        String[] elenco6 = {"Miyu Irino", "Kana Hanazawa", "Takeshi Maeda", "Yuka Terasaki",
            "Fumi Hirano", "Mikako Komatsu", "Suguru Inoue", "Megumi Han"};
        Pelicula p6 = new Pelicula( /*TITULO*/"El jardín de las palabras",
                /*DIRECTOR*/ "Makoto Shinkai",
                /*GENERO*/ "ANIMACIÓN",
                /*AÑO DE ESTRENO*/ 2013,
                /*EDAD APTA*/ 12,
                /*SINOPSIS*/ "Nadie parece entender a un adolescente y sus futuros sueños... "
                + "hasta que un día conoce a una chica problemática y distante en el parque.",
                /*CAST*/ elenco5,
                /*ESTADO DE VISUALIZACION*/ false,
                /*DURACION*/ 46,
                /*MINUTOS REPRODUCIDOS*/ 0,
                /*CODIGO*/ 6, "src\\Imagenes\\Peliculas\\El jardín de las palabras.jpg", "src\\Video\\Peliculas\\El jardín de las palabras.mp4");

//        Media.Multimedia.catalogoPeliculas.agregarPelicula(p6);

        //INSTANCIAMOS LA PELICULA "El exorcista"
        String[] elenco7 ={ "Ellen Burstyn","Max Von Sydow","Jason Miller","Linda Blair",
                            "Kitty Winn","Lee J. Cobb","Jack MacGowran","Megumi Han"};
        Pelicula p7 = new Pelicula( /*TITULO*/"El exorcista", 
                                    /*DIRECTOR*/"William Friedkin", 
                                    /*GENERO*/"TERROR", 
                                    /*AÑO DE ESTRENO*/1973, 
                                    /*EDAD APTA*/18, 
                                    /*SINOPSIS*/"NUna actriz llama a unos sacerdotes jesuitas para que intenten terminar con la posesión demoníaca de su hija de 12 años.", 
                                    /*CAST*/elenco6, 
                                    /*ESTADO DE VISUALIZACION*/false, 
                                    /*DURACION*/122, 
                                    /*MINUTOS REPRODUCIDOS*/0,
                                    /*CODIGO*/7,"src\\Imagenes\\Peliculas\\El exorcista.jpg", "src\\Video\\Peliculas\\El exorcista.mp4");
        
        Media.Multimedia.catalogoPeliculas.agregarPelicula(p7);
        
        //INSTANCIAMOS LA PELICULA "El cuaderno de Tomy"
        String[] elenco8 ={ "Valeria Bertuccelli","Esteban Lamothe","Julián Sorín","Mauricio Dayub"
                            ,"Catarina Spinetta","Carla Quevedo","Anita Pauls","Mónica Antonópulos"};
        Pelicula p8 = new Pelicula( /*TITULO*/"El cuaderno de Tomy", 
                                    /*DIRECTOR*/"Carlos Sorín", 
                                    /*GENERO*/"DRAMA", 
                                    /*AÑO DE ESTRENO*/2020, 
                                    /*EDAD APTA*/16, 
                                    /*SINOPSIS*/"madre decide usar su humor y su optimismo, "
                                            + "\ny escribe un libro para que su hijo de cuatro años la recuerde por siempre.", 
                                    /*CAST*/elenco7, 
                                    /*ESTADO DE VISUALIZACION*/false, 
                                    /*DURACION*/84, 
                                    /*MINUTOS REPRODUCIDOS*/0,
                                    /*CODIGO*/8,"src\\Imagenes\\Peliculas\\El cuaderno de Tomy.jpg", "src\\Video\\Peliculas\\El cuaderno de Tomy.mp4");
        
        Media.Multimedia.catalogoPeliculas.agregarPelicula(p8);
        
        //INSTANCIAMOS LA PELICULA "El proyecto de la bruja de Blair"
        String[] elenco9 ={ "HEATHER DONAHUE","MICHAEL C. WILLIAMS","JOSHUA LEONARD","PATRICIA DECOU"
                            ,"BOB GRIFFITH","JIM KING","SANDRA SANCHEZ","ED SWANSON"};
        Pelicula p9 = new Pelicula( /*TITULO*/"El proyecto de la bruja de Blair", 
                                    /*DIRECTOR*/"Daniel Myrick", 
                                    /*GENERO*/"TERROR", 
                                    /*AÑO DE ESTRENO*/1999, 
                                    /*EDAD APTA*/18, 
                                    /*SINOPSIS*/"Tres estudiantes de cine se pierden en un bosque habitado por una bruja, "+
                                                "durante su investigación de la leyenda.", 
                                    /*CAST*/elenco9, 
                                    /*ESTADO DE VISUALIZACION*/false, 
                                    /*DURACION*/86, 
                                    /*MINUTOS REPRODUCIDOS*/0,
                                    /*CODIGO*/9,"src\\Imagenes\\Peliculas\\El proyecto de la bruja de Blair.jpg", "src\\Video\\Peliculas\\El proyecto de la bruja de Blair.mp4");
        
        Media.Multimedia.catalogoPeliculas.agregarPelicula(p9);
        
        //INSTANCIAMOS LA PELICULA "El rey león"
        String[] elenco10 ={ "Matthew Broderick","James Earl Jones","Jeremy Irons","Moira Kelly"
                            ,"Nathan Lane","Ernie Sabella","Robert Guillaume","Rowan Atkinson"};
        Pelicula p10 = new Pelicula( /*TITULO*/"El rey león", 
                                    /*DIRECTOR*/"Rob Minkoff", 
                                    /*GENERO*/"ANIMACIÓN", 
                                    /*AÑO DE ESTRENO*/1994, 
                                    /*EDAD APTA*/6, 
                                    /*SINOPSIS*/"Tras la muerte de su padre, Simba vuelve a enfrentar a su malvado tío, Scar, y reclamar el trono de rey.", 
                                    /*CAST*/elenco10, 
                                    /*ESTADO DE VISUALIZACION*/false, 
                                    /*DURACION*/46, 
                                    /*MINUTOS REPRODUCIDOS*/0,
                                    /*CODIGO*/10,"src\\Imagenes\\Peliculas\\El rey león.jpg", "src\\Video\\Peliculas\\El rey león.mp4");
        
        Media.Multimedia.catalogoPeliculas.agregarPelicula(p10);

        //INSTANCIAMOS LA PELICULA "Scary Movie"
        String[] elenco11 ={ "Anna Faris","Regina Hall","Dave Sheridan","Jon Abrahams"
                            ,"Shawn Wayans","Marlon Wayans","Shannon Elizabeth","Lochlyn Munro"};
        Pelicula p11 = new Pelicula( /*TITULO*/"Scary Movie", 
                                    /*DIRECTOR*/"Keenen Ivory Wayans", 
                                    /*GENERO*/"TERROR", 
                                    /*AÑO DE ESTRENO*/2013, 
                                    /*EDAD APTA*/18, 
                                    /*SINOPSIS*/"La saga de Scary Movie es una serie de películas estadounidenses de comedia especializada en \"burlarse\" "
                                                +"\nde las películas populares de terror, que han recaudado más de $896 millones en la taquilla en todo el mundo.", 
                                    /*CAST*/elenco11, 
                                    /*ESTADO DE VISUALIZACION*/false, 
                                    /*DURACION*/88, 
                                    /*MINUTOS REPRODUCIDOS*/0,
                                    /*CODIGO*/11,"src\\Imagenes\\Peliculas\\Scary Movie.jpg", "src\\Video\\Peliculas\\Scary Movie.mp4");
        
        Media.Multimedia.catalogoPeliculas.agregarPelicula(p11);
        
        //INSTANCIAMOS LA PELICULA "Bob Esponja: al rescate"
        String[] elenco12 ={ "Tom Kenny","Bill Fagerbakke","Rodger Bumpass","Clancy Brown"
                            ,"Carolyn Lawrence","Doug Lawrence","Keanu Reeves","Matt Berry"};
        Pelicula p12 = new Pelicula( /*TITULO*/"Bob Esponja: al rescate", 
                                    /*DIRECTOR*/"Tim Hill", 
                                    /*GENERO*/"ANIMACIÓN", 
                                    /*AÑO DE ESTRENO*/2013, 
                                    /*EDAD APTA*/12, 
                                    /*SINOPSIS*/"Bob Esponja y Patricio se embarcan en una aventura épica. Durante una misión heroica e hilarante para salvar a la mascota de Bob, "
                                                +"\nel caracol Gary, descubren que nada es más fuerte que el poder de la amistad.", 
                                    /*CAST*/elenco12, 
                                    /*ESTADO DE VISUALIZACION*/false, 
                                    /*DURACION*/103, 
                                    /*MINUTOS REPRODUCIDOS*/0,
                                    /*CODIGO*/12,"src\\Imagenes\\Peliculas\\Bob Esponja al rescate.jpg", "src\\Video\\Peliculas\\Bob Esponja - al rescate.mp4");
        
        Media.Multimedia.catalogoPeliculas.agregarPelicula(p12);
        
        //INSTANCIAMOS LA PELICULA "¡Scooby!"
        String[] elenco13 ={ "Don Messick","Casey Kasem","Frank Welker","Nicole Jaffe","Stefanianna Christopherson"};
        Pelicula p13 = new Pelicula( /*TITULO*/"¡Scooby!", 
                                    /*DIRECTOR*/"Makoto Shinkai", 
                                    /*GENERO*/"ANIMACIÓN", 
                                    /*AÑO DE ESTRENO*/2020, 
                                    /*EDAD APTA*/6, 
                                    /*SINOPSIS*/"Con cientos de casos resueltos y aventuras compartidas, Scooby y la pandilla se enfrentan al misterio más grande "
                                                +"\ny desafiante de todos los tiempos: un complot para liberar al aterrador perro fantasma Cerbero en el mundo de los vivos.", 
                                    /*CAST*/elenco13, 
                                    /*ESTADO DE VISUALIZACION*/false, 
                                    /*DURACION*/22, 
                                    /*MINUTOS REPRODUCIDOS*/0,
                                    /*CODIGO*/13,"src\\Imagenes\\Peliculas\\Scooby.jpg", "src\\Video\\Peliculas\\¡Scooby!.mp4");
        
        Media.Multimedia.catalogoPeliculas.agregarPelicula(p13);
        
        //INSTANCIAMOS LA PELICULA "Juego del miedo"
        String[] elenco14 ={ "Miyu Irino","Kana Hanazawa","Takeshi Maeda","Yuka Terasaki"
                            ,"Fumi Hirano","Mikako Komatsu","Suguru Inoue","Megumi Han"};
        Pelicula p14 = new Pelicula( /*TITULO*/"Juego del miedo", 
                                    /*DIRECTOR*/"James Wan", 
                                    /*GENERO*/"TERROR", 
                                    /*AÑO DE ESTRENO*/2004, 
                                    /*EDAD APTA*/18, 
                                    /*SINOPSIS*/"Adam y Lawrence se despiertan encadenados en un baño infecto con un cadáver entre ellos. Su secuestrador es un maniaco, cuyo juego consiste "
                                                +"\nen forzar a sus cautivos a herirse a sí mismos o a otros para permanecer vivos.", 
                                    /*CAST*/elenco14, 
                                    /*ESTADO DE VISUALIZACION*/false, 
                                    /*DURACION*/111, 
                                    /*MINUTOS REPRODUCIDOS*/0,
                                    /*CODIGO*/14,"src\\Imagenes\\Peliculas\\Juego del miedo.jpg", "src\\Video\\Peliculas\\Juego del miedo.mp4");
        
        Media.Multimedia.catalogoPeliculas.agregarPelicula(p14);
        
        //INSTANCIAMOS LA PELICULA "Juegos demoníacos"
        String[] elenco15 ={ "Jennifer Armour","Jeremy Isabella","Alina Golovlyova","Paul S. Tracey"};
        Pelicula p15 = new Pelicula( /*TITULO*/"Juegos demoníacos", 
                                    /*DIRECTOR*/"Petr Jákl", 
                                    /*GENERO*/"TERROR", 
                                    /*AÑO DE ESTRENO*/2015, 
                                    /*EDAD APTA*/18, 
                                    /*SINOPSIS*/"Tres cineastas viajan a Ucrania para investigar las historias de canibalismo que se produjeron durante la hambruna de 1932."
                                                +"\nSin embargo, la experiencia se vuelve aterradora cuando quedan a merced del espíritu del peor asesino de la historia del país.", 
                                    /*CAST*/elenco15, 
                                    /*ESTADO DE VISUALIZACION*/false, 
                                    /*DURACION*/86, 
                                    /*MINUTOS REPRODUCIDOS*/0,
                                    /*CODIGO*/15,"src\\Imagenes\\Peliculas\\Juegos demoníacos.jpg", "src\\Video\\Peliculas\\Juegos demoníacos.mp4");
        
        Media.Multimedia.catalogoPeliculas.agregarPelicula(p15);
        
        //INSTANCIAMOS LA PELICULA "Losing Alice"
        String[] elenco16 ={ "Lihi Kornowski","Ayelet Zurer","Gal Toren","Chelli Goldenberg"};
        Pelicula p16 = new Pelicula( /*TITULO*/"Losing Alice", 
                                    /*DIRECTOR*/"Makoto Shinkai", 
                                    /*GENERO*/"DRAMA", 
                                    /*AÑO DE ESTRENO*/2021, 
                                    /*EDAD APTA*/18, 
                                    /*SINOPSIS*/"Se trata de Losing Alice, donde la veterana directora Sigal Avin nos pone en medio de la discordia de pareja entre "
                                                +"\nAlice (Ayelet Zurer) y su esposo David (Gal Toren), ambos seducidos por la personalidad y el talento de una joven guionista llamada Sophie (Lihi Kornowski).", 
                                    /*CAST*/elenco16, 
                                    /*ESTADO DE VISUALIZACION*/false, 
                                    /*DURACION*/60, 
                                    /*MINUTOS REPRODUCIDOS*/0,
                                    /*CODIGO*/16,"src\\Imagenes\\Peliculas\\Losing Alice.jpg", "src\\Video\\Peliculas\\Losing Alice.mp4");
        
        Media.Multimedia.catalogoPeliculas.agregarPelicula(p16);
        
        //INSTANCIAMOS LA PELICULA "Siempre a tu lado"
        String[] elenco17 ={ "Richard Gere","Joan Allen","Cary-Hiroyuki Tagawa","Sarah Roemer"
                            ,"Jason Alexander","Erick Avari","Davenia McFadden","Kevin DeCoste"};
        Pelicula p17 = new Pelicula( /*TITULO*/"Siempre a tu lado", 
                                    /*DIRECTOR*/"Lasse Hallström", 
                                    /*GENERO*/"DRAMA", 
                                    /*AÑO DE ESTRENO*/2009, 
                                    /*EDAD APTA*/12, 
                                    /*SINOPSIS*/"Un perro fiel llamado Hachiko acompaña cada mañana a su amo a la estación de tren "
                                                + "y regresa cada tarde para darle la bienvenida después del trabajo. Sin embargo, esta rutina se ve rota por una desgracia.", 
                                    /*CAST*/elenco17, 
                                    /*ESTADO DE VISUALIZACION*/false, 
                                    /*DURACION*/93, 
                                    /*MINUTOS REPRODUCIDOS*/0,
                                    /*CODIGO*/17,"src\\Imagenes\\Peliculas\\Siempre a tu lado.jpg", "src\\Video\\Peliculas\\Siempre a tu lado.mp4");
        
        Media.Multimedia.catalogoPeliculas.agregarPelicula(p17);
        
        //INSTANCIAMOS LA PELICULA "En busca de la felicidad"
        String[] elenco18 ={ "Will Smith","Jaden Smith","Thandie Newton","Brian Howe"
                            ,"Dan Castellaneta","James Karen","Kurt Fuller","Takayo Fischer"};
        Pelicula p18 = new Pelicula( /*TITULO*/"En busca de la felicidad", 
                                    /*DIRECTOR*/"Gabriele Muccino", 
                                    /*GENERO*/"DRAMA", 
                                    /*AÑO DE ESTRENO*/2013, 
                                    /*EDAD APTA*/12, 
                                    /*SINOPSIS*/"La vida es una lucha para Chris Gardner. Expulsado de su apartamento, él y su joven hijo se encuentran solos sin ningún lugar a donde ir. "
                                            + "A pesar de que Chris eventualmente consigue trabajo como interno en una prestigiada firma financiera, "
                                            + "la posición no le da dinero. El dúo debe vivir en un albergue y enfrentar muchas dificultades, "
                                            + "pero Chris no se da por vencido y lucha por conseguir una vida mejor para él y su hijo.", 
                                    /*CAST*/elenco18, 
                                    /*ESTADO DE VISUALIZACION*/false, 
                                    /*DURACION*/117, 
                                    /*MINUTOS REPRODUCIDOS*/0,
                                    /*CODIGO*/18,"src\\Imagenes\\Peliculas\\En busca de la felicidad.jpg", "src\\Video\\Peliculas\\En busca de la felicidad.mp4");
        
        Media.Multimedia.catalogoPeliculas.agregarPelicula(p18);
        
        //INSTANCIAMOS LA PELICULA "Forrest Gump"
        String[] elenco19 ={ "Tom Hanks","Robin Wright","Gary Sinise","Sally Field"
                            ,"Haley Joel Osment","Peter Dobson","Dick Cavett","Sam Anderson"};
        Pelicula p19 = new Pelicula( /*TITULO*/"Forrest Gump", 
                                    /*DIRECTOR*/"Robert Zemeckis", 
                                    /*GENERO*/"DRAMA", 
                                    /*AÑO DE ESTRENO*/2013, 
                                    /*EDAD APTA*/12, 
                                    /*SINOPSIS*/"Forrest Gump, un joven sureño, tenaz e inocente, "
                                                +"es protagonista de acontecimientos cruciales en la historia de los Estados Unidos.", 
                                    /*CAST*/elenco18, 
                                    /*ESTADO DE VISUALIZACION*/false, 
                                    /*DURACION*/142, 
                                    /*MINUTOS REPRODUCIDOS*/0,
                                    /*CODIGO*/19,"src\\Imagenes\\Peliculas\\Forrest Gump.jpg", "src\\Video\\Peliculas\\Forrest Gump.mp4");
        
        Media.Multimedia.catalogoPeliculas.agregarPelicula(p19);
        
        //INSTANCIAMOS LA PELICULA "5 centímetros por segundo"
        String[] elenco20 ={ "Kenji Mizuhashi","Yoshimi Kondō","Satomi Hanamura"};
        Pelicula p20 = new Pelicula( /*TITULO*/"5 centímetros por segundo", 
                                    /*DIRECTOR*/"Makoto Shinkai", 
                                    /*GENERO*/"ANIMACIÓN", 
                                    /*AÑO DE ESTRENO*/2007, 
                                    /*EDAD APTA*/12, 
                                    /*SINOPSIS*/"5 centímetros por segundo​​ es una película de animación japonesa dirigida por Makoto Shinkai. La película se finalizó el 22 de enero de 2007. "
                                                +"La primera parte de la película se estrenó en Yahoo! Japón para miembros exclusivos desde el 16 al 19 de febrero de 2007.", 
                                    /*CAST*/elenco19, 
                                    /*ESTADO DE VISUALIZACION*/false, 
                                    /*DURACION*/64, 
                                    /*MINUTOS REPRODUCIDOS*/0,
                                    /*CODIGO*/20,"src\\Imagenes\\Peliculas\\5 centímetros por segundo.jpg", "src\\Video\\Peliculas\\5 centímetros por segundo.mp4");
        
        Media.Multimedia.catalogoPeliculas.agregarPelicula(p20);
        
        //INSTANCIAMOS LA PELICULA "Pesadilla en Elm Street"
        String[] elenco21 ={ "Heather Langenkamp","John Saxon","Ronee Blakley","Amanda Wyss"
                            ,"Jsu García","Johnny Depp","Charles Fleischer","Robert Englund"};
        Pelicula p21 = new Pelicula( /*TITULO*/"Pesadilla en Elm Street", 
                                    /*DIRECTOR*/"Wes Craven", 
                                    /*GENERO*/"TERROR", 
                                    /*AÑO DE ESTRENO*/	1984, 
                                    /*EDAD APTA*/18, 
                                    /*SINOPSIS*/"Un grupo de adolescentes sufre unas pesadillas horrendas en las que un ser deforme que porta garras de acero los persigue. "
                                                +"Lo más inquietante es que los hechos empiezan a sugerir que lo que ocurre mientras sueñan repercute en la vida real.", 
                                    /*CAST*/elenco21, 
                                    /*ESTADO DE VISUALIZACION*/false, 
                                    /*DURACION*/91, 
                                    /*MINUTOS REPRODUCIDOS*/0,
                                    /*CODIGO*/21,"src\\Imagenes\\Peliculas\\Pesadilla en Elm Street.jpg", "src\\Video\\Peliculas\\Pesadilla en Elm Street.mp4");
        
        Media.Multimedia.catalogoPeliculas.agregarPelicula(p21);
//        Media.Multimedia.catalogoSeries.agregarArregloSerie(new Serie[]{
//            new Serie(),new Serie(),new Serie(),new Serie(),new Serie(),new Serie()
//        });

//        Media.Multimedia.catalogoPeliculas.agregarPelicula(p21);
        
//        Media.Multimedia.catalogoPeliculas.agregarArregloPelicula(new Pelicula[]{
//        p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15,p16,p17,p18,p19,p20,p21
//        });

        //CREANDO EL CATALOGO DE VIDEOS (SERIES + PELICULAS)
        for (Serie s : Media.Multimedia.catalogoSeries.getSeries()) {
            Video v = s;
            try {
                Multimedia.catalogoVideos.agregarVideo(v);
            } catch (NullPointerException e) {
                break;
            }
        }

        for (Pelicula p : Media.Multimedia.catalogoPeliculas.getPeliculas()) {
            Video v = p;
            try {
                Multimedia.catalogoVideos.agregarVideo(v);
            } catch (NullPointerException e) {
                break;
            }
        }
        
        //INSTANCIAMOS UNA VARAIBLE DEL TIPO FILE
        File file = new File(Archivo.archivoArregloCuentas);

        //INSTANCIAMOS LA VARIABLE ARCHIVO PARA PODER HACER USO
        //DE SERIALIZAR Y DESERIALIZAR
        Archivo archivo = new Archivo();

        //EN CASO QUE EL ARCHIVO CUENTAS NO EXISTA CREAR UNO POR DEFAULT//    
        if (!file.exists()) {
            archivo.serializar(Archivo.archivoArregloCuentas, Cuentas);
        }

        //SERIALZIANDO
        Cuentas = (ArregloDeCuentas) archivo.deserializar(Archivo.archivoArregloCuentas);

        FrmInicio fInicio = new FrmInicio();
        CtrlInicio ctrlInicio = new CtrlInicio(Cuentas, fInicio);

        ctrlInicio.init();

    }

}
