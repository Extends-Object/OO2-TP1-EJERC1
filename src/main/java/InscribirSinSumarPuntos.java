import java.time.LocalDate;

public class InscribirSinSumarPuntos implements Inscripcion{

    @Override
    public void inscribir(Participante participante, Concurso concurso) {
        concurso.agregarParicipante(participante);
        System.out.println("Se inscribió el participante sin sumar puntos.");
    }
}
