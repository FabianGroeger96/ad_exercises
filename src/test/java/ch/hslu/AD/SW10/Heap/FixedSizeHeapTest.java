package ch.hslu.AD.SW10.Heap;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Übung: Höhere Sortieralgorithmen (A2)
 * Aufgabe: Datenstruktur Heap
 *
 * @author Fabian Gröger
 * @version 01.05.2018
 */
public class FixedSizeHeapTest {

    @Test
    public void testInsertElement() {
        FixedSizeHeap heap = new FixedSizeHeap(16);
        heap.insert(20);

        assertEquals(1, heap.getSize());
    }


}