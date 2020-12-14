package Classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import pkg4._proyecto_lenguajes_warner_hurtado.pkg2.Main;

/**
 *
 * @author warne
 */
public class Project {

    private String name;
    private String image;
    private String imageEdited;

    /**
     * Constructor
     *
     * @param name
     * @param image
     * @param imageEdited
     */
    public Project(String name, String image, String imageEdited) {
        this.name = name;
        this.image = image;
        this.imageEdited = imageEdited;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageEdited() {
        return imageEdited;
    }

    public void setImageEdited(String imageEdited) {
        this.imageEdited = imageEdited;
    }

    /**
     * metodo para crear proyectos
     *
     * @param name
     * @param image
     * @param imageEdited
     */
    public static void createProject(String name, String image, String imageEdited) {

        Project i = new Project(name, image, imageEdited);
        Main.projects.add(i);

        JOptionPane.showMessageDialog(null, "Imagen editada con exito!", "Inane custom dialog", JOptionPane.INFORMATION_MESSAGE);
    }

    //Metodo para crear y escribir en archivos
    /**
     *
     * @param nameFile
     * @param newData
     */
    public static void writeFile(String nameFile, String newData) {

        File file;
        FileWriter write;
        PrintWriter line;

        file = new File(nameFile + ".txt");

        if (!file.exists()) {
            try {
                file.createNewFile();
                if (newData == null) {
                    return;
                }
            } catch (IOException ex) {
                Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            write = new FileWriter(file, true);
            line = new PrintWriter(write);

            line.println(newData);
            line.close();
            write.close();

        } catch (IOException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void readFile() {

        File file;
        FileReader read;
        BufferedReader storage;
        String chain;
        String pathG = "resourses\\pictures\\edited_images\\";

        file = new File("projects.txt");

        try {
            read = new FileReader(file);
            storage = new BufferedReader(read);
            chain = "";
            while (chain != null) {

                try {
                    chain = storage.readLine();
                    if (chain == null) {
                        storage.close();
                        read.close();
                        return;
                    } else {

                        Project i = new Project(chain, pathG.concat(storage.readLine()), pathG.concat(storage.readLine()));
                        Main.projects.add(i);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                storage.close();
                read.close();

            } catch (IOException ex) {
                Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No tienes proyectos en memoria!", "Inane custom dialog", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
