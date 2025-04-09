import java.io.*;

public class ManejoArchivos {

    //************************* CLASE PARA REPASO DE CONCEPTOS  - MANEJO DE ARCHIVOS ***********************************

    public static void crearArchivo(String nombreArchivo){

        File archivo = new File(nombreArchivo);

        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.close();
            System.out.println("Se creó el archivo correctamente.");
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }

    }

    //******************************************************************************************************************
    public static void escribirArchivo(String nombreArchivo, String contenido){

        File archivo = new File(nombreArchivo);

        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.println(contenido);
            salida.close();
            System.out.println("Se escribió en el archivo correctamente.");
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }

    }

    //******************************************************************************************************************
    public static void actualizarArchivo(String nombreArchivo, String contenido){

        File archivo = new File(nombreArchivo);

        try {
            PrintWriter salida = new PrintWriter(new FileWriter(archivo, true));
            salida.println(contenido);
            salida.close();
            System.out.println("Se actualizó en el archivo correctamente.");
        } catch (FileNotFoundException e) {
            System.out.println("Error: No se encontró el archivo");
        } catch (IOException e) {
            System.out.println("Error: no se pudo actualizar el archivo");
        }

    }

    //******************************************************************************************************************
    public static void leerArchivo(String nombreArchivo){

        File archivo = new File(nombreArchivo);

        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String lectura = entrada.readLine();
            while(lectura != null) {
                System.out.println(lectura);
                lectura = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: No se encontró el archivo");
        } catch (IOException e) {
            System.out.println("Error: no se pudo leer el archivo");
        }

    }

    //******************************************************************************************************************
    public static void borrarArchivo(String nombreArchivo){

        File archivo = new File(nombreArchivo);

        if(archivo.exists()) {
            archivo.delete();
            System.out.println("Se eliminó correctamente el archivo");
        } else {
            System.out.println("No existe el archivo para eliminar");
        }
    }

    //******************************************************************************************************************

}
