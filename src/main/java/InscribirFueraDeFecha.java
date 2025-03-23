import exceptions.LateRegistrationException;

public class InscribirFueraDeFecha implements Inscripcion{

    @Override
    public void inscribir(Participante participante, Concurso concurso) throws LateRegistrationException {
        System.out.println("No se puede inscribir. Motivo: inscripción fuera de término.");
        throw new LateRegistrationException("No se puede inscribir. Motivo: inscripción fuera de término.");
    }
}
