package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    private static String URL_DB;
    private static String DB;
    private static String user;
    private static String pass;
    private static Connection conn = null;



    public static void connect() {
        try {
            cargarConfiguracion();
            conn = DriverManager.getConnection(URL_DB + DB, user, pass);
        } catch (SQLException sqlEx) {
            System.out.println("No se ha podido conectar a " + URL_DB + DB + ". " + sqlEx.getMessage());	//el mensaje esta definido dentro del motor de la base
            System.out.println("Error al cargar el driver.");
        }//el motor de la base de datos genera un mensaje de error y lo envía al driver JDBC,
    }	// que a su vez lo encapsula en una excepción SQLException




    public static void disconnect() {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
                System.out.println("Conexión cerrada exitosamente.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        } else {
            System.out.println("No existe conexión activa para cerrar.");
        }
    }




    public static void reconnect() {
        disconnect();
        connect();
    }




    public static Connection getConnection() {
        if (conn == null) {
            connect();
        }
        return conn;
    }




    private static void cargarConfiguracion() {
        try {
            Properties properties = new Properties();
            properties.load(ConnectionManager.class.getClassLoader().getResourceAsStream("Database.properties"));

            URL_DB = properties.getProperty("db.url");
            DB = properties.getProperty("db.name");
            user = properties.getProperty("db.user");
            pass = properties.getProperty("db.password");

            //System.out.println(URL_DB + " " + DB + " " + user + " " + pass);

        } catch (Exception e) {
            throw new RuntimeException("Error al cargar configuración de la base de datos.", e);
        }
    }

}
