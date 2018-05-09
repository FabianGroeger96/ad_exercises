package ch.hslu.AD.SW11.ParallelQuickSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * Übung: Parallelisierungsframeworks (N4)
 * Aufgabe: Quicksort parallelisieren
 *
 * @author Fabian Gröger
 * @version 09.05.2018
 */
public class QuickSortTask extends RecursiveAction {

    private final int[] array;
    private final int min;
    private final int max;
    private final int threshold;

    public QuickSortTask(final int array[], final int threshold) {
        this.array = array;
        this.min = 0;
        this.max = array.length - 1;
        this.threshold = threshold;
    }

    public QuickSortTask(final int array[], final int min, final int max, final int threshold) {
        this.array = array;
        this.min = min;
        this.max = max;
        this.threshold = threshold;
    }

    @Override
    protected void compute() {
        int up = min;
        int down = max - 1;
        int t = array[max];
        do {
            while (array[up] < t) {
                up++;
            }
            while (array[down] >= t && down > up) {
                down--;
            }
            if (up < down) {
                exchange(array, up, down);
            }
        } while (up < down);

        exchange(array, up, max);

        List<QuickSortTask> tasks = new ArrayList<>();

        if (min < up - 1) {
            int size = (up - 1) - min + 1;
            if (size < threshold) {
                Arrays.sort(array, min, up);
            } else {
                tasks.add(new QuickSortTask(array, min, up - 1, threshold));
            }
        }

        if (max > up + 1) {
            int size = max - (up + 1) + 1;
            if (size < threshold) {
                Arrays.sort(array, up + 1, max + 1);
            } else {
                tasks.add(new QuickSortTask(array, up + 1, max, threshold));
            }
        }

        if (!tasks.isEmpty()) {
            invokeAll(tasks);
        }
    }

    /**
     * Vertauscht zwei bestimmte Zeichen im Array.
     *
     * @param data        Zeichen‐Array
     * @param firstIndex  Index des ersten Zeichens
     * @param secondIndex Index des zweiten Zeichens
     */
    private static void exchange(final int data[], final int firstIndex, final int secondIndex) {
        int tmp = data[firstIndex];
        data[firstIndex] = data[secondIndex];
        data[secondIndex] = tmp;
    }
}
