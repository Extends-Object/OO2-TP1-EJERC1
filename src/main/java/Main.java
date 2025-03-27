import exceptions.LateRegistrationException;

import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        LocalDate fechaInicio =  LocalDate.of(2025, 03, 15);
        LocalDate fechaFin = LocalDate.of(2025, 03, 28);
        LocalDate fechaActual = LocalDate.of(2025, 03, 19);

        Concurso concurso = new Concurso("Nuevo concurso", fechaInicio, fechaFin);
        Participante participante = new Participante("Mar");

        try {
            concurso.inscribirParticipante(participante, fechaActual);
        } catch (LateRegistrationException e) {
            System.out.println(e.getMessage());
        }

    }
}