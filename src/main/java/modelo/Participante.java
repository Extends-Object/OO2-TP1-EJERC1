package modelo;

public class Participante {

    //ATRIBUTO DE CLASE
    private static int contador = 1;

    //ATRIBUTOS DE INSTANCIA
    private final int id;
    private String nombre;
    private int puntosAcumulados;


    //CONSTRUCTOR
    public Participante(String nombre) {
        this.id = this.contador++;
        this.nombre = nombre;
        this.puntosAcumulados = 0;
    }

    //METODOS
    public void sumarPuntos (int cantidad){
        this.puntosAcumulados = this.puntosAcumulados + cantidad;
    }

    public int getPuntosAcumulados() {
        return puntosAcumulados;
    }

    @Override
    public String toString() {
        return "[ID modelo.Participante: " + String.valueOf(id) + ", Puntos acumulados: " + this.puntosAcumulados + "], ";
    }

    public int getId() {        //COMO VERIFICO ESTA LOGICA
        return id;
    }
}
