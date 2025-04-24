import exceptions.DatabaseConnectionException;
import exceptions.LateRegistrationException;
import modelo.Concurso;
import modelo.Notificacion;
import modelo.Participante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ConcursoTest {

    private Participante participante;
    private RegistroInscripcionFake registro;
    private Concurso concurso;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private NotificadorFake notificador;
    private Notificacion notificacion;

    @BeforeEach
    public void setUp() {
        participante = new Participante("Mar");
        registro = new RegistroInscripcionFake();
        notificador = new NotificadorFake();
        fechaInicio = LocalDate.of(2025, 3, 20); // Valor base
        fechaFin = LocalDate.of(2025, 3, 28);    // Valor base
        concurso = new Concurso("Nuevo concurso", fechaInicio, fechaFin, registro, notificador);

        String emisor = "your.recipient@email.com";
        String destinatario = "john.doe@your.domain";
        String asunto = "Inscripción";
        String cuerpo = "Usted se encuentra inscripto en el concurso.";
        notificacion = new Notificacion(emisor, destinatario, asunto, cuerpo);

    }


    @Test
    public void inscribirPrimerDiaTest () throws LateRegistrationException, DatabaseConnectionException {

        LocalDate fechaActual =  LocalDate.of(2025, 03, 20);

        Concurso concurso = new Concurso("Nuevo concurso", fechaInicio, fechaFin, registro, notificador);

        //Exercise
        concurso.inscribirParticipante(participante, fechaActual, notificacion);     //ACA YA TIENE EL REGISTRO FAKE ASIGNADO

        //Verify
        //Cambio la cantidad de elementos de la lista/ se agrego el participante? -----> EN MEMORIA
        assertEquals(1, concurso.getListaInscriptos().size(), "El número actual de participantes no es el esperado.");
        //Se encuentra en la lista? ----> EN MEMORIA
        assertTrue(concurso.getListaInscriptos().contains(participante), "El participante no se encuentra en la lista.");
        //Sumo los puntos acumulados? -----> EN MEMORIA
        assertEquals(10, participante.getPuntosAcumulados(), "Los puntos no se pudieron agregar correctamente.");


        //Se grabo la inscripcion correctamente en el fake? ----> PARA ARCHIVOS O BD
        assertTrue(((RegistroInscripcionFake) registro).seRegistro(), "La inscripción no fue registrada.");
        //EL CAST ES PARA PODER ACCEDER AL METODO DEL FAKE, DOWNCASTING (estoy especificando mas)

        //Solo imprime la lista del fake
        System.out.println(((RegistroInscripcionFake) registro).getRegistros());
    }

    //Hay un problema aca y es que no tengo control sobre la fecha actual, eso genera conflicto
    //tengo que poder hacer inyeccion de fecha --> Refactorizando modelo.Concurso
    //Una forma --> que se reciba la fecha actual como parametro (¿?)




    @Test
    public void inscribirSinSumarPuntosTest () throws LateRegistrationException, DatabaseConnectionException {

        LocalDate fechaActual=  LocalDate.of(2025, 03, 23);

        Concurso concurso = new Concurso("Nuevo concurso", fechaInicio, fechaFin, registro, notificador);

        //Exercise
        concurso.inscribirParticipante(participante, fechaActual, notificacion);

        //Verify
        //Se modifico la cantidad de participantes de la lista? -----> EN MEMORIA
        assertEquals(1, concurso.getListaInscriptos().size(), "El número actual de participantes no es el esperado.");
        //Se encuentra en la lista? ---> EN MEMORIA
        assertTrue(concurso.getListaInscriptos().contains(participante), "El participante no se encuentra en la lista.");

        //Se grabo la inscripcion correctamente en el fake? ----> PARA ARCHIVOS O BD
        assertTrue(((RegistroInscripcionFake) registro).seRegistro(), "La inscripción no fue registrada."); //DOWNCASTING

        //Solo imprime la lista del fake
        System.out.println(((RegistroInscripcionFake) registro).getRegistros());
    }

    @Test
    public void inscribirFueraDeFechaTest () throws LateRegistrationException{

        LocalDate fechaAntes =  LocalDate.of(2025, 03, 18);
        LocalDate fechaDespues =  LocalDate.of(2025, 03, 29);

        Concurso concurso = new Concurso("Nuevo concurso", fechaInicio, fechaFin, registro, notificador);

        //Verify
        assertThrows(LateRegistrationException.class, () -> {
            //Exercise
            concurso.inscribirParticipante(participante, fechaAntes, notificacion);
        });

        assertThrows(LateRegistrationException.class, () -> {
            //Exercise
            concurso.inscribirParticipante(participante, fechaDespues, notificacion);
        });


        //jaja esto esta mal aca no va
        //assertTrue(((RegistroInscripcionFake) registro).seRegistro(), "La inscripción no fue registrada.");
    }

    @Test
    public void notificarTest (){

        //Exercise
        notificador.notificar(notificacion);

        //Verify
        assertTrue(((NotificadorFake) notificador).seNotifico(), "La inscripción no fue notificada.");

        //Solo imprime la lista del notificador fake por consola
        System.out.println(((NotificadorFake) notificador).getMensajesEnviados());
    }

}
