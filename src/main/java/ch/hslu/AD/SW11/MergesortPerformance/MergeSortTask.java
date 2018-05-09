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

    private final int[] array;
    private final int min;
    private final int max;
    private final int threshold;

    public MergeSortTask(final int array[], final int min, final int max, final int threshold) {
        this.array = array;
        this.min = min;
        this.max = max;
        this.threshold = threshold;
    }

    public MergeSortTask(final int array[], final int threshold) {
        this(array, 0, array.length, threshold);
    }

    @Override
    protected void compute() {
        if (max - min < threshold) {
            sortSequentially(min, max);
        } else {
            final int mid = min + (max - min) / 2;

            invokeAll(
                    new MergeSortTask(array, min, mid, threshold),
                    new MergeSortTask(array, mid, max, threshold)
            );

            merge(min, mid, max);
        }
    }

    private void merge(final int min, final int mid, final int max) {
        int buf[] = Arrays.copyOfRange(array, min, mid);
        int readBuf = 0;
        int readArray = mid;
        int write = min;
        while (readBuf < buf.length) {
            if (readArray == max || buf[readBuf] < array[readArray]) {
                array[write++] = buf[readBuf++];
            } else {
                array[write++] = array[readArray++];
            }
        }
    }

    private void sortSequentially(final int min, final int max) {
        Arrays.sort(array, min, max);
    }
}
