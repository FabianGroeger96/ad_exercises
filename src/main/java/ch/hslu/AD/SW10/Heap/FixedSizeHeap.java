package ch.hslu.AD.SW10.Heap;

import java.util.Arrays;

/**
 * Übung: Höhere Sortieralgorithmen (A2)
 * Aufgabe: Datenstruktur Heap
 *
 * @author Fabian Gröger
 * @version 01.05.2018
 */
public class FixedSizeHeap implements IntegerHeap {

    private int heapSize;
    private int usedSize;
    private int heap[];

    /**
     * Constructor
     *
     * @param capacity size of the heap
     */
    public FixedSizeHeap(int capacity) {
        this.heapSize = 0;
        this.usedSize = 0;
        this.heap = new int[capacity + 1];

        Arrays.fill(heap, -1);
    }

    /**
     * Inserts an element to the heap and reorganizes it
     *
     * @param element the element to insert
     */
    @Override
    public void insert(int element) {
        if (isFull()) {
            throw new IllegalStateException("Heap is full.");
        }

        // Percolate up
        usedSize++;
        heap[heapSize++] = element; // insert element
        heapifyUp(heapSize - 1);

        // Percolate down (doesn't work)
        //usedSize++;
        //heap[heapSize++] = element;
        //heapifyDown(heapSize - 1);
    }

    /**
     * Function to delete element at an index
     *
     * @param index the index of the element to delete
     * @return the deleted item
     */
    @Override
    public int delete(int index) {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty.");
        }

        int keyItem = heap[index];
        heap[index] = heap[heapSize - 1];
        usedSize--;
        heapSize--;
        heapifyDown(index);
        return keyItem;
    }

    /**
     * Function to delete min element
     *
     * @return the deleted item
     */
    public int deleteMin() {
        int keyItem = heap[0];
        delete(0);
        return keyItem;
    }

    /**
     * Function to find least element
     */
    @Override
    public int getMinChild() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty.");
        }
        return heap[0];
    }

    /**
     * Function to check if heap is empty
     *
     * @return if heap is empty
     */
    @Override
    public boolean isEmpty() {
        return heapSize == 0;
    }

    /**
     * Check if heap is full
     *
     * @return if heap is full
     */
    @Override
    public boolean isFull() {
        return heapSize == heap.length;
    }

    /**
     * Function to get index parent of i
     *
     * @param index index of element to search parent of
     * @return index of parent
     */
    private int parent(int index) {
        return (index - 1) / 2; // 2 = binary heap
    }

    /**
     * Function to get index of k th child of i
     *
     * @return index of k th child
     */
    private int kthChild(int index, int k) {
        return 2 * index + k; // 2 = binary heap
    }

    /**
     * Function heapifyUp
     **/
    private void heapifyUp(int childInd) {
        int tmp = heap[childInd];
        while (childInd > 0 && tmp < heap[parent(childInd)]) {
            heap[childInd] = heap[parent(childInd)];
            childInd = parent(childInd);
        }
        heap[childInd] = tmp;
    }

    /**
     * Function heapifyDown
     **/
    private void heapifyDown(int ind) {
        int child;
        int tmp = heap[ind];
        while (kthChild(ind, 1) < heapSize) {
            child = minChild(ind);
            if (heap[child] < tmp)
                heap[ind] = heap[child];
            else
                break;
            ind = child;
        }
        heap[ind] = tmp;
    }

    /**
     * Function to get smallest child
     */
    private int minChild(int ind) {
        int bestChild = kthChild(ind, 1);
        int k = 2;
        int pos = kthChild(ind, k);
        while ((k <= 2) && (pos < heapSize)) {
            if (heap[pos] < heap[bestChild])
                bestChild = pos;
            pos = kthChild(ind, k++);
        }
        return bestChild;
    }

    /**
     * Function to print heap
     */
    @Override
    public void print() {
        System.out.print("Heap = ");
        for (int i = 0; i < heapSize; i++)
            System.out.print(heap[i] + " ");
        System.out.println("\n");
    }

    /**
     * Returns the used size in the heap
     *
     * @return the used size
     */
    @Override
    public int getUsedSize() {
        return usedSize;
    }
}
