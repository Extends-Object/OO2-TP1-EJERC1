import modelo.Notificacion;
import modelo.Notificador;

import java.util.ArrayList;
import java.util.List;

public class NotificadorFake implements Notificador {

    private final List<String> mensajesEnviados = new ArrayList<>();

    @Override
    public void notificar(Notificacion notificacion) {

        this.mensajesEnviados.add("Para: " + notificacion.getDestinatario() + ", Asunto: " + notificacion.getAsunto() + ", Mensaje: " + notificacion.getCuerpo());

    }

    //SOLO PARA IMPRIMIR LA LISTA DE "MENSAJES ENVIADOS" POR CONSOLA Y SABER SI LE LLEGO Y SE ALMACENO UNA NOTIFICACION
    public List<String> getMensajesEnviados() {
        return mensajesEnviados;
    }

    public boolean seNotifico() {
        return !mensajesEnviados.isEmpty();
    }
}
