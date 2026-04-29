
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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
    
    private static int fallos;
    private static final String FICHERO = "palabras.txt";
    private static Map<Integer,String> MAPA_PALABRAS = null;
    private static final int FALLOS_MAX = 0;
    
    
    // Constructor
    
    public AhorcadoJuego()
    {
        cargarFichero();
        Random rd = new Random();
    }
    public AhorcadoJuego(String fichero)
    {
        cargarFichero(fichero);
    }
    
    // Métodos
    
    public void cargarFichero(String fichero)
    {
        MAPA_PALABRAS = new HashMap<>();
        int contador = 1;
        BufferedReader lector = null;
        try 
        {
            File file = new File(fichero);
            lector = new BufferedReader(new FileReader(file));
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
            MAPA_PALABRAS.put(1,"VEHICULO");
            MAPA_PALABRAS.put(2,"MANZANA");
            MAPA_PALABRAS.put(3,"EUROPA");
            MAPA_PALABRAS.put(4,"VACACIONES");
            MAPA_PALABRAS.put(5,"VERANO");
        } 
        finally 
        {
            try 
            {
                lector.close();
            } 
            catch (IOException ex) 
            {
                //System.getLogger(AhorcadoJuego.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
        
    }
    public void cargarFichero()
    {
        cargarFichero(FICHERO);
    }
    
}
