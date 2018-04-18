package ch.hslu.AD.SW08.SpeedCount;

/**
 * Übung: Weiterführende Konzepte (N3)
 * Aufgabe: Speed Count
 *
 * @author Fabian Gröger
 * @version 18.04.2018
 */
public interface Count {
    /**
     * Increments the counter
     */
    public void increment();

    /**
     * Decrements the counter
     */
    public void decrement();

    /**
     * Returns the counter
     *
     * @return the number of the counter
     */
    public int get();
}
