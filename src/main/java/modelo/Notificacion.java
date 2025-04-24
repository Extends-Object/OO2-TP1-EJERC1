package modelo;

public class Notificacion {

    private String emisor;
    private String destinatario;
    private String asunto;
    private String cuerpo;

    public Notificacion(String emisor, String destinatario, String asunto, String cuerpo) {
        this.emisor = emisor;
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
    }

    public String getEmisor() {
        return emisor;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }

}
