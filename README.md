# Patrones de diseño

## 1. Patrón Creacional: Singleton

### Propósito del patrón:

El patrón Singleton asegura que una clase tenga solo una instancia y proporciona un punto de acceso global a esa instancia.

##### Situaciones en las que es más útil aplicar este patrón:

- Cuando se necesita exactamente una instancia de una clase para coordinar acciones en todo el sistema.
- Controladores de impresión.
- Acceso a un recurso compartido como una base de datos o un archivo.
- Gestión de configuraciones de aplicaciones.

###### Ejemplo de un código en Java

```java
public class Singleton {
    // La única instancia de la clase Singleton
    private static Singleton instance;

    // Constructor privado para evitar la instanciación directa
    private Singleton() {}

    // Método para obtener la única instancia de la clase Singleton
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    // Método de ejemplo que muestra el uso de Singleton
    public void showMessage() {
        System.out.println("Hello World! This is a Singleton instance.");
    }
}

public class SingletonPatternDemo {
    public static void main(String[] args) {
        // Obtén la única instancia de Singleton
        Singleton singleton = Singleton.getInstance();

        // Llama al método de la instancia Singleton
        singleton.showMessage();
    }
}

```

---

## 2. Patrón Estructural: Adapter

### Propósito del patrón:

El patrón Adapter permite que las interfaces incompatibles trabajen juntas. Convierte la interfaz de una clase en otra interfaz que el cliente espera.

##### Situaciones en las que es más útil aplicar este patrón:

- Cuando se desea usar una clase que no tiene la interfaz necesaria.
- Integración de componentes de software que no fueron diseñados para trabajar juntos pero necesitan hacerlo.

###### Ejemplo de un código en Java

```java
// Interfaz esperada por el cliente
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// Clase que implementa la interfaz MediaPlayer
class AudioPlayer implements MediaPlayer {
    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3 file. Name: " + fileName);
        } else {
            System.out.println("Invalid media. " + audioType + " format not supported");
        }
    }
}

// Interfaz de un reproductor avanzado
interface AdvancedMediaPlayer {
    void playVlc(String fileName);
    void playMp4(String fileName);
}

// Implementación de AdvancedMediaPlayer
class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        // No hace nada
    }
}

class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        // No hace nada
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: " + fileName);
    }
}

// Clase Adapter que permite a AudioPlayer reproducir formatos avanzados
class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer advancedMusicPlayer;

    public MediaAdapter(String audioType) {
        if(audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer.playVlc(fileName);
        } else if(audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer.playMp4(fileName);
        }
    }
}

// Clase cliente que usa el Adapter para reproducir archivos avanzados
class AudioPlayerWithAdapter implements MediaPlayer {
    MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3 file. Name: " + fileName);
        } else if(audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Invalid media. " + audioType + " format not supported");
        }
    }
}

public class AdapterPatternDemo {
    public static void main(String[] args) {
        AudioPlayerWithAdapter audioPlayer = new AudioPlayerWithAdapter();

        audioPlayer.play("mp3", "beyond_the_horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far_far_away.vlc");
        audioPlayer.play("avi", "mind_me.avi");
    }
}

```

---

## 3. Patrón de Comportamiento: Observer

### Propósito del patrón:

El patrón Observer define una relación de dependencia de uno a muchos entre objetos, de manera que cuando uno de los objetos cambia de estado, todos sus dependientes son notificados y actualizados automáticamente.

##### Situaciones en las que es más útil aplicar este patrón:

- Cuando un cambio en un objeto requiere cambios en otros objetos y no se sabe cuántos objetos necesitan ser cambiados.
- Cuando un objeto debe notificar a otros objetos sin asumir quiénes son esos objetos.

###### Ejemplo de un código en Java

```java
import java.util.ArrayList;
import java.util.List;

// Interfaz Observer
interface Observer {
    void update(String message);
}

// Interfaz Subject
interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

// Clase concreta Subject
class ConcreteSubject implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String message;

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    // Método para cambiar el estado del Subject y notificar a los observers
    public void setMessage(String message) {
        this.message = message;
        notifyObservers();
    }
}

// Clase concreta Observer
class ConcreteObserver implements Observer {
    private String name;

    public ConcreteObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received message: " + message);
    }
}

// Ejemplo de uso del patrón Observer
public class ObserverPatternDemo {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();

        Observer observer1 = new ConcreteObserver("Observer 1");
        Observer observer2 = new ConcreteObserver("Observer 2");

        subject.registerObserver(observer1);
        subject.registerObserver(observer2);

        subject.setMessage("Hello, Observers!");

        subject.removeObserver(observer1);

        subject.setMessage("Hello, Observer 2!");
    }
}

```

---
