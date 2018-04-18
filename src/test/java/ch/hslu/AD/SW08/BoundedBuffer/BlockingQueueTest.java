package ch.hslu.AD.SW08.BoundedBuffer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Übung: Weiterführende Konzepte (N3)
 * Aufgabe: Bounded Buffer
 *
 * @author Fabian Gröger
 * @version 18.04.2018
 */
public class BlockingQueueTest {

    private static final int CAPACITY = 1_000_000;

    private Queue<String> queue;

    @Before
    public void initQueue() {
        queue = new ArrayBlockingQueue<>(CAPACITY);
    }

    @Test
    public void testMultipleProducersAndConsumers() {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        final int nProducers = 10;
        final int nConsumers = 10;
        int p = nProducers;
        int c = nConsumers;
        // make sure to mix producers and consumers
        while (p > 0 || c > 0) {
            if (p > 0) {
                final int producerNumber = nProducers - p;
                int toProduce = CAPACITY / nProducers;
                executor.submit(createProducer(toProduce, producerNumber));
                p--;
            }
            if (c > 0) {
                int toConsume = CAPACITY / nConsumers;
                executor.submit(createConsumer(toConsume));
                c--;
            }
        }
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(0, queue.size());
        Assert.assertTrue(queue.isEmpty());
    }

    private Runnable createProducer(int itemsToProduce, int producerNumber) {
        return () -> {
            for (int n = 0; n < itemsToProduce; n++) {
                queue.add(producerNumber + "String" + n);
            }
        };
    }

    private Runnable createConsumer(int itemsToConsume) {
        return () -> {
            int n = itemsToConsume;
            while (n > 0) {
                String str = queue.poll();
                if (str != null) {
                    Assert.assertNotNull(str);
                    n--;
                }
            }
        };
    }

}