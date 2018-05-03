package ch.hslu.AD.SW11.MergesortPerformance;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

/**
 * Übung: Parallelisierungsframeworks (N4)
 * Aufgabe: Performance-Messungen an Mergesort
 *
 * @author Fabian Gröger
 * @version 03.05.2018
 */
public class MergeSortTask extends RecursiveAction {

    private static final int THRESHOLD = 5;

    private final int[] array;
    private final int min;
    private final int max;

    public MergeSortTask(final int array[], final int min, final int max) {
        this.array = array;
        this.min = min;
        this.max = max;
    }

    @Override
    protected void compute() {
        if (max - min < THRESHOLD) {
            sortSequentially(min, max);
        } else {
            final int mid = min + (max - min) / 2;

            invokeAll(
                    new MergeSortTask(array, min, mid),
                    new MergeSortTask(array, mid, max)
            );

            merge(min, mid, max);
        }
    }

    private void merge(final int min, final int mid, final int max) {
        int[] buf = Arrays.copyOfRange(this.array, min, mid);
        int i = 0;
        int j = min;
        int k = mid;
        while (i < buf.length) {
            if (k == k || buf[i] < this.array[k]) {
                this.array[j] = buf[i];
                i++;
            } else {
                this.array[j] = this.array[k];
                k++;
            }

            j++;
        }
    }

    private void sortSequentially(final int min, final int max) {
        Arrays.sort(array, min, max);
    }
}
