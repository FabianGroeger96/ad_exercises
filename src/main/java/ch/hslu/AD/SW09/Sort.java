package ch.hslu.AD.SW09;

/**
 * Übung: Grundlagen, einfache Sortieralgorithmen (A1)
 * Aufgabe: Direktes Einfügen
 *
 * @author Fabian Gröger
 * @version 25.04.2018
 */
public class Sort {
    /**
     * Sortiert das int‐Array aufsteigend, mit direktem Einfügen (insertion sort)
     *
     * @param a Zu sortierendes Array.
     */
    public static void insertionSort(final Integer[] a) {
        int elt;
        int j;

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

    /**
     * Sortiert das int-Array aufsteigend, mit direktem Auswählen (selection sort)
     *
     * @param a Zu sortierendes Array.
     */
    public static void selectionSort(final Integer[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < a.length; j++)
                if (a[j] < a[index])
                    index = j;

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
}
