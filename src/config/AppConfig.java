package src.config;

public class AppConfig {

  /*
   * Patron creacional -> Singleton
   * Almacena configuraciones como la URL del servidor, la versión de la
   * aplicación, etc.
   */

  private static AppConfig instance;
  private String serverUrl;

  private AppConfig() {
    // Configuraciones iniciales
    serverUrl = "http://localhost:8080";
  }

  public static AppConfig getInstance() {
    if (instance == null) {
      instance = new AppConfig();
    }
    return instance;
  }

  public String getServerUrl() {
    return serverUrl;
  }
}
