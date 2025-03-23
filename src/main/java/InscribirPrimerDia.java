import java.time.LocalDate;

public class InscribirPrimerDia implements Inscripcion{

    @Override
    public void inscribir(Participante participante, Concurso concurso) {
        participante.sumarPuntos(10);
        concurso.agregarParicipante(participante);
        System.out.println("Se inscribió el participante el primer día de inscripción.");
    }
}
