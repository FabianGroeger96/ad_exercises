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

    private static final boolean VIOLENT_STOP = false;

    private Thread runThread; // thread
    private boolean isStopped = false; // true = stopped, false = running

    private int rangeBegin; // range beginning
    private int rangeEnd; // range ending

    /**
     * Default constructor for creating an addition task
     *
     * @param rangeBegin the range beginning
     * @param rangeEnd   the range ending
     */
    public AdditionTask(int rangeBegin, int rangeEnd) {
        this.rangeBegin = rangeBegin;
        this.rangeEnd = rangeEnd;
    }

    /**
     * Starts the addition task
     */
    public void start() {
        this.runThread = new Thread(this);
        this.runThread.start();
    }

    /**
     * Overrides the implementation of the runnable interface
     * specifies what the addition task does when it's running
     *
     * calculates the cross sum of a simple series of numbers
     */
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

    /**
     * Stopps the addition task
     */
    public void stop() {
        isStopped = true;

        if (VIOLENT_STOP){
            runThread.stop();
            LOGGER.info("violent stop");
        } else {
            if (runThread != null) {
                runThread.interrupt();
                LOGGER.info("interrupt");
            }
        }
    }


    /**
     * Checks if the addition task is stopped
     * @return if the addition task is stopped
     */
    public boolean isStopped() {
        return this.isStopped;
    }
}
