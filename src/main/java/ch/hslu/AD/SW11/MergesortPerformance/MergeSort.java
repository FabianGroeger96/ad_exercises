package ch.hslu.AD.SW11.MergesortPerformance;

import java.util.concurrent.ForkJoinPool;

/**
 * Übung: Parallelisierungsframeworks (N4)
 * Aufgabe: Performance-Messungen an Mergesort
 *
 * @author Fabian Gröger
 * @version 03.05.2018
 */
public class MergeSort {

    public static void mergeSort(int array[], int threshold) {
        ForkJoinPool pool = new ForkJoinPool();
        MergeSortTask task = new MergeSortTask(array, threshold);
        pool.invoke(task);
    }
}
