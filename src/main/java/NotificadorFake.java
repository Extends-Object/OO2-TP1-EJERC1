import java.util.ArrayList;
import java.util.List;

public class NotificadorFake implements Notificador{

    private final List<String> mensajesEnviados = new ArrayList<>();

    @Override
    public void notificar(String emisor, String destinatario, String asunto, String cuerpo) {

        this.mensajesEnviados.add("Para: " + destinatario + ", Asunto: " + asunto + ", Mensaje: " + cuerpo);

    }
}
