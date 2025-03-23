public class Participante {
    private String nombre;
    private int puntosAcumulados;

    public Participante(String nombre) {
        this.nombre = nombre;
        this.puntosAcumulados = 0;
    }

    public void sumarPuntos (int cantidad){
        this.puntosAcumulados = this.puntosAcumulados + cantidad;
    }

    public int getPuntosAcumulados() {
        return puntosAcumulados;
    }
}
