package ch.hslu.AD.SW08.SpeedCount;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Übung: Weiterführende Konzepte (N3)
 * Aufgabe: Speed Count
 *
 * @author Fabian Gröger
 * @version 18.04.2018
 */
public class CounterComparison {

    private static final int TASKS_PER_COUNTER = 10;
    private static final int DURATION_IN_SECS = 5;

    private int init;

    private Count atomicCounter;
    private Count synchronizedCounter;

    @Before
    public void init() {
        Random random = new Random(System.currentTimeMillis());
        init = random.nextInt(Integer.MAX_VALUE);
        atomicCounter = new AtomicCounter(init);
        synchronizedCounter = new SynchronizedCounter(init);
    }

    @Test
    public void testInit() {
        Assert.assertEquals(init, atomicCounter.get());
        Assert.assertEquals(init, synchronizedCounter.get());
    }

    @Test
    public void testIncrement() {
        ExecutorService counting = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
        for (int n = 0; n < TASKS_PER_COUNTER; n++) {
            counting.submit(() -> {
                while (true) {
                    atomicCounter.increment();
                }
            });
            counting.submit(() -> {
                while (true) {
                    synchronizedCounter.increment();
                }
            });
        }
        try {
            counting.awaitTermination(DURATION_IN_SECS, TimeUnit.SECONDS);
            System.out.println(String.format("%10d (init testIncrement())", init));
            Assert.assertTrue(atomicCounter.get() > init);
            Assert.assertTrue(synchronizedCounter.get() > init);
            Assert.assertTrue(atomicCounter.get() > synchronizedCounter.get());
            System.out.println(String.format("%10d (atomic)", atomicCounter.get()));
            System.out.println(String.format("%10d (synchronized)", synchronizedCounter.get()));
            float ratio = (float) atomicCounter.get() / synchronizedCounter.get();
            System.out.println(String.format("%.2f ratio (atomic/synchronized)", ratio));
        } catch (InterruptedException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testIncIncDec() {
        ExecutorService counting = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
        for (int n = 0; n < TASKS_PER_COUNTER; n++) {
            counting.submit(() -> {
                while (true) {
                    atomicCounter.increment();
                    atomicCounter.increment();
                    atomicCounter.decrement();
                }
            });
            counting.submit(() -> {
                while (true) {
                    synchronizedCounter.increment();
                    synchronizedCounter.increment();
                    synchronizedCounter.decrement();
                }
            });
        }
        try {
            counting.awaitTermination(DURATION_IN_SECS, TimeUnit.SECONDS);
            System.out.println(String.format("%10d (init testIncIncDec())", init));
            Assert.assertTrue(atomicCounter.get() > init);
            Assert.assertTrue(synchronizedCounter.get() > init);
            Assert.assertTrue(atomicCounter.get() > synchronizedCounter.get());
            System.out.println(String.format("%10d (atomic)", atomicCounter.get()));
            System.out.println(String.format("%10d (synchronized)", synchronizedCounter.get()));
            float ratio = (float) atomicCounter.get() / synchronizedCounter.get();
            System.out.println(String.format("%.2f ratio (atomic/synchronized)", ratio));
        } catch (InterruptedException e) {
            Assert.fail(e.getMessage());
        }
    }

}