import exceptions.DatabaseConnectionException;
import exceptions.LateRegistrationException;

import java.time.LocalDate;
import java.util.ArrayList;

public class Concurso {

    //ATRIBUTO DE CLASE
    private static int contador = 1;

    //ATRIBUTOS DE INSTANCIA
    private final int id;

    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    private ArrayList<Participante> listaInscriptos;            //EN MEMORIA
    private Inscripcion inscribir;

    private RegistroInscripcion registro;           //ARCHIVO o BD


    //CONSTRUCTOR
    public Concurso(String nombre, LocalDate fechaInicio, LocalDate fechaFin, RegistroInscripcion registro) {   //Se inyecto la dependencia
        this.id = this.contador++;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.listaInscriptos = new ArrayList<>();
        this.registro = registro;
    }


    //METODOS
    public void inscribirParticipante (Participante participante, LocalDate fechaActual) throws LateRegistrationException, DatabaseConnectionException {


        //PARA REGISTRAR EN MEMORIA (TP 1)------------------------------------------------------------------------------
        if(fechaActual.isBefore(fechaInicio) || fechaActual.isAfter(fechaFin)){
            inscribir = new InscribirFueraDeFecha();
        } else if (fechaActual.isEqual(fechaInicio)){
            inscribir = new InscribirPrimerDia();
        } else {
            inscribir = new InscribirSinSumarPuntos();
        }

        inscribir.inscribir(participante, this);
        //--------------------------------------------------------------------------------------------------------------


        //PARA REGISTRAR EN ARCHIVOS O BD
        this.registro.registrarInscripcion(fechaActual, participante, this);    //ACA PARTICIPANTE YA TIENE LOS PUNTOS ASIGNADOS
        //ESTE REGISTRAR ES POLIMORFICO, REGISTRO PUEDE SER UN FAKE


    }


    //******************************************** OTROS METODOS *******************************************************
    public void agregarParicipante (Participante participante){
        listaInscriptos.add(participante);
    }

    public ArrayList<Participante> getListaInscriptos() {
        return listaInscriptos;
    }

    //PARA GUARDAR EN LA BASE DE DATOS ----- COMO TESTEO ESTA LOGICA??
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "[ID Concurso: " + String.valueOf(id) + ", " + this.nombre + "]";
    }

    //******************************************************************************************************************



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
}
