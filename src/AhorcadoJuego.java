
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
public class AhorcadoJuego 
{
    // Atributos
    private String palabraSorteada;
    private Set<String> letrasElegidas;
    
    private int fallos = 0;
    private static final String FICHERO = "palabras.txt";
    private static Map<Integer,String> MAPA_PALABRAS = null;
    private static final int FALLOS_MAX = 6;
    
    
    // Constructor
    
    public AhorcadoJuego()
    {
        /* cargarFichero();
        Random rd = new Random();
	int radNum = rd.nextInt(MAPA_PALABRAS.size() + 1);
	palabraSorteada = MAPA_PALABRAS.get(radNum);
	letrasElegidas = new TreeSet<>();
	*/
	this(FICHERO);
    }
    public AhorcadoJuego(String fichero)
    {
        cargarFichero(fichero);
        Random rd = new Random();
	int radNum = rd.nextInt(MAPA_PALABRAS.size());
	palabraSorteada = MAPA_PALABRAS.get(radNum);
	letrasElegidas = new TreeSet<>();
    }
    
    // Métodos
    
    public void cargarFichero(String fichero)
    {
        MAPA_PALABRAS = new HashMap<>();
        int contador = 1;
            File file = new File(fichero);
        try (BufferedReader lector = new BufferedReader(new FileReader(file)))
        {
            String linea = lector.readLine();
            while (linea != null)
            {
                MAPA_PALABRAS.put(contador, linea);
                contador++;
                linea = lector.readLine();
            }
        } 
        catch (IOException ex) 
        {
            // PALABRAS DUMMY
            MAPA_PALABRAS.put(0,"VEHICULO");
            MAPA_PALABRAS.put(1,"MANZANA");
            MAPA_PALABRAS.put(2,"EUROPA");
            MAPA_PALABRAS.put(3,"VACACIONES");
            MAPA_PALABRAS.put(4,"VERANO");
        } 
    }

    public void cargarFichero()
    {
        cargarFichero(FICHERO);
    }
    
    public ArrayList<Integer> buscar(char letra)
    {
	if (letra >= 'a' && letra <= 'z') letra -= 40;
	ArrayList<Integer> posiciones = new ArrayList<>();
	for (int i = 0; i < palabraSorteada.length(); i++)
	{
		if (palabraSorteada.charAt(i) == letra) posiciones.add(i);
	}
	return posiciones;
    }

    public boolean resolver(String propuesta)
    {
	    if (!propuesta.equalsIgnoreCase(palabraSorteada))
	    {
		    fallos = FALLOS_MAX;
		    return false;
		    // comentario
	    }  
	    else
	    {
		    return true;
	    }
    }

    public void reset()
    {
	Random rd = new Random();
	int radNum = rd.nextInt(MAPA_PALABRAS.size());
	palabraSorteada = MAPA_PALABRAS.get(radNum);
    }
}
