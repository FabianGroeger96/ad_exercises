package ch.hslu.AD.SW06.HorseRace;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Übung: Thread Steuerung (N2)
 * Aufgabe: Pferderennen
 * <p>
 * Eine Rennbahn.
 *
 * @author Fabian Gröger
 * @version 09.04.2018
 */
public final class Turf {
    private static final Logger LOG = LogManager.getLogger("HorseRace"); //...ist zu initialisieren

    public static void main(final String[] args) {
        Synch starterBox = new Latch();
        for (int i = 1; i < 6; i++) {
            new Thread(new RaceHorse(starterBox), "Horse " + i).start();
        }
        LOG.info("Start...");
        starterBox.release();
    }
}
