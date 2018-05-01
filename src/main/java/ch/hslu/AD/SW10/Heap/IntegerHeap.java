package ch.hslu.AD.SW10.Heap;

/**
 * Übung: Höhere Sortieralgorithmen (A2)
 * Aufgabe: Datenstruktur Heap
 *
 * @author Fabian Gröger
 * @version 01.05.2018
 */
public interface IntegerHeap {

    /**
     * Inserts an element to the heap
     *
     * @param element the element to insert
     */
    public void insert(int element);

    /**
     * Deletes an element at the index
     *
     * @param index index of the element to delete
     * @return the deleted element
     */
    public int delete(int index);

    /**
     * Returns the min child
     *
     * @return the min child
     */
    public int getMinChild();

    /**
     * Returns the used size of the heap
     *
     * @return used size
     */
    public int getUsedSize();

    /**
     * Checks if the heap is empty
     *
     * @return if the heap is empty
     */
    public boolean isEmpty();

    /**
     * Checks if the heap is full
     *
     * @return if the heap is full
     */
    public boolean isFull();

    /**
     * Prints the heap
     */
    public void print();
}
