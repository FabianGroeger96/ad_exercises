package ch.hslu.AD.SW02.ArrayRingBuffer;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

import static org.junit.Assert.*;

/**
 * Übung: Arrays, Listen, Stack und Queue (D1)
 * Aufgabe: Implementation einer Queue mit Array als Ringbuffer
 *
 * @author Fabian Gröger
 * @version 07.03.2018
 */
public class RingBufferTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testCreatingRingBuffer() {
        RingBuffer<Integer> ringBuffer = new RingBuffer<>();
        assertTrue(ringBuffer.isEmpty());
        assertFalse(ringBuffer.isFull());
        assertEquals(0, ringBuffer.used());
    }

    @Test
    public void testAddingElements() {
        RingBuffer<Integer> ringBuffer = new RingBuffer<>();
        ringBuffer.enqueue(6);
        assertFalse(ringBuffer.isEmpty());
        assertFalse(ringBuffer.isFull());
        assertEquals(1, ringBuffer.used());
    }

    @Test
    public void testFillingRingBuffer() {
        RingBuffer<Integer> ringBuffer = new RingBuffer<>(1);
        ringBuffer.enqueue(6);
        assertFalse(ringBuffer.isEmpty());
        assertTrue(ringBuffer.isFull());
        assertEquals(1, ringBuffer.used());
    }

    @Test
    public void testGettingElements() {
        RingBuffer<Integer> ringBuffer = new RingBuffer<>();
        ringBuffer.enqueue(6);
        assertEquals(ringBuffer.dequeue(), new Integer(6));
        assertEquals(0, ringBuffer.used());
    }

    @Test
    public void testOveflow() {
        RingBuffer<Character> queue = new RingBuffer<>(4);
        queue.enqueue('J');
        queue.enqueue('A');
        queue.enqueue('V');
        queue.enqueue('A');

        exception.expect(BufferOverflowException.class);
        queue.enqueue('S');
    }

    @Test
    public void testUnderflow() {
        RingBuffer<Character> queue = new RingBuffer<>(4);

        exception.expect(BufferUnderflowException.class);
        queue.dequeue();
    }


}