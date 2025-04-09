package exceptions;

public class DatabaseConnectionException extends Exception{

    public DatabaseConnectionException() {

    }

    public DatabaseConnectionException(String mensaje) {
        super(mensaje);
    }

    public DatabaseConnectionException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

}
