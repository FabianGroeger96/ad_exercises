package ch.hslu.AD.SW10.Heap;

/**
 * Übung: Höhere Sortieralgorithmen (A2)
 * Aufgabe: Datenstruktur Heap
 *
 * @author Fabian Gröger
 * @version 01.05.2018
 */
public class FixedSizeHeap implements IntegerHeap {

    private int heap[];

    public FixedSizeHeap(int size) {
        this.heap = new int[size];
    }

    @Override
    public void insert(int element) {
        heap[heap.length - 1] = element;
        int newElementIndex = heap.length - 1;
        raise(newElementIndex);
    }

    @Override
    public int getMax() {
        if (heap.length == 0) {
            throw new IllegalStateException("Heap is empty.");
        }

        int max = heap[0];

        heap[0] = heap[heap.length - 1];
        heap[heap.length - 1] = -1; // oder 0

        sink();
        return max;
    }

    @Override
    public int getSize() {
        return heap.length;
    }

    private void sink() {
        final int size = heap.length;
        boolean sunk = false;

        int l = 1, f = 0, r = 2;

        while (!sunk && (l < size || r < size)) {
            int father = heap[f];

            int left = l < size ? heap[l] : father;
            int right = r < size ? heap[r] : father;

            if (father < left || father < right) {
                int biggerChildIndex = left > right ? l : r;
                swap(f, biggerChildIndex);
                f = biggerChildIndex;
                l = (2 * f) + 1;
                r = 2 * (f + 1);
            } else {
                sunk = true;
            }
        }
    }

    private void raise(int i) {
        boolean risen = false;

        while (!risen) {

            int father = (i - 1) / 2;

            if (heap[i] > heap[father]) {
                swap(i, father);
                i = father;
                if (i == 0) {
                    risen = true;
                }
            } else {
                risen = true;
            }
        }
    }

    private void swap(int a, int b) {
        int tmp = heap[a];
        heap[a] = heap[b];
        heap[b] = tmp;
    }
}
