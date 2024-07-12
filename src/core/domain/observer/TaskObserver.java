package src.core.domain.observer;

public class TaskObserver implements Observer {

  /*
   * Patron estructural -> Observer
   * Ver task
   */

  private String observerName;

  public TaskObserver(String observerName) {
    this.observerName = observerName;
  }

  @Override
  public void update(String message) {
    System.out.println(observerName + " received: " + message);
  }

}
