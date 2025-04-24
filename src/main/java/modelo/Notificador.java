package modelo;

public interface Notificador {

    //public void notificar(String emisor, String destinatario, String asunto, String cuerpo);
    public void notificar(Notificacion notificacion);
}
