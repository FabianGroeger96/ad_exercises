package ch.hslu.AD.SW06.WaitingPool;

/**
 * Übung: Thread Steuerung (N2)
 * Aufgabe: Wait-Pool-Demo
 * <p>
 * Demonstration eines Wait-Pools.
 * Korrigiert.
 *
 * @author Fabian Gröger
 * @version 09.04.2018
 */
public class DemoWaitPoolCorrected {
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        synchronized (lock) {
            MyTask waiter = new MyTask(lock);
            new Thread(waiter).start();
            Thread.sleep(1000);

            lock.notify();
        }
    }
}
