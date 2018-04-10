package ch.hslu.AD.SW06.HorseRace;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Übung: Thread Steuerung (N2)
 * Aufgabe: Pferderennen
 *
 * @author Fabian Gröger
 * @version 09.04.2018
 */
public class Latch implements Synch {
    private static final Logger LOG = LogManager.getLogger("HorseRace");

    private final int totalSlots;
    private int slotsFilled;
    private boolean started = false;

    //private final Thread timeout;

    public Latch(int totalSlots) {
        this.totalSlots = totalSlots;

        //timeout = new Thread(() -> {
        //});
    }

    @Override
    public synchronized void acquire() throws InterruptedException {
        if (this.slotsFilled > this.totalSlots) { // if there is space
            this.slotsFilled++;
            this.wait();
        }
    }

    @Override
    public synchronized void release() {
        if (started) { // if the race is already started
            LOG.info("Rennen wurde bereits gestartet");
            return;
        }

        while (this.slotsFilled > this.totalSlots) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!started) { // if the race is not started
            this.started = true;
            this.notifyAll();
            this.slotsFilled--;
        }

        LOG.info("Start Race ...");
    }
}
