package ch.hslu.AD.SW10.Sorting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Übung: Höhere Sortieralgorithmen (A2)
 *
 * @author Fabian Gröger
 * @version 26.04.2018
 */
public class SortTest {

    private static final int ARRAY_ELEMENTS[] = {1_000, 5_000, 10_000, 50_000, 100_000, 500_000};
    private static final int ARRAY_MS[] = {5, 10, 15, 20, 25, 30, 40, 50, 75, 100, 125, 150, 200, 250, 500, 1_000};
    private static final int DEFAULT_SIZE = 100_000;
    private static final int DEFAULT_M = 50;
    private static final Logger LOG = LogManager.getLogger(SortTest.class);

    private Character[] charArray;
    private String[] stringArray;
    private Integer[] integerArray;

    @Before
    public void setup() {
        charArray = SortUtils.randomChars(DEFAULT_SIZE);
        stringArray = SortUtils.randomStrings(DEFAULT_SIZE);
        integerArray = SortUtils.randomIntegers(DEFAULT_SIZE);
    }

    @Test
    public void testQuickSort() {
        Sort.quickSort(charArray, 0, charArray.length - 1);
        boolean sorted = SortUtils.isSorted(charArray, SortUtils.Order.ASCENDING);
        assertTrue(sorted);
    }

    @Test
    public void testQuickSort2() {
        Sort.quickSort(charArray);
        boolean sorted = SortUtils.isSorted(charArray, SortUtils.Order.ASCENDING);
        assertTrue(sorted);
    }

    @Test
    public void testQuickSortGenericString() {
        Sort.quickSort(stringArray);
        boolean sorted = SortUtils.isSorted(stringArray, SortUtils.Order.ASCENDING);
        assertTrue(sorted);
    }

    @Test
    public void testQuickSortGenericInteger() {
        Sort.quickSort(integerArray);
        boolean sorted = SortUtils.isSorted(integerArray, SortUtils.Order.ASCENDING);
        assertTrue(sorted);
    }

    @Test
    public void benchmarkQuickSort() {
        LOG.info("quick sort:");
        LOG.info(String.format("%8s %13s", "Elements", "Time"));

        for (int n : ARRAY_ELEMENTS) {
            Character data[] = SortUtils.randomChars(n);

            long start = System.currentTimeMillis();
            Sort.quickSort(data);
            long end = System.currentTimeMillis();

            assertTrue(SortUtils.isSorted(data, SortUtils.Order.ASCENDING));
            LOG.info(String.format("%8d %10d ms", n, end - start));
        }
    }

    @Test
    public void testQuickInsertionSort() {
        Sort.quickInsertionSort(charArray, DEFAULT_M);
        boolean sorted = SortUtils.isSorted(charArray, SortUtils.Order.ASCENDING);
        assertTrue(sorted);
    }

    @Test
    public void benchmarkQuickInsertionSort() {
        LOG.info("quick insertion sort:");
        LOG.info(String.format("%4s %13s", "M", "Time"));

        for (int m : ARRAY_MS) {
            Character data[] = SortUtils.randomChars(DEFAULT_SIZE);

            long start = System.currentTimeMillis();
            Sort.quickInsertionSort(data, m);
            long end = System.currentTimeMillis();

            assertTrue(SortUtils.isSorted(data, SortUtils.Order.ASCENDING));
            LOG.info(String.format("%4d %10d ms", m, end - start));
        }
    }

    @Test
    public void compareQuickSortWithQuickInsertionSort() {
        LOG.info("quick sort vs quick insertion sort:");
        LOG.info(String.format("%10s %20s %30s", "Elements", "Quick Sort", "Quick insertion sort"));

        for (int n : ARRAY_ELEMENTS) {
            Character data[] = SortUtils.randomChars(n);

            long startQuickSort = System.currentTimeMillis();
            Sort.quickSort(data);
            long endQuickSort = System.currentTimeMillis();

            long startQuickInsertionSort = System.currentTimeMillis();
            Sort.quickInsertionSort(data, DEFAULT_M);
            long endQuickInsertionSort = System.currentTimeMillis();

            LOG.info(String.format("%10d %17d ms %27d ms", n, endQuickSort - startQuickSort, endQuickInsertionSort - startQuickInsertionSort));
        }
    }
}