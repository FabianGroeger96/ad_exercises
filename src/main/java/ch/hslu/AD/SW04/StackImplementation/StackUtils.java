package ch.hslu.AD.SW04.StackImplementation;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Übung: Hashbasierte Datenstrukturen, Performance, Thirdparty-Datenstrukturen (D3)
 * Aufgabe: Performance-Vergleich: Stack-Implementationen
 *
 * @author Fabian Gröger
 * @version 20.03.2018
 */
public class StackUtils {

    /**
     * Creates a stored array from the given size
     *
     * @param size the size of the array
     * @return the sorted array
     */
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
        final int size = 1000000; // initial size

        /**
         * Create the array with initial size
         */
        long startArray = System.currentTimeMillis();
        Integer array[] = StackUtils.getSortedArray(size);
        long endArray = System.currentTimeMillis();

        System.out.println("Size: " + array.length);

        System.out.println("Created Array in: " + (endArray - startArray));

        /**
         * Create the java.util.stack and fill it with the elements from the array
         */
        long startStack = System.currentTimeMillis();
        Stack<Integer> stack = new Stack<Integer>(); // Initial size???
        for (int n = 0; n < size; n++) {
            stack.push(array[n]);
        }
        long endStack = System.currentTimeMillis();

        System.out.println("Created Java.Util.Stack in: " + (endStack - startStack));

        /**
         * Create the my.stack and fill it with the elements from the array
         */
        long startMyStack = System.currentTimeMillis();
        ch.hslu.AD.SW04.StackImplementation.Stack myStack = new ch.hslu.AD.SW04.StackImplementation.Stack(size);
        for (int n = 0; n < size; n++) {
            myStack.push(array[n]);
        }
        long endMyStack = System.currentTimeMillis();

        System.out.println("Created My.Stack in: " + (endMyStack - startMyStack));

        /**
         * Create the deque and fill it with the elements from the array
         */
        long startDeque = System.currentTimeMillis();
        Deque<Integer> deque = new ArrayDeque<>(size);
        for (int n = 0; n < size; n++) {
            deque.push(array[n]);
        }
        long endDeque = System.currentTimeMillis();
        System.out.println("Created Deque in: " + (endDeque - startDeque));
    }
}
