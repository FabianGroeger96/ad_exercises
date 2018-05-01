package ch.hslu.AD.SW10.Heap;

/**
 * Übung: Höhere Sortieralgorithmen (A2)
 * Aufgabe: Datenstruktur Heap
 *
 * @author Fabian Gröger
 * @version 01.05.2018
 */
public interface IntegerHeap {

    public void insert(int element);

    public int getMax();

    public int getSize();
}
