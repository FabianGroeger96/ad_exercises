package ch.hslu.AD.SW11.ParallelQuickSort;

import java.util.concurrent.ForkJoinPool;

/**
 * Übung: Parallelisierungsframeworks (N4)
 * Aufgabe: Quicksort parallelisieren
 *
 * @author Fabian Gröger
 * @version 09.05.2018
 */
public class QuickSort {

    public static void quickSort(int array[], int threshold) {
        ForkJoinPool pool = new ForkJoinPool();
        QuickSortTask task = new QuickSortTask(array, threshold);
        pool.invoke(task);
    }
}
