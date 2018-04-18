package ch.hslu.AD.SW08.BigPrimeNumber;

import java.math.BigInteger;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Übung: Weiterführende Konzepte (N3)
 * Aufgabe: Suche nach grossen Primzahlen
 *
 * @author Fabian Gröger
 * @version 18.04.2018
 */
public class BigPrimeFinder implements Callable<BigInteger> {

    private static final int NUM_BITS = 1024;
    private Queue<BigInteger> resultQueue;

    public BigPrimeFinder(Queue<BigInteger> resultQueue) {
        this.resultQueue = resultQueue;
    }

    public BigPrimeFinder() {
        this.resultQueue = null;
    }

    public static BigInteger findBigPrime() {
        boolean foundPrime = false;
        BigInteger number;
        do {
            number = new BigInteger(NUM_BITS, new Random());
            foundPrime = number.isProbablePrime(Integer.MAX_VALUE);
        } while (!foundPrime);
        return number;
    }

    @Override
    public BigInteger call() throws Exception {
        boolean foundPrime = false;
        BigInteger number;

        do {
            number = new BigInteger(NUM_BITS, new Random());
            foundPrime = number.isProbablePrime(Integer.MAX_VALUE);
        } while (!foundPrime);

        if (resultQueue != null) {
            resultQueue.offer(number);
        }

        return number;
    }
}
