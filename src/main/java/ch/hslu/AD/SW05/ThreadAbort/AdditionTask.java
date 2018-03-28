package ch.hslu.AD.SW05.ThreadAbort;

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

    public void start() {
        this.runThread = new Thread(this);
        this.runThread.start();
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
                e.printStackTrace();
            }
        }

        if (!isStopped) {
            LOGGER.info(runThread.getName() + ": SUM from " + this.rangeBegin + " to " + this.rangeEnd + " = " + sum);
        } else {
            LOGGER.info(runThread.getName() + ": interrupted.");
        }
    }

    public void stop() {
        isStopped = true;

        if (runThread != null) {
            runThread.interrupt();
        }
    }

    public boolean isStopped() {
        return this.isStopped;
    }
}
