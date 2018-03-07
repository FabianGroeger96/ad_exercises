package ch.hslu.AD.SW02.ArrayRingBuffer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Übung: Arrays, Listen, Stack und Queue (D1)
 * Aufgabe: Implementation einer Queue mit Array als Ringbuffer
 *
 * @author Fabian Gröger
 * @version 07.03.2018
 */
public class RingBufferApp {

    private static final Logger LOGGER = LogManager.getLogger(RingBuffer.class);

    public static void main(String[] args) {
        RingBuffer<Integer> ringBuffer = new RingBuffer<>();
        ringBuffer.enqueue(1);
        ringBuffer.enqueue(2);
        ringBuffer.enqueue(3);
        ringBuffer.enqueue(4);
        ringBuffer.enqueue(5);
        ringBuffer.enqueue(6);
        ringBuffer.enqueue(7);
        ringBuffer.enqueue(8);

        ringBuffer.dequeue();
        ringBuffer.dequeue();
        ringBuffer.dequeue();
        ringBuffer.dequeue();

        LOGGER.info(ringBuffer.toString());

    }
}
