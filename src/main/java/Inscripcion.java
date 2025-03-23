import exceptions.LateRegistrationException;

public interface Inscripcion {

    public void inscribir(Participante participante, Concurso concurso) throws LateRegistrationException;
}
