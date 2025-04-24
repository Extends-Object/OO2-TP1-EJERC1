package persistencia;

import exceptions.DatabaseConnectionException;

import java.time.LocalDate;

public interface RegistroInscripcion {

    public void registrarInscripcion(LocalDate fecha, Participante participante, Concurso concurso) throws DatabaseConnectionException;
}
