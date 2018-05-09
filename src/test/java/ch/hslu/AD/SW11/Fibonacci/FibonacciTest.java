package ch.hslu.AD.SW11.Fibonacci;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

/**
 * Übung: Parallelisierungsframeworks (N4)
 * Aufgabe: Fibonacci-Zahlen
 *
 * @author Fabian Gröger
 * @version 09.05.2018
 */
public class FibonacciTest {

    private static final int N = 42;
    private BigInteger[] fib = new BigInteger[N];

    @Before
    public void setup() {
        for (int n = 0; n < N; n++) {
            if (n < 2) {
                fib[n] = BigInteger.ONE;
            } else {
                fib[n] = fib[n - 1].add(fib[n - 2]);
            }
        }
    }

    @Test
    public void testFirstTenManually() {
        assertEquals(BigInteger.valueOf(1), fib[0]);
        assertEquals(BigInteger.valueOf(1), fib[1]);
        assertEquals(BigInteger.valueOf(2), fib[2]);
        assertEquals(BigInteger.valueOf(3), fib[3]);
        assertEquals(BigInteger.valueOf(5), fib[4]);
        assertEquals(BigInteger.valueOf(8), fib[5]);
        assertEquals(BigInteger.valueOf(13), fib[6]);
        assertEquals(BigInteger.valueOf(21), fib[7]);
        assertEquals(BigInteger.valueOf(34), fib[8]);
        assertEquals(BigInteger.valueOf(55), fib[9]);
    }

    @Test
    public void testConcurrentlyForkJoinPool() {
        assertEquals(fib[fib.length - 1], Fibonacci.concurrentFibonacciForkJoinPool(N));
    }

    @Test
    public void testConcurrentlyCommonPool() {
        assertEquals(fib[fib.length - 1], Fibonacci.concurrentFibonacciCommonPool(N));
    }

    @Test
    public void testRecursively() {
        assertEquals(fib[fib.length - 1], Fibonacci.recursiveFibonacci(N));
    }

    @Test
    public void testIteratively() {
        assertEquals(fib[fib.length - 1], Fibonacci.iterativeFibonacci(N));
    }

    @Test
    public void benchmarkRecursivelyVSConcurrentlyVSIteratively() {
        System.out.println(String.format("%15s %15s %15s %15s", "nth Fib", "recursively", "concurrently", "iteratively"));
        System.out.println(String.format("%15s %15s %15s %15s", "-------", "-----------", "------------", "-----------"));

        for (int n = 1; n <= N; n++) {
            BigInteger result;

            long rStart = System.currentTimeMillis();
            result = Fibonacci.recursiveFibonacci(n);
            long rEnd = System.currentTimeMillis();
            assertEquals(fib[n - 1], result);

            long cStart = System.currentTimeMillis();
            result = Fibonacci.concurrentFibonacciCommonPool(n);
            long cEnd = System.currentTimeMillis();
            assertEquals(fib[n - 1], result);

            long iStart = System.currentTimeMillis();
            result = Fibonacci.iterativeFibonacci(n);
            long iEnd = System.currentTimeMillis();
            assertEquals(fib[n - 1], result);

            System.out.printf("%15d %15d %15d %15d\n", n, rEnd - rStart, cEnd - cStart, iEnd - iStart);
        }
    }

    @Test
    public void benchmarkForkJoinPoolVSCommonPool() {
        System.out.println(String.format("%10s %20s %30s", "nth Fib", "Fork Join Pool", "Common Pool"));
        System.out.println(String.format("%10s %20s %30s", "-------", "--------------", "-----------"));

        for (int n = 1; n <= N; n++) {
            BigInteger result;

            long fStart = System.currentTimeMillis();
            result = Fibonacci.concurrentFibonacciForkJoinPool(n);
            long fEnd = System.currentTimeMillis();
            assertEquals(fib[n - 1], result);

            long cStart = System.currentTimeMillis();
            result = Fibonacci.concurrentFibonacciCommonPool(n);
            long cEnd = System.currentTimeMillis();
            assertEquals(fib[n - 1], result);

            System.out.println(String.format("%10d %17d ms %27d ms", n, fEnd - fStart, cEnd - cStart));
        }
    }
}