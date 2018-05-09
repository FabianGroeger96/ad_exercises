package ch.hslu.AD.SW11.Fibonacci;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;

/**
 * Übung: Parallelisierungsframeworks (N4)
 * Aufgabe: Fibonacci-Zahlen
 *
 * @author Fabian Gröger
 * @version 09.05.2018
 */
public class Fibonacci {

    /**
     * Berechnet den Fibonacci Wert für n parallelisiert
     * verwendet den Common Pool
     *
     * @param n für die Fibonacci Berechnung
     * @return Resultat der Fibonacci Berechnung
     */
    public static BigInteger concurrentFibonacciCommonPool(int n) {
        return new FibonacciTask(n).fork().join();
    }

    /**
     * Berechnet den Fibonacci Wert für n parallelisiert
     * verwendet den ForkJoinPool
     *
     * @param n für die Fibonacci Berechnung
     * @return Resultat der Fibonacci Berechnung
     */
    public static BigInteger concurrentFibonacciForkJoinPool(int n) {
        ForkJoinPool pool = new ForkJoinPool();
        FibonacciTask task = new FibonacciTask(n);
        return pool.invoke(task);
    }

    /**
     * Berechnet den Fibonacci Wert für n rekursiv
     *
     * @param n für die Fibonacci Berechnung
     * @return Resultat der Fibonacci Berechnung
     */
    public static BigInteger recursiveFibonacci(int n) {
        if (n <= 2) {
            return BigInteger.ONE;
        } else {
            return recursiveFibonacci(n - 1).add(recursiveFibonacci(n - 2));
        }
    }

    /**
     * Berechnet den Fibonacci Wert für n iterativ
     *
     * @param n für die Fibonacci Berechnung
     * @return Resultat der Fibonacci Berechnung
     */
    public static BigInteger iterativeFibonacci(int n) {
        BigInteger[] fib = new BigInteger[n];
        for (int i = 0; i < n; i++) {
            if (i < 2) {
                fib[i] = BigInteger.ONE;
            } else {
                fib[i] = fib[i - 1].add(fib[i - 2]);
            }
        }
        return fib[n - 1];
    }
}
