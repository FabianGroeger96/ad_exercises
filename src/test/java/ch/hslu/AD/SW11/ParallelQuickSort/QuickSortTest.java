package ch.hslu.AD.SW11.ParallelQuickSort;

import ch.hslu.AD.SW10.Sorting.Sort;
import ch.hslu.AD.SW10.Sorting.SortUtils;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * Übung: Parallelisierungsframeworks (N4)
 * Aufgabe: Quicksort parallelisieren
 *
 * @author Fabian Gröger
 * @version 09.05.2018
 */
public class QuickSortTest {

    private static final int ARRAY_SIZE = 10_000_000;
    private static final int THRESHOLD = 50;
    private static final int[] ARRAY_ELEMENTS = new int[]{1_000, 5_000, 10_000, 50_000, 100_000, 500_000, 1_000_000, 10_000_000, 100_000_000};
    private static final int[] THRESHOLDS = new int[]{10, 25, 50, 75, 100, 200, 250};

    @Test
    public void testQuickSort() {
        int[] array = SortUtils.randomInt(ARRAY_SIZE);
        QuickSort.quickSort(array, THRESHOLD);
        assertTrue(SortUtils.isSorted(array, SortUtils.Order.ASCENDING));
    }

    @Test
    public void benchmarkQuickSort() {
        System.out.println("Threshold duration");
        System.out.println("--------- --------");

        for (int threshold : THRESHOLDS) {
            int[] array = SortUtils.randomInt(ARRAY_SIZE);

            long start = System.currentTimeMillis();
            QuickSort.quickSort(array, threshold);
            long end = System.currentTimeMillis();

            assertTrue(SortUtils.isSorted(array, SortUtils.Order.ASCENDING));
            System.out.printf("%9d %8d\n", threshold, end - start);
        }
    }

    @Test
    public void benchmarkPerformance() {
        System.out.println("parallel quick sort vs quick sort vs arrays.sort:");
        System.out.println(String.format("%10s %20s %30s %40s", "Elements", "PQS", "QS", "AS"));

        for (int elements : ARRAY_ELEMENTS) {
            int data[] = SortUtils.randomInt(elements);
            long startPQuickSort = System.currentTimeMillis();
            QuickSort.quickSort(data, THRESHOLD);
            long endPQuickSort = System.currentTimeMillis();

            Integer[] array = SortUtils.randomIntegers(elements);
            long startQuickSort = System.currentTimeMillis();
            Sort.quickInsertionSort(array, THRESHOLD);
            long endQuickSort = System.currentTimeMillis();

            data = SortUtils.randomInt(elements);
            long startArraysSort = System.currentTimeMillis();
            Arrays.sort(data);
            long endArraysSort = System.currentTimeMillis();

            System.out.println(String.format("%10d %17d ms %27d ms %37d ms", elements, endPQuickSort - startPQuickSort, endQuickSort - startQuickSort, endArraysSort - startArraysSort));
        }
    }

}