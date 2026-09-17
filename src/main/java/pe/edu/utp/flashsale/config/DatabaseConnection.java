package pe.edu.utp.flashsale.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//patron singleton
public class DatabaseConnection {
    private static DatabaseConnection instancia;
    private Connection connection;

    private final String URL = "jdbc:postgresql://localhost:5432/flashsale_db";
    private final String USER = "postgres";
    private final String PASS = "123456";

    private DatabaseConnection() {
        try {
            // Inicializa la conexión a PostgreSQL
            this.connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("[DB] Conexión a PostgreSQL establecida con éxito.");
        } catch (SQLException e) {
            System.err.println("[DB ERROR] Fallo al conectar: " + e.getMessage());
        }
    }

    public static synchronized DatabaseConnection getInstancia() {
        if (instancia == null) {
            instancia = new DatabaseConnection();
        }
        return instancia;
    }

    public Connection getConnection() {
        return connection;
    }
}