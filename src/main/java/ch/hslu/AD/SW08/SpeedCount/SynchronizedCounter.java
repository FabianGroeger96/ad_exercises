package ch.hslu.AD.SW08.SpeedCount;

/**
 * Übung: Weiterführende Konzepte (N3)
 * Aufgabe: Speed Count
 *
 * @author Fabian Gröger
 * @version 18.04.2018
 */
public final class SynchronizedCounter implements Count {

    private int counter;

    public SynchronizedCounter(final int init) {
        counter = init;
    }

    @Override
    public synchronized void increment() {
        counter++;
    }

    @Override
    public synchronized void decrement() {
        counter--;
    }

    @Override
    public synchronized int get() {
        return counter;
    }
}
