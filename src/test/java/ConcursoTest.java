import exceptions.DatabaseConnectionException;
import exceptions.LateRegistrationException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ConcursoTest {


    @Test
    public void inscribirPrimerDiaTest () throws LateRegistrationException, DatabaseConnectionException {
        //Set up
        Participante participante1 = new Participante("Mar");

        LocalDate fechaInicio =  LocalDate.of(2025, 03, 23);
        LocalDate fechaFin = LocalDate.of(2025, 03, 28);
        LocalDate fechaActual =  LocalDate.of(2025, 03, 23);

        RegistroInscripcion registro = new RegistroInscripcionFake();

        Concurso concurso = new Concurso("Nuevo concurso", fechaInicio, fechaFin, registro);

        //Exercise
        concurso.inscribirParticipante(participante1, fechaActual);     //ACA YA TIENE EL REGISTRO FAKE ASIGNADO

        //Verify
        //Cambio la cantidad de elementos de la lista/ se agrego el participante? -----> EN MEMORIA
        assertEquals(1, concurso.getListaInscriptos().size(), "El número actual de participantes no es el esperado.");
        //Se encuentra en la lista? ----> EN MEMORIA
        assertTrue(concurso.getListaInscriptos().contains(participante1), "El participante no se encuentra en la lista.");
        //Sumo los puntos acumulados? -----> EN MEMORIA
        assertEquals(10, participante1.getPuntosAcumulados(), "Los puntos no se pudieron agregar correctamente.");


        //Se grabo la inscripcion correctamente en el fake? ----> PARA ARCHIVOS O BD
        assertTrue(((RegistroInscripcionFake) registro).seRegistro(), "La inscripción no fue registrada.");
        //EL CAST ES PARA PODER ACCEDER AL METODO DEL FAKE, DOWNCASTING (estoy especificando mas)

        //Solo imprime la lista del fake
        System.out.println(((RegistroInscripcionFake) registro).getRegistros());
    }

    //Hay un problema aca y es que no tengo control sobre la fecha actual, eso genera conflicto
    //tengo que poder hacer inyeccion de fecha --> Refactorizando Concurso
    //Una forma --> que se reciba la fecha actual como parametro (¿?)




    @Test
    public void inscribirSinSumarPuntosTest () throws LateRegistrationException, DatabaseConnectionException {
        //Set up
        Participante participante1 = new Participante("Mar");

        LocalDate fechaInicio =  LocalDate.of(2025, 03, 20);
        LocalDate fechaFin = LocalDate.of(2025, 03, 28);
        LocalDate fechaActual=  LocalDate.of(2025, 03, 23);

        RegistroInscripcion registro = new RegistroInscripcionFake();

        Concurso concurso = new Concurso("Nuevo concurso", fechaInicio, fechaFin, registro);

        //Exercise
        concurso.inscribirParticipante(participante1, fechaActual);

        //Verify
        //Se modifico la cantidad de participantes de la lista? -----> EN MEMORIA
        assertEquals(1, concurso.getListaInscriptos().size(), "El número actual de participantes no es el esperado.");
        //Se encuentra en la lista? ---> EN MEMORIA
        assertTrue(concurso.getListaInscriptos().contains(participante1), "El participante no se encuentra en la lista.");


        //Se grabo la inscripcion correctamente en el fake? ----> PARA ARCHIVOS O BD
        assertTrue(((RegistroInscripcionFake) registro).seRegistro(), "La inscripción no fue registrada."); //DOWNCASTING

        //Solo imprime la lista del fake
        System.out.println(((RegistroInscripcionFake) registro).getRegistros());
    }

    @Test
    public void inscribirFueraDeFechaTest () throws LateRegistrationException{
        //Set up
        Participante participante1 = new Participante("Mar");

        LocalDate fechaInicio =  LocalDate.of(2025, 03, 20);
        LocalDate fechaFin = LocalDate.of(2025, 03, 28);
        LocalDate fechaActual=  LocalDate.of(2025, 03, 29);

        RegistroInscripcion registro = new RegistroInscripcionFake();

        Concurso concurso = new Concurso("Nuevo concurso", fechaInicio, fechaFin, registro);

        //Verify
        assertThrows(LateRegistrationException.class, () -> {
            //Exercise
            concurso.inscribirParticipante(participante1, fechaActual);
        });


        //jaja esto esta mal aca no va
        //assertTrue(((RegistroInscripcionFake) registro).seRegistro(), "La inscripción no fue registrada.");


    }

    @Test
    public void notificarTest (){
        //Set up
        String emisor = "your.recipient@email.com";
        String destinatario = "john.doe@your.domain";
        String asunto = "Inscripción";
        String cuerpo = "Usted se encuentra inscripto en el concurso.";

        Notificador notificador = new NotificadorFake();           //INSTANCIO EL NOTIFICADOR

        //Exercise
        notificador.notificar(emisor, destinatario, asunto, cuerpo);

        //Verify
        assertTrue(((NotificadorFake) notificador).seNotifico(), "La inscripción no fue registrada.");


        //Solo imprime la lista del notificador fake por consola
        System.out.println(((NotificadorFake) notificador).getMensajesEnviados());
    }

}
