package ch.hslu.AD.SW09;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertFalse;

/**
 * Übung: Grundlagen, einfache Sortieralgorithmen (A1)
 * Aufgabe: Direktes Einfügen
 *
 * @author Fabian Gröger
 * @version 25.04.2018
 */
public class SortTest {

    private static final int ARRAY_SIZE = 50000;
    private static final Logger LOG = LogManager.getLogger(SortTest.class);

    private Integer[] bigArraySorted;
    private Integer[] bigArrayReverseSorted;
    private Integer[] bigArrayUnsorted;

    private long start;
    private long end;

    @Before
    public void setup() {
        bigArraySorted = new Integer[ARRAY_SIZE];
        bigArrayReverseSorted = new Integer[ARRAY_SIZE];
        bigArrayUnsorted = new Integer[ARRAY_SIZE];

        Random random = new Random();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            bigArraySorted[i] = i;
            bigArrayReverseSorted[i] = ARRAY_SIZE - i;
            bigArrayUnsorted[i] = random.nextInt(ARRAY_SIZE * 10);
        }
    }

    @Test
    public void testInsertionSortTime() {
        // test with a big array sorted
        start = System.currentTimeMillis();
        Sort.insertionSort(bigArraySorted);
        end = System.currentTimeMillis();

        LOG.info("Insertion sort - Big array sorted: " + (end - start));
        // check if array was correctly sorted
        assertIsSorted(bigArraySorted, Order.ASCENDING);

        // test with a big array reverse sorted
        start = System.currentTimeMillis();
        Sort.insertionSort(bigArrayReverseSorted);
        end = System.currentTimeMillis();

        LOG.info("Insertion sort - Big array reverse sorted: " + (end - start));
        // check if array was correctly sorted
        assertIsSorted(bigArrayReverseSorted, Order.ASCENDING);

        // test with a big array unsorted
        start = System.currentTimeMillis();
        Sort.insertionSort(bigArrayUnsorted);
        end = System.currentTimeMillis();

        LOG.info("Insertion sort - Big array unsorted: " + (end - start));
        // check if array was correctly sorted
        assertIsSorted(bigArrayUnsorted, Order.ASCENDING);
    }

    @Test
    public void testBinaryInsertionSortTime() {
        // test with a big array sorted
        start = System.currentTimeMillis();
        Sort.binaryInsertionSort(bigArraySorted);
        end = System.currentTimeMillis();

        LOG.info("Binary Insertion sort - Big array sorted: " + (end - start));
        // check if array was correctly sorted
        assertIsSorted(bigArraySorted, Order.ASCENDING);

        // test with a big array reverse sorted
        start = System.currentTimeMillis();
        Sort.binaryInsertionSort(bigArrayReverseSorted);
        end = System.currentTimeMillis();

        LOG.info("Binary Insertion sort - Big array reverse sorted: " + (end - start));
        // check if array was correctly sorted
        assertIsSorted(bigArrayReverseSorted, Order.ASCENDING);

        // test with a big array unsorted
        start = System.currentTimeMillis();
        Sort.binaryInsertionSort(bigArrayUnsorted);
        end = System.currentTimeMillis();

        LOG.info("Binary Insertion sort - Big array unsorted: " + (end - start));
        // check if array was correctly sorted
        assertIsSorted(bigArrayUnsorted, Order.ASCENDING);
    }

    @Test
    public void testSelectionSortTime() {
        // test with a big array sorted
        start = System.currentTimeMillis();
        Sort.selectionSort(bigArraySorted);
        end = System.currentTimeMillis();

        LOG.info("Selection sort - Big array sorted: " + (end - start));
        // check if array was correctly sorted
        assertIsSorted(bigArraySorted, Order.ASCENDING);

        // test with a big array reverse sorted
        start = System.currentTimeMillis();
        Sort.selectionSort(bigArrayReverseSorted);
        end = System.currentTimeMillis();

        LOG.info("Selection sort - Big array reverse sorted: " + (end - start));
        // check if array was correctly sorted
        assertIsSorted(bigArrayReverseSorted, Order.ASCENDING);

        // test with a big array unsorted
        start = System.currentTimeMillis();
        Sort.selectionSort(bigArrayUnsorted);
        end = System.currentTimeMillis();

        LOG.info("Selection sort - Big array unsorted: " + (end - start));
        // check if array was correctly sorted
        assertIsSorted(bigArrayUnsorted, Order.ASCENDING);
    }

    @Test
    public void testBubbleSortTime() {
        // test with a big array sorted
        start = System.currentTimeMillis();
        Sort.bubbleSort(bigArraySorted);
        end = System.currentTimeMillis();

        LOG.info("Bubble sort - Big array sorted: " + (end - start));
        // check if array was correctly sorted
        assertIsSorted(bigArraySorted, Order.ASCENDING);

        // test with a big array reverse sorted
        start = System.currentTimeMillis();
        Sort.bubbleSort(bigArrayReverseSorted);
        end = System.currentTimeMillis();

        LOG.info("Bubble sort - Big array reverse sorted: " + (end - start));
        // check if array was correctly sorted
        assertIsSorted(bigArrayReverseSorted, Order.ASCENDING);

        // test with a big array unsorted
        start = System.currentTimeMillis();
        Sort.bubbleSort(bigArrayUnsorted);
        end = System.currentTimeMillis();

        LOG.info("Bubble sort - Big array unsorted: " + (end - start));
        // check if array was correctly sorted
        assertIsSorted(bigArrayUnsorted, Order.ASCENDING);
    }

    @Test
    public void testShellSortTime() {
        // test with a big array sorted
        start = System.currentTimeMillis();
        Sort.shellSort(bigArraySorted);
        end = System.currentTimeMillis();

        LOG.info("Shell sort - Big array sorted: " + (end - start));
        // check if array was correctly sorted
        assertIsSorted(bigArraySorted, Order.ASCENDING);

        // test with a big array reverse sorted
        start = System.currentTimeMillis();
        Sort.shellSort(bigArrayReverseSorted);
        end = System.currentTimeMillis();

        LOG.info("Shell sort - Big array reverse sorted: " + (end - start));
        // check if array was correctly sorted
        assertIsSorted(bigArrayReverseSorted, Order.ASCENDING);

        // test with a big array unsorted
        start = System.currentTimeMillis();
        Sort.shellSort(bigArrayUnsorted);
        end = System.currentTimeMillis();

        LOG.info("Shell sort - Big array unsorted: " + (end - start));
        // check if array was correctly sorted
        assertIsSorted(bigArrayUnsorted, Order.ASCENDING);
    }

    public enum Order {
        ASCENDING, DESCENDING
    }

    @SuppressWarnings("unchecked")
    public <T> boolean assertIsSorted(Comparable<T> a[], Order order) {
        for (int i = 0; i < a.length - 1; i++) {
            Comparable<T> current = a[i];
            Comparable<T> next = a[i + 1];

            int result = current.compareTo((T) next);
            switch (order) {
                case ASCENDING:
                    assertFalse(result > 0);
                    break;
                case DESCENDING:
                    assertFalse(result < 0);
                    break;
            }
        }

        return true;
    }
}