package ch.hslu.AD.SW08.SpeedCount;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Übung: Weiterführende Konzepte (N3)
 * Aufgabe: Speed Count
 *
 * @author Fabian Gröger
 * @version 18.04.2018
 */
public final class AtomicCounter implements Count {

    private final AtomicInteger counter;

    public AtomicCounter(final int init) {
        counter = new AtomicInteger(init);
    }

    @Override
    public void increment() {
        counter.incrementAndGet();
    }

    @Override
    public void decrement() {
        counter.decrementAndGet();
    }

    @Override
    public int get() {
        return counter.get();
    }
}
