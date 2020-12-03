/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4._proyecto_lenguajes_warner_hurtado.pkg2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author warne
 */
public class Main {

    /**
     * example gotten of: http://www.chuidiang.org/java/ejemplos/Runtime/runtime.php
     * funtion for execute .exe of C
     */
    public static void Execute(){
                System.err.println("running .exe");
    //Runtime app = Runtime.getRuntime();
    try{
        /*app.exec("C:\\Users\\warne\\Desktop\\IV_Semestre\\Lenguajes\\Proyectos\\"
                + "lenguagesProyect\\cmake-build-debug\\untitled2.exe Francia.jpg"
                + " nuevaFrancia ejecutando prueba ARIALNEGRITA 28");*/
        Process p = Runtime.getRuntime().exec ("C:\\Users\\warne\\Desktop\\IV_Semestre\\Lenguajes\\Proyectos\\"
                + "4_Proyecto\\resourses\\executable\\imageEdit.exe Francia.jpg"
                + " nuevaFrancia ejecutando prueba ARIALNEGRITA 28");
        
                    // Se obtiene el stream de salida del programa
        InputStream is = p.getInputStream();
        /* Se prepara un bufferedReader para poder leer la salida más comodamente. */
            BufferedReader br = new BufferedReader (new InputStreamReader (is));
            
            // Se lee la primera linea
            String aux = br.readLine();
            
            // Mientras se haya leido alguna linea
            while (aux!=null)
            {
                // Se escribe la linea en pantalla
                System.out.println (aux);
                
                // y se lee la siguiente.
                aux = br.readLine();
            }
    }
    catch(IOException e){
        System.out.println("Hay un error. "+ e);
    }
    }
    
    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Execute();
    }
    
}
