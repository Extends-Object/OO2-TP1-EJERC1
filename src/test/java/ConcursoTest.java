import exceptions.LateRegistrationException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ConcursoTest {

    @Test
    public void agregarParticipanteTest (){

        //Set up
        Participante participante1 = new Participante("Mar");
        Participante participante2 = new Participante("Rodrigo");
        Participante participante3 = new Participante("Magin");

        LocalDate fechaInicio =  LocalDate.of(2025, 03, 15);
        LocalDate fechaFin = LocalDate.of(2025, 03, 28);

        Concurso concurso = new Concurso("Nuevo concurso", fechaInicio, fechaFin);

        //Exercise
        concurso.agregarParicipante(participante1);
        concurso.agregarParicipante(participante2);
        concurso.agregarParicipante(participante3);

        //Verify
        assertEquals(3, concurso.getListaInscriptos().size(), "El número de participantes no es correcto.");

        assertTrue(concurso.getListaInscriptos().contains(participante1), "El participante1 no está inscrito.");
        assertTrue(concurso.getListaInscriptos().contains(participante2), "El participante2 no está inscrito.");
        assertTrue(concurso.getListaInscriptos().contains(participante3), "El participante3 no está inscrito.");
    }

    @Test
    public void inscribirPrimerDiaTest () throws LateRegistrationException {
        //Set up
        Participante participante1 = new Participante("Mar");

        LocalDate fechaInicio =  LocalDate.of(2025, 03, 23);
        LocalDate fechaFin = LocalDate.of(2025, 03, 28);
        LocalDate fechaActual =  LocalDate.of(2025, 03, 23);

        Concurso concurso = new Concurso("Nuevo concurso", fechaInicio, fechaFin);

        //Exercise
        concurso.inscribirParticipante(participante1, fechaActual);

        //Verify
        assertEquals(1, concurso.getListaInscriptos().size(), "El número actual de participantes no es el esperado.");
        assertTrue(concurso.getListaInscriptos().contains(participante1), "El participante no se encuentra en la lista.");
        assertEquals(10, participante1.getPuntosAcumulados(), "Los puntos no se pudieron agregar correctamente.");
    }

    //Hay un problema aca y es que no tengo control sobre la fecha actual, eso genera conflicto
    //tengo que poder hacer inyeccion de fecha --> Refactorizando Concurso
    //Una forma --> que se reciba la fecha actual como parametro (¿?)

    @Test
    public void inscribirSinSumarPuntosTest () throws LateRegistrationException{
        //Set up
        Participante participante1 = new Participante("Mar");

        LocalDate fechaInicio =  LocalDate.of(2025, 03, 20);
        LocalDate fechaFin = LocalDate.of(2025, 03, 28);
        LocalDate fechaActual=  LocalDate.of(2025, 03, 23);

        Concurso concurso = new Concurso("Nuevo concurso", fechaInicio, fechaFin);

        //Exercise
        concurso.inscribirParticipante(participante1, fechaActual);

        //Verify
        assertEquals(1, concurso.getListaInscriptos().size(), "El número actual de participantes no es el esperado.");
        assertTrue(concurso.getListaInscriptos().contains(participante1), "El participante no se encuentra en la lista.");
    }

    @Test
    public void inscribirFueraDeFechaTest () throws LateRegistrationException{
        //Set up
        Participante participante1 = new Participante("Mar");

        LocalDate fechaInicio =  LocalDate.of(2025, 03, 20);
        LocalDate fechaFin = LocalDate.of(2025, 03, 28);
        LocalDate fechaActual=  LocalDate.of(2025, 03, 29);

        Concurso concurso = new Concurso("Nuevo concurso", fechaInicio, fechaFin);

        //Verify
        assertThrows(LateRegistrationException.class, () -> {
            //Exercise
            concurso.inscribirParticipante(participante1, fechaActual);
        });
    }

}
