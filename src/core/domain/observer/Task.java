package src.core.domain.observer;

import java.util.ArrayList;
import java.util.List;

public class Task {

  /*
   * Patrón de diseño -> Observer
   * Para manejar la notificación de cambios en las tareas.
   * notifica a los observadores (como una interfaz de usuario o un sistema de
   * notificaciones) cuando se cree, edite o elimine una tarea.
   */

  private List<Observer> observers = new ArrayList<>();
  private String taskName;

  public void addObserver(Observer observer) {
    observers.add(observer);
  }

  public void removeObserver(Observer observer) {
    observers.remove(observer);
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
    notifyObservers();
  }

  private void notifyObservers() {
    for (Observer observer : observers) {
      observer.update("Task updated: " + taskName);
    }
  }
}
