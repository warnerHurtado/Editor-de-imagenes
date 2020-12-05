/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4._proyecto_lenguajes_warner_hurtado.pkg2;

import Classes.Project;
import Frames.mainView;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.swing.JOptionPane;

/**
 *
 * @author warne
 */
public class Main {

    public static ArrayList<Project> projects = new ArrayList();

    /**
     * metodo para ejecutar el .exe
     *
     * @param nameProject
     * @param pathImage
     * @param textAbove
     * @param textBelow
     * @param fontText
     * @param size
     */
    public static void execute(String nameProject, String pathImage, String textAbove,
            String textBelow, String fontText, String size) throws IOException {

        for (Project project : Main.projects) {
            if (project.getName().equals(nameProject)) {
                JOptionPane.showMessageDialog(null, "Ya existe un proyecto con este nombre!.", "Inane custom dialog", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        File imgObj = new File(pathImage);
        int id = projects.size();
        String name = id + imgObj.getName();
        String pathImageSave = "resourses\\\\pictures\\edited_images\\".concat(name);

        String probando = "resourses\\executable\\imageEdit.exe " + pathImage
                + " " + pathImageSave + " " + textAbove + " " + textBelow + " " + fontText + " " + size;

        System.err.println(probando);

        System.err.println("running .exe");
        /**
         * example gotten of:
         * http://www.chuidiang.org/java/ejemplos/Runtime/runtime.php funtion
         * for execute .exe of C
         */
        try {
            Process p = Runtime.getRuntime().exec("resourses\\executable\\imageEdit.exe " + pathImage
                    + " " + pathImageSave + " \"" + textAbove + "\" \"" + textBelow + "\" " + fontText + " " + size);
            // Se obtiene el stream de salida del programa
            InputStream is = p.getInputStream();
            /* Se prepara un bufferedReader para poder leer la salida más comodamente. */
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            // Se lee la primera linea
            String aux = br.readLine();

            // Mientras se haya leido alguna linea
            while (aux != null) {
                // Se escribe la linea en pantalla
                System.out.println(aux);

                if (aux.equals("Error to charge Image")) {
                }

                // instrucción switch con tipo de datos String
                switch (aux) {
                    case "Error to charge Image":
                        JOptionPane.showMessageDialog(null, aux, "Inane error", JOptionPane.ERROR_MESSAGE);
                        return;
                    case "The image is very small":
                        JOptionPane.showMessageDialog(null, aux, "Inane error", JOptionPane.ERROR_MESSAGE);
                        return;
                    case "The lengh or size of the text is very big":
                        JOptionPane.showMessageDialog(null, aux, "Inane error", JOptionPane.ERROR_MESSAGE);
                        return;
                    case "Error to create font":
                        JOptionPane.showMessageDialog(null, aux, "Inane error", JOptionPane.ERROR_MESSAGE);
                        return;
                    default:
                        break;
                }
                // y se lee la siguiente.
                aux = br.readLine();
            }
            Runtime.runFinalizersOnExit(true);
        } catch (IOException e) {
            System.err.println("Hay un error. " + e);
        }
        Path origenPath = Paths.get(pathImage);
        String pathNewImg = "resourses\\pictures\\edited_images\\".concat(imgObj.getName());
        Path destinoPath = Paths.get(pathNewImg);
        try {
            Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (FileNotFoundException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
        Project.createProject(nameProject, pathNewImg, pathImageSave);
        Project.writeFile("projects", nameProject);
        Project.writeFile("projects", imgObj.getName());
        Project.writeFile("projects", name);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        //Leyendo los datos guardados
        Project.readFile();
        //Llamando la ventana principal
        mainView p = new mainView();
        p.show();
    }

}
