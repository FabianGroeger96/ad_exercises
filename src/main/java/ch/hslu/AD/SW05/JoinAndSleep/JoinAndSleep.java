package ch.hslu.AD.SW05.JoinAndSleep;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Übung: Threads & Synchronisation (N1)
 * Aufgabe: JoinAndSleep
 *
 * @author Fabian Gröger
 * @version 29.03.2018
 */
public class JoinAndSleep extends Thread {

    private static final Logger LOGGER = LogManager.getLogger(JoinAndSleep.class);

    public JoinAndSleep(Runnable runnable) {
        super(runnable);
    }

    public static void main(String[] args) {
        // creating thread 3
        JoinAndSleep thread3 = new JoinAndSleep(() -> {
            LOGGER.info("thread 3 active");
            try {
                LOGGER.info("thread 3 sleeping");
                Thread.sleep(4000);
                LOGGER.info("thread 3 woke up");
            } catch (InterruptedException e) {
                LOGGER.info("thread 3 interrupted");
            }
        });

        // creating thread 2
        JoinAndSleep thread2 = new JoinAndSleep(() -> {
            LOGGER.info("thread 2 active");
            try {
                LOGGER.info("thread 2 waiting for thread 3");
                thread3.join();
                LOGGER.info("thread 2 done waiting for thread 3");

                LOGGER.info("thread 2 sleeping");
                Thread.sleep(3000);
                LOGGER.info("thread 2 woke up");

            } catch (InterruptedException e) {
                LOGGER.info("thread 2 interrupted");
            }
        });

        // creating thread 1
        JoinAndSleep thread1 = new JoinAndSleep(() -> {
            LOGGER.info("thread 1 active");
            try {
                LOGGER.info("thread 1 waiting for thread 2");
                thread2.join();
                LOGGER.info("thread 1 done waiting for thread 2");

                LOGGER.info("thread 1 sleeping");
                Thread.sleep(2000);
                LOGGER.info("thread 1 woke up");

            } catch (InterruptedException e) {
                LOGGER.info("thread 1 interrupted");
            }
        });

        thread1.start(); // starting thread 1
        thread2.start(); // starting thread 2
        thread3.start(); // starting thread 3

        //t3.interrupt();
    }
}
