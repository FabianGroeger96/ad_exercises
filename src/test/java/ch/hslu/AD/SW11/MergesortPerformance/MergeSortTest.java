package ch.hslu.AD.SW11.MergesortPerformance;

import ch.hslu.AD.SW09.Sort;
import ch.hslu.AD.SW10.Sorting.SortUtils;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Übung: Parallelisierungsframeworks (N4)
 * Aufgabe: Performance-Messungen an Mergesort
 *
 * @author Fabian Gröger
 * @version 09.05.2018
 */
public class MergeSortTest {

    private static final int ARRAY_SIZE = 100_000_000;
    private static final int[] ARRAY_ELEMENTS = new int[]{1_000, 5_000, 10_000, 50_000, 100_000, 500_000, 1_000_000};
    private static final int[] THRESHOLDS = new int[]{10, 25, 50, 75, 100, 200, 250};

    @Test
    public void testMergeSort() {
        int[] array = SortUtils.randomInt(ARRAY_SIZE);
        MergeSort.mergeSort(array, 10);
        assertTrue(SortUtils.isSorted(array, SortUtils.Order.ASCENDING));
    }

    @Test
    public void benchmarkMergeSort() {
        System.out.println("Threshold duration");
        System.out.println("--------- --------");

        for (int threshold : THRESHOLDS) {
            int[] array = SortUtils.randomInt(ARRAY_SIZE);

            long start = System.currentTimeMillis();
            MergeSort.mergeSort(array, threshold);
            long end = System.currentTimeMillis();

            assertTrue(SortUtils.isSorted(array, SortUtils.Order.ASCENDING));
            System.out.printf("%9d %8d\n", threshold, end - start);
        }
    }

    @Test
    public void benchmarkMergeSortVSShellSort() {
        System.out.println("merge sort vs shell sort:");
        System.out.println(String.format("%10s %20s %30s", "Elements", "Merge Sort", "Shell Sort"));

        for (int elements : ARRAY_ELEMENTS) {
            int data[] = SortUtils.randomInt(elements);

            long startMergeSort = System.currentTimeMillis();
            MergeSort.mergeSort(data, 50);
            long endMergeSort = System.currentTimeMillis();

            data = SortUtils.randomInt(elements);

            long startShellSort = System.currentTimeMillis();
            Sort.shellSort(data);
            long endShellSort = System.currentTimeMillis();

            System.out.println(String.format("%10d %17d ms %27d ms", elements, endMergeSort - startMergeSort, endShellSort - startShellSort));
        }
    }
}