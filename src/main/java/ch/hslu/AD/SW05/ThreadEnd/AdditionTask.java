package ch.hslu.AD.SW05.ThreadEnd;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Übung: Threads & Synchronisation (N1)
 * Aufgabe: Das Ende eines Threads
 *
 * @author Fabian Gröger
 * @version 26.03.2018
 */
public class AdditionTask implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(AdditionTask.class);

    private Thread runThread;
    private boolean isStopped = false;

    private int rangeBegin;
    private int rangeEnd;

    public AdditionTask(int rangeBegin, int rangeEnd) {
        this.rangeBegin = rangeBegin;
        this.rangeEnd = rangeEnd;
    }

    @Override
    public void run() {
        this.runThread = Thread.currentThread();

        long sum = 0;

        for (int i = this.rangeBegin; i <= this.rangeEnd && !isStopped; i++) {
            sum += i;
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                break;
            }
        }

        if (!isStopped) {
            System.out.println(runThread.getName() + ": SUM" + (this.rangeBegin - this.rangeEnd) + " -> " + sum);
        } else {
            System.out.println(runThread.getName() + ": interrupted.");
        }
    }

    public void stop() {
        isStopped = true;

        if (runThread != null) {
            runThread.interrupt();
        }
    }
}
