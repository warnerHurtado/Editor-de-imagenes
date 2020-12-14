package Classes;

/**
 * @author ashraf
 *
 */
public class ClassLoaderTest extends JavaClassLoader {

    public static void main(String[] args) {

        JavaClassLoader javaClassLoader = new JavaClassLoader();
        javaClassLoader.invokeClassMethod("PluginWarner", "loadFile", "C:\\Users\\warne\\Desktop\\IV_Semestre\\Lenguajes\\Proyectos\\4_Proyecto\\4°_proyecto_lenguajes_warner_hurtado-2\\resourses\\pictures\\images\\Francia.jpg");

    }

}
