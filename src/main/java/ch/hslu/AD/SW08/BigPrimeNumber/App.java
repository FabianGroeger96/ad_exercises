package ch.hslu.AD.SW08.BigPrimeNumber;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Übung: Weiterführende Konzepte (N3)
 * Aufgabe: Suche nach grossen Primzahlen
 *
 * @author Fabian Gröger
 * @version 18.04.2018
 */
public class App {
    private static final int N_PRIMES = 100;
    private static final Logger LOG = LogManager.getLogger();
    private static final boolean logging = false;

    public static void main(String args[]) {
        findPrimesAsync();

        findPrimesSync();
    }

    /**
     * Find prime numbers asynchronous
     */
    public static void findPrimesAsync() {
        try {
            long start = System.currentTimeMillis();
            List<BigInteger> primes = findBigPrimesAsync(N_PRIMES);
            long end = System.currentTimeMillis();

            if (logging) {
                for (BigInteger prime : primes) {
                    LOG.debug(prime.toString().substring(0, 20) + "...");
                }
            }

            LOG.debug(String.format("asynchronous: found %d big prime numbers in %.2f seconds", N_PRIMES, (float) (end - start) / 1000));
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
        }
    }

    public static List<BigInteger> findBigPrimesAsync(final int nPrimes) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
        CompletionService<BigInteger> completionService = new ExecutorCompletionService<>(executorService);
        List<BigInteger> primes = new ArrayList<>(nPrimes);

        for (int n = 0; n < nPrimes; n++) {
            completionService.submit(new BigPrimeFinder());
        }

        for (int n = 0; n < nPrimes; n++) {
            try {
                primes.add(completionService.take().get());
            } catch (InterruptedException | ExecutionException e) {
                throw new Exception(e);
            }
        }

        executorService.shutdown();
        return primes;
    }

    /**
     * Find prime numbers synchronous
     */
    public static void findPrimesSync() {
        long start = System.currentTimeMillis();
        List<BigInteger> primes = findBigPrimesSync(N_PRIMES);
        long end = System.currentTimeMillis();

        if (logging) {
            for (BigInteger prime : primes) {
                LOG.debug(prime.toString().substring(0, 20) + "...");
            }
        }

        LOG.debug(String.format("synchronous: found %d big prime numbers in %.2f seconds", N_PRIMES, (float) (end - start) / 1000));
    }

    public static List<BigInteger> findBigPrimesSync(final int nPrimes) {
        List<BigInteger> bigPrimes = new ArrayList<>(nPrimes);
        int n = 0;
        while (n < nPrimes) {
            BigInteger prime = BigPrimeFinder.findBigPrime();
            n++;
            bigPrimes.add(prime);
        }
        return bigPrimes;
    }
}
