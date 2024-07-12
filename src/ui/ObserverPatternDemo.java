package src.ui;

import src.core.domain.observer.Task;
import src.core.domain.observer.TaskObserver;

public class ObserverPatternDemo {

  /*
   * Patron estructural -> Observer
   * Ver task
   */
  public static void main(String[] args) {
    Task task = new Task();

    TaskObserver observer1 = new TaskObserver("Observer 1");
    TaskObserver observer2 = new TaskObserver("Observer 2");

    task.addObserver(observer1);
    task.addObserver(observer2);

    task.setTaskName("Complete Assignment");

    task.removeObserver(observer1);

    task.setTaskName("Review Assignment");
  }
}
