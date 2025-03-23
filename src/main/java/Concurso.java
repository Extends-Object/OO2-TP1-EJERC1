import exceptions.LateRegistrationException;

import java.time.LocalDate;
import java.util.ArrayList;

public class Concurso {
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private ArrayList<Participante> listaInscriptos;
    private Inscripcion inscribir;

    public Concurso(String nombre, LocalDate fechaInicio, LocalDate fechaFin) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.listaInscriptos = new ArrayList<>();
    }


    //Sin polimorfismo
    /*
    public void inscribirParticipante (Participante participante){
        LocalDate fechaActual = LocalDate.now();

        if(fechaActual.isBefore(fechaInicio) || fechaActual.isAfter(fechaFin)){
            System.out.println("No se puede inscribir");
        } else {
            if (fechaActual.isEqual(fechaInicio)) {
                participante.sumarPuntos(10);
            }
            this.listaInscriptos.add(participante);
            System.out.println("Se ha inscripto correctamente");
        }
    }
    */


    public void inscribirParticipante (Participante participante, LocalDate fechaActual) throws LateRegistrationException {
        //LocalDate fechaActual = LocalDate.now();

        if(fechaActual.isBefore(fechaInicio) || fechaActual.isAfter(fechaFin)){
            inscribir = new InscribirFueraDeFecha();
        } else if (fechaActual.isEqual(fechaInicio)){
            inscribir = new InscribirPrimerDia();
        } else {
            inscribir = new InscribirSinSumarPuntos();
        }

        inscribir.inscribir(participante, this);
    }

    public void agregarParicipante (Participante participante){
        listaInscriptos.add(participante);
    }

    public ArrayList<Participante> getListaInscriptos() {
        return listaInscriptos;
    }
}
