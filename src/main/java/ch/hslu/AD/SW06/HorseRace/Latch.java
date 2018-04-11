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

    private Thread timeout;

    //private int count;

    public Latch(int totalSlots) {
        this.totalSlots = totalSlots;
        //this.count = 1;

        timeout = new Thread(() -> {
            LOG.info("Automatischer Start in 1000ms...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LOG.info("Manueller Start ist dem automatischem Start zuvorgekommen");
                return;
            }

            release(); // automatischer Start nach 1000ms
        });
    }

    @Override
    public synchronized void acquire() throws InterruptedException {
        if (this.slotsFilled < this.totalSlots) { // if there is space
            this.slotsFilled++;
            if (this.slotsFilled == this.totalSlots) { // if all slots are filled
                this.timeout.start();
            }
            this.wait();
        }

//        while (count > 0){
//            timeout.start();
//            this.wait();
//        }
    }

    @Override
    public synchronized void release() {
        if (started) { // if the race is already started
            LOG.info("Rennen möchte manuell gestartet werden, wurde jedoch bereits automatisch gestartet");
            return;
        }

        while (this.slotsFilled < this.totalSlots) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!started) { // if the race is not started
            this.started = true;
            this.timeout.interrupt();
            this.notifyAll();
        }

        LOG.info("Start ...");

//        this.timeout.interrupt();
//        this.notifyAll();
//        this.count--;
    }
}
