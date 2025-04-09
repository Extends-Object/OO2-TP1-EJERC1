import java.time.LocalDate;

public class RegistroInscripcionFake implements RegistroInscripcion {
    public boolean fueLlamado = false;
    public LocalDate fechaRecibida;
    public Participante participanteRecibido;
    public Concurso concursoRecibido;

    @Override
    public void registrarInscripcion(LocalDate fecha, Participante participante, Concurso concurso) {
        this.fueLlamado = true;
        this.fechaRecibida = fecha;
        this.participanteRecibido = participante;
        this.concursoRecibido = concurso;
    }
}