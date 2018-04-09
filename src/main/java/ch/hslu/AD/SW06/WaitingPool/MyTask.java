package ch.hslu.AD.SW06.WaitingPool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Übung: Thread Steuerung (N2)
 * Aufgabe: Wait-Pool-Demo
 * <p>
 * Einfacher Task für die Demonstration eines Wait-Pools.
 *
 * @author Fabian Gröger
 * @version 09.04.2018
 */
public final class MyTask implements Runnable {
    private static final Logger LOG = LogManager.getLogger(MyTask.class);
    private final Object lock;

    public MyTask(final Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        LOG.info("warten...");
        synchronized (lock) {
            try {
                wait();
            } catch (InterruptedException ex) {
                return;
            }
        }
        LOG.info("...aufgewacht");
    }
}
