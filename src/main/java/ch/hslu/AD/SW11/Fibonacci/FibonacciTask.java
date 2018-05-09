package ch.hslu.AD.SW11.Fibonacci;

import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;

/**
 * Übung: Parallelisierungsframeworks (N4)
 * Aufgabe: Fibonacci-Zahlen
 *
 * @author Fabian Gröger
 * @version 09.05.2018
 */
public class FibonacciTask extends RecursiveTask<BigInteger> {

    private final int n;

    public FibonacciTask(final int n) {
        this.n = n;
    }

    @Override
    protected BigInteger compute() {
        if (n <= 2) {
            return BigInteger.ONE;
        }

        FibonacciTask f1 = new FibonacciTask(n - 1);
        f1.fork();
        FibonacciTask f2 = new FibonacciTask(n - 2);
        return f2.compute().add(f1.join());
    }
}
