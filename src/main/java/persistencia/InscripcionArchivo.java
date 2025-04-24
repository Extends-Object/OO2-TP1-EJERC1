package persistencia;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InscripcionArchivo implements RegistroInscripcion {

    private String rutaArchivo;

    public InscripcionArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public void registrarInscripcion(LocalDate fecha, Participante participante, Concurso concurso) {

        File archivo = new File(this.rutaArchivo);

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = fecha.format(formato);

        String informacionInscripto = (fechaFormateada + ", " + participante + ", " + concurso);

        try {               //ESTO DEBERIA IR EN MAIN Y MANEJARSE CON THROWS?? NO DEBERIA HACER MANEJO DE EXEPCIONES EN LA LOGICA DE PERSISTENCIA
            PrintWriter salida = new PrintWriter(new FileWriter(archivo, true));
            salida.println(informacionInscripto);
            salida.close();
            System.out.println("Se actualizó en el archivo correctamente.");
        } catch (FileNotFoundException e) {
            System.out.println("Error: No se encontró el archivo");
        } catch (IOException e) {
            System.out.println("Error: no se pudo actualizar el archivo");
        }

    }
}
