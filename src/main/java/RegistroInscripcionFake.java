import persistencia.RegistroInscripcion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistroInscripcionFake implements RegistroInscripcion {

    private final List<String> registros = new ArrayList<>();


    @Override
    public void registrarInscripcion(LocalDate fecha, Participante participante, Concurso concurso) {
        registros.add("Fecha: " + fecha.toString() + ", ID Participante: " + participante.getId() + ", puntos acumulados: " + participante.getPuntosAcumulados() + ", ID Concurso: " + concurso.getId());
    }


    // Simula el comportamiento esperado (guardar una inscripción) sin escribir en
    // archivo ni conectarse a la base de datos. En los tests lo único que importa es
    // que la funcionalidad se llame correctamente y que la lógica de negocio funcione,
    // no que se efectúe el almacenamiento real.


    public boolean seRegistro() {
        return !registros.isEmpty();
    }


    //SOLO PARA IMPRIMIR LA LISTA POR CONSOLA Y SABER SI SE GUARDO CORRECTAMENTE LA INSCRIPCION
    public List<String> getRegistros() {
        return registros;
    }
}