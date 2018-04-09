package ch.hslu.AD.SW06.HorseRace;

/**
 * Übung: Thread Steuerung (N2)
 * Aufgabe: Pferderennen
 * <p>
 * Schnittstelle für die Zutrittsverwaltung geschützter Bereiche.
 *
 * @author Fabian Gröger
 * @version 09.04.2018
 */
public interface Synch {
    /**
     * Eintritt in einen geschützten Bereich erlangen,
     * falls kein Zutritt möglich ist warten.
     *
     * @throws InterruptedException wenn das Warten unterbrochen wird.
     */
    public void acquire() throws InterruptedException;

    /**
     * Freigabe des geschützten Bereiches.
     */
    public void release();
}
