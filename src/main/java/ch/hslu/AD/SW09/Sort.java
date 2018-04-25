package ch.hslu.AD.SW09;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

/**
 * Übung: Grundlagen, einfache Sortieralgorithmen (A1)
 * Aufgabe: Direktes Einfügen
 *
 * @author Fabian Gröger
 * @version 25.04.2018
 */
public class Sort {
    private static final Logger LOG = LogManager.getLogger(Sort.class);

    /**
     * Sortiert das int‐Array aufsteigend, mit direktem Einfügen (insertion sort)
     *
     * @param a Zu sortierendes Array.
     */
    public static void insertionSort(final Integer[] a) {
        int elt;
        int j;

        // Initialize a new array that is bigger than the given
        // the new array will be used to store the dummy element on the first position
        Integer[] dummyArray = new Integer[a.length + 1];
        System.arraycopy(a, 0, dummyArray, 1, a.length);

        for (int i = 2; i < dummyArray.length; i++) {
            elt = dummyArray[i]; // next elt for insert
            dummyArray[0] = elt; // dummy - element
            j = i; // a[1] ... a[j-1] already sorted

            while (dummyArray[j - 1] > elt) {
                dummyArray[j] = dummyArray[j - 1]; // shift right
                j--; // go further left
            }

            dummyArray[j] = elt; // insert elt
        } // a[1] ... a[j] sorted

        System.arraycopy(dummyArray, 1, a, 0, a.length); // copy array back to source array
    }

    public static void binaryInsertionSort(final Integer[] a) {
        for (int i = 1; i < a.length; i++) {
            int x = a[i];

            // Find location to insert using binary search
            int j = Math.abs(Arrays.binarySearch(a, 0, i, x) + 1);

            //Shifting array to one location right
            System.arraycopy(a, j, a, j + 1, i - j);

            //Placing element at its correct location
            a[j] = x;
        }
    }

    /**
     * Sortiert das int-Array aufsteigend, mit direktem Auswählen (selection sort)
     *
     * @param a Zu sortierendes Array.
     */
    public static void selectionSort(final Integer[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int index = i;

            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[index])
                    index = j;
            }

            int smallerNumber = a[index];
            a[index] = a[i];
            a[i] = smallerNumber;
        }
    }

    /**
     * Sortiert das int-Array aufsteigend, mit direktem Austauschen (bubble sort)
     *
     * @param a Zu sortierendes Array.
     */
    public static void bubbleSort(final Integer[] a) {
        int n = a.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    // swap temp and arr[i]
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Sortiert das int-Array aufsteigend, mit shell sort
     *
     * @param a Zu sortierendes Array.
     */
    public static void shellSort(final Integer[] a) {
        int n = a.length;

        // Start with a big gap, then reduce the gap
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Do a gapped insertion sort for this gap size.
            // The first gap elements a[0..gap-1] are already
            // in gapped order keep adding one more element
            // until the entire array is gap sorted
            for (int i = gap; i < n; i += 1) {
                // add a[i] to the elements that have been gap
                // sorted save a[i] in temp and make a hole at
                // position i
                int temp = a[i];

                // shift earlier gap-sorted elements up until
                // the correct location for a[i] is found
                int j;
                for (j = i; j >= gap && a[j - gap] > temp; j -= gap) {
                    a[j] = a[j - gap];
                }

                // put temp (the original a[i]) in its correct
                // location
                a[j] = temp;
            }
        }
    }
}
