import exceptions.DatabaseConnectionException;
import persistencia.ConnectionManager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class InscripcionBaseDatos implements RegistroInscripcion{

    @Override
    public void registrarInscripcion(LocalDate fecha, Participante participante, Concurso concurso) throws DatabaseConnectionException {

            try {
                Connection conn = ConnectionManager.getConnection();
                PreparedStatement statement = conn.prepareStatement(
                        "INSERT INTO inscripciones (fecha, id_participante, puntos_acumulados, id_concurso) " + "VALUES (?, ?, ?, ?)");

                statement.setDate(1, Date.valueOf(fecha));
                statement.setInt(2, participante.getId());
                statement.setInt(3, participante.getPuntosAcumulados());
                statement.setInt(4, concurso.getId());

                int cantidad = statement.executeUpdate(); // Retorna un entero la cantidad de filas que afectó

                if (cantidad == 0) {
                    System.out.println("Error al insertar el registro.");
                }

            } catch (SQLException e) {
                System.out.println("Error al procesar la sentencia.");
                throw new DatabaseConnectionException("Error en la conexión: " + e.getMessage());
            } finally {
                ConnectionManager.disconnect();
            }
    }
}
