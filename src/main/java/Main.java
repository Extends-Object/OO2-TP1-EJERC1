import exceptions.DatabaseConnectionException;
import exceptions.LateRegistrationException;
import modelo.Concurso;
import modelo.Notificador;
import modelo.NotificadorEmail;
import modelo.Participante;
import persistencia.InscripcionArchivo;
import persistencia.InscripcionBaseDatos;
import persistencia.RegistroInscripcion;

import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        // Creé una abstraccion mas para poder separar el registro en archivos del registro en BD


        //************************************** Crear la informacion del concurso *************************************
        LocalDate fechaInicio =  LocalDate.of(2025, 03, 15);
        LocalDate fechaFin = LocalDate.of(2025, 03, 28);
        LocalDate fechaActual = LocalDate.of(2025, 03, 15);

        Participante participante1 = new Participante("Mar");
        Participante participante2 = new Participante("Rodrigo");


        //******************************* Registrar inscripciones en ARCHIVO ***************************

        //CREANDO EL ARCHIVO
        String rutaArchivoInscriptos = "C:\\Users\\retur\\OneDrive\\Escritorio\\UNRN\\TERCER AÑO\\PRIMER CUATRIMESTRE\\OBJETOS 2\\ArchivosJava\\Inscriptos.txt";
        RegistroInscripcion registroArchivo = new InscripcionArchivo(rutaArchivoInscriptos);

        //CREANDO EL CONCURSO
        Concurso concurso1 = new Concurso("modelo.Concurso A", fechaInicio, fechaFin, registroArchivo);

        try {
            concurso1.inscribirParticipante(participante1, fechaActual);
            concurso1.inscribirParticipante(participante2, fechaActual);

        } catch (LateRegistrationException e) {
            System.out.println(e.getMessage());
        } catch (DatabaseConnectionException e) {
            System.out.println(e.getMessage());
        }


        //------------------------- Registrar inscripciones en BASE DE DATOS ---------------------------------------------
        RegistroInscripcion inscriptosBD = new InscripcionBaseDatos();

        Concurso concurso2 = new Concurso("modelo.Concurso B", fechaInicio, fechaFin, inscriptosBD);

        Participante participante3 = new Participante("Pepe");
        Participante participante4 = new Participante("Pepa");

        try {
            concurso2.inscribirParticipante(participante3, fechaActual);
            concurso2.inscribirParticipante(participante4, fechaActual);

        } catch (LateRegistrationException e) {
            System.out.println(e.getMessage());
        } catch (DatabaseConnectionException e) {
            System.out.println(e.getMessage());
        }

        //****************************** ENVIO DE EMAIL ************************************************************

        //NO ESTOY HACIENDO CORRECTAMENTE EL DESACOPLAMIENTO ACA

        String emisor = "your.recipient@email.com";
        String destinatario = "john.doe@your.domain";
        String asunto = "Inscripción";
        String cuerpo = "Usted se encuentra inscripto en el concurso.";

        Notificador notificador = new NotificadorEmail();           //INSTANCIO EL NOTIFICADOR

        notificador.notificar(emisor, destinatario, asunto, cuerpo);



        //**************************** PRUEBAS QUE NO TIENEN NADA QUE VER CON LAS CONSIGNAS ****************************

        //persistencia.ManejoArchivos.crearArchivo("Archivos\\ArchivoPrueba.txt");

        //persistencia.ManejoArchivos.crearArchivo("C:\\Users\\retur\\OneDrive\\Escritorio\\UNRN\\TERCER AÑO\\PRIMER CUATRIMESTRE\\OBJETOS 2\\ArchivosJava\\ArchivoPrueba2.txt");

        //persistencia.ManejoArchivos.escribirArchivo("C:\\Users\\retur\\OneDrive\\Escritorio\\UNRN\\TERCER AÑO\\PRIMER CUATRIMESTRE\\OBJETOS 2\\ArchivosJava\\ArchivoPrueba2.txt", "Hola :)");

        //persistencia.ManejoArchivos.actualizarArchivo("C:\\Users\\retur\\OneDrive\\Escritorio\\UNRN\\TERCER AÑO\\PRIMER CUATRIMESTRE\\OBJETOS 2\\ArchivosJava\\ArchivoPrueba2.txt", "Holaaa :)");

        //persistencia.ManejoArchivos.actualizarArchivo("C:\\Users\\retur\\OneDrive\\Escritorio\\UNRN\\TERCER AÑO\\PRIMER CUATRIMESTRE\\OBJETOS 2\\ArchivosJava\\ArchivoPrueba2.txt", "Chauuu :)");

        //persistencia.ManejoArchivos.leerArchivo("C:\\Users\\retur\\OneDrive\\Escritorio\\UNRN\\TERCER AÑO\\PRIMER CUATRIMESTRE\\OBJETOS 2\\ArchivosJava\\ArchivoPrueba2.txt");

        //persistencia.ManejoArchivos.crearArchivo("C:\\Users\\retur\\OneDrive\\Escritorio\\UNRN\\TERCER AÑO\\PRIMER CUATRIMESTRE\\OBJETOS 2\\ArchivosJava\\ArchivoPrueba3.txt");

        //persistencia.ManejoArchivos.borrarArchivo("C:\\Users\\retur\\OneDrive\\Escritorio\\UNRN\\TERCER AÑO\\PRIMER CUATRIMESTRE\\OBJETOS 2\\ArchivosJava\\ArchivoPrueba2.txt");

        //persistencia.ManejoArchivos.borrarArchivo("C:\\Users\\retur\\OneDrive\\Escritorio\\UNRN\\TERCER AÑO\\PRIMER CUATRIMESTRE\\OBJETOS 2\\ArchivosJava\\ArchivoPrueba3.txt");
    }
}