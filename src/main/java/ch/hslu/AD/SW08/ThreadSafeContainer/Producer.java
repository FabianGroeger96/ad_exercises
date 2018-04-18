package ch.hslu.AD.SW08.ThreadSafeContainer;

import java.util.Collection;
import java.util.concurrent.Callable;

/**
 * Übung: Weiterführende Konzepte (N3)
 * Aufgabe: Container Thread-sicher machen
 *
 * @author Fabian Gröger
 * @version 18.04.2018
 */
public class Producer implements Callable<Long> {
    private final Collection<Integer> numbers;
    private final int maxRange;

    public Producer(int maxRange, Collection<Integer> numbers) {
        this.numbers = numbers;
        this.maxRange = maxRange;
    }

    @Override
    public Long call() throws Exception {
        long sum = 0;
        for (int i = 0; i < maxRange; i++) {
            sum += i;
            numbers.add(i);
        }
        return sum;
    }
}
