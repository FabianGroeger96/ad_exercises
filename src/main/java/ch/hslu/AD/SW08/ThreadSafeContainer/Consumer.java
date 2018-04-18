package ch.hslu.AD.SW08.ThreadSafeContainer;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;

/**
 * Übung: Weiterführende Konzepte (N3)
 * Aufgabe: Container Thread-sicher machen
 *
 * @author Fabian Gröger
 * @version 18.04.2018
 */
public class Consumer implements Callable<Long> {

    private final Collection<Integer> list;

    public Consumer(Collection<Integer> list) {
        this.list = list;
    }

    @Override
    public Long call() throws Exception {
        long sum = 0;
        Iterator<Integer> iterable = list.iterator();
        while (iterable.hasNext()) {
            sum += iterable.next();
        }
        return sum;
    }
}
