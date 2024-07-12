package src.ports;

import java.sql.Connection;

public class DatabaseConnection {

  /*
   * Patron creacional -> Singleton
   * Asegura que solo haya una conexión activa a la base de datos.
   */

  private static DatabaseConnection instance;
  private Connection connection;

  private DatabaseConnection() {
    // Establece la conexión a la base de datos
  }

  public static DatabaseConnection getInstance() {
    if (instance == null) {
      instance = new DatabaseConnection();
    }
    return instance;
  }

  public Connection getConnection() {
    return connection;
  }
}
