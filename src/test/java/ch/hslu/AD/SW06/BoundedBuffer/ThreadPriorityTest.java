package ch.hslu.AD.SW06.BoundedBuffer;

import org.junit.Assert;
import org.junit.Test;

/**
 * Übung: Thread Steuerung (N2)
 * Aufgabe: Bounded Buffer
 *
 * @author Fabian Gröger
 * @version 11.04.2018
 */
public class ThreadPriorityTest {
    private static final int BUFFER_CAPACITY = 1024;
    private static final int ITEMS = 1024 * 1024;

    @Test
    public void highestVsLowestPriority() {
        BoundedBuffer<String> buffer = new BoundedBuffer<>(BUFFER_CAPACITY);

        final int nProducers = 2;
        final int itemsToProduce = ITEMS / nProducers;
        Producer producer1 = new Producer(itemsToProduce, buffer);
        Producer producer2 = new Producer(itemsToProduce, buffer);

        Consumer consumer1 = new Consumer(buffer);
        Consumer consumer2 = new Consumer(buffer);

        Thread p1 = new Thread(producer1);
        Thread p2 = new Thread(producer2);
        Thread c1 = new Thread(consumer1);
        Thread c2 = new Thread(consumer2);

        p1.start();
        p2.start();

        c1.setPriority(Thread.MAX_PRIORITY);
        c2.setPriority(Thread.MIN_PRIORITY);
        c1.start();
        c2.start();

        try {
            p1.join();
            p2.join();
            consumer1.requestStop();
            consumer2.requestStop();
            c1.join();
            c2.join();
            String log = String.format("consumer1 %d, consumer2 %d", consumer1.getConsumed(), consumer2.getConsumed());
            System.out.println(log);
            // consumer1 has MAX, consumer2 MIN priority, so theoretically...
            Assert.assertTrue(consumer1.getConsumed() > consumer2.getConsumed());
            // ... but only theoretically :-(
        } catch (InterruptedException iEx) {
            iEx.printStackTrace();
        }
    }
}
