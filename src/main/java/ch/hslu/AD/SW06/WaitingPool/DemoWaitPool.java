package ch.hslu.AD.SW06.WaitingPool;

/**
 * Übung: Thread Steuerung (N2)
 * Aufgabe: Wait-Pool-Demo
 * <p>
 * Demonstration eines Wait-Pools.
 *
 * @author Fabian Gröger
 * @version 09.04.2018
 */
public final class DemoWaitPool {
    private static final Object lock = new Object();

    public static void main(final String args[]) throws InterruptedException {
        final MyTask waiter = new MyTask(lock);
        new Thread(waiter).start();
        Thread.sleep(1000);
        lock.notify();
    }
}
