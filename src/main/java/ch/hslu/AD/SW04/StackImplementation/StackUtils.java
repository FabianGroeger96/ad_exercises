package ch.hslu.AD.SW04.StackImplementation;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Übung: Hashbasierte Datenstrukturen, Performance, Thirdparty-Datenstrukturen (D3)
 * Aufgabe: Performance-Vergleich: Stack-Implementationen
 *
 * @author Fabian Gröger
 * @version 20.03.2018
 */
public class StackUtils {

    public static Integer[] getSortedArray(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException();
        }
        Integer array[] = new Integer[size];
        for (Integer n = 0; n < size; n++) {
            array[n] = n;
        }
        return array;
    }

    public static void main(String args[]) {
        final int size = 1000000;
        Integer array[] = StackUtils.getSortedArray(size);

        long start = System.currentTimeMillis();
        Deque<Integer> deque = new ArrayDeque<>(size);
        for (int n = 0; n < size; n++) {
            deque.push(n);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(array.length);
    }
}
