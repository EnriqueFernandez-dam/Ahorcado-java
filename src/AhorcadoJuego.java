
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author alumnotd
 */
public class AhorcadoJuego {

    // Atributos
    private String palabraSorteada;
    private Set<String> letrasElegidas;

    private int fallos = 0;
    private static final String FICHERO = "./palabras.txt";
    private static Map<Integer, String> MAPA_PALABRAS = null;
    private static final int FALLOS_MAX = 6;

    // Constructor
    public AhorcadoJuego() {
        /* cargarFichero();
        Random rd = new Random();
	int radNum = rd.nextInt(MAPA_PALABRAS.size() + 1);
	palabraSorteada = MAPA_PALABRAS.get(radNum);
	letrasElegidas = new TreeSet<>();
         */
        this(FICHERO); // llamar a otro constructor
    }

    public AhorcadoJuego(String fichero) {
        cargarFichero(fichero);
        Random rd = new Random();
        int radNum = rd.nextInt(MAPA_PALABRAS.size());
        palabraSorteada = MAPA_PALABRAS.get(radNum);
        letrasElegidas = new TreeSet<>();
    }

    // Métodos
    public void cargarFichero(String fichero) {
        MAPA_PALABRAS = new HashMap<>();
        int contador = 1;
        File file = new File(fichero);

        try (BufferedReader lector = new BufferedReader(new FileReader(file))) {
            String linea = lector.readLine();
            while (linea != null) {
                MAPA_PALABRAS.put(contador, linea);
                contador++;
                linea = lector.readLine();
            }
        } catch (IOException ex) {
            //System.err.println(ex.getMessage());
            //ex.printStackTrace();
            // PALABRAS DUMMY
            MAPA_PALABRAS.put(0, "VEHICULO");
            MAPA_PALABRAS.put(1, "MANZANA");
            MAPA_PALABRAS.put(2, "EUROPA");
            MAPA_PALABRAS.put(3, "VACACIONES");
            MAPA_PALABRAS.put(4, "VERANO");
        }
    }

    public void cargarFichero() {
        cargarFichero(FICHERO);
    }

    public Set<String> letrasElegidas() {
        return letrasElegidas;
    }

    public int getFallos() {
        return this.fallos;
    }

    public String getPalabraSorteada() {
        return this.palabraSorteada;
    }

    public ArrayList<Integer> buscar(char letra) {
        ArrayList<Integer> posiciones = new ArrayList<>(); // lista vacía

        for (int i = 0; i < palabraSorteada.length(); i++) {
            if (palabraSorteada.charAt(i) == letra) {
                posiciones.add(i);
            }
        }

        return posiciones;
    }

    public boolean resolver(String propuesta) {
        if (fallos >= FALLOS_MAX) {
            return false;
        }

        /*propuesta = propuesta.toUpperCase();
        for(int i = 0; i < propuesta.length(); i++){
            letrasElegidas.add(propuesta.charAt(i));
        }*/
        if (propuesta.equalsIgnoreCase(palabraSorteada)) {
            return true;
        } else {
            fallos = FALLOS_MAX;
            return false;
        }

    }

    public boolean puedeJugar() {
        return fallos < FALLOS_MAX && !palabraSorteada.equals(palabraEnJuego());
    }

    public boolean jugarLetra(char letra) {
        /* recibe una letra y consulta si está en la palabraSorteada.
           Si está, devolverá true (representa éxito).
           Si la letra no está en la palabraSorteada, incrementará el contador
           de fallos en una unidad y devolverá false (representa fracaso).
           Tanto si está como si no, la incluirá en el conjunto de letrasElegidas. 
         */
        if (puedeJugar()) {
            
            letra = Character.toUpperCase(letra);

            if (palabraSorteada.indexOf(letra) >= 0) {
                return true;
            } else {
                fallos++;
                return false;
            }

        }
        return false;

    }

    public String palabraEnJuego() {
        /* devuelve el String de la palabra del juego con las letras que ha
           acertado y un carácter de guión en las letras que aún no ha acertado.
           Por ejemplo, si la palabraSorteada ha sido CULTURA y el conjunto de
           letrasElegidas es 'A', 'S', 'C, devolverá  C - - - - - A
         */
        String resultado = "";

        for (int i = 0; i < palabraSorteada.length(); i++) {
            String letra = Character.toString(palabraSorteada.charAt(i));

            if (letrasElegidas.contains(letra)) {
                resultado += letra + " ";
            } else {
                resultado += "_ ";
            }
        }

        return resultado.trim();
    }

    public void reset() {
        this.fallos = 0;
        this.letrasElegidas.clear();
        Random rd = new Random();
        int radNum = rd.nextInt(MAPA_PALABRAS.size());
        palabraSorteada = MAPA_PALABRAS.get(radNum);
    }

    
}