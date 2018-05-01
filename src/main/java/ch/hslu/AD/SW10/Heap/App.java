package ch.hslu.AD.SW10.Heap;

/**
 * Übung: Höhere Sortieralgorithmen (A2)
 * Aufgabe: Datenstruktur Heap
 *
 * @author Fabian Gröger
 * @version 01.05.2018
 */
public class App {

    private static final int ELEMENTS[] = new int[]{20, 10, 5, 12, 7, 50};

    /**
     * main method
     *
     * @param args start arguments
     */
    public static void main(final String[] args) {
        IntegerHeap heap = new FixedSizeHeap(16);

        for (int i = 0; i < ELEMENTS.length; i++) {
            int element = ELEMENTS[i];
            System.out.println("Inserted: " + element);
            heap.insert(element);
            heap.print();
        }
    }
}
