package ch.hslu.AD.SW10.Sorting;

import java.util.Arrays;

/**
 * Übung: Höhere Sortieralgorithmen (A2)
 *
 * @author Fabian Gröger
 * @version 26.04.2018
 */
public class Sort {

    /**
     * Sortiert das generische Array aufsteigend, mit direktem Einfügen (insertion sort)
     *
     * @param data Zu sortierendes Array
     * @param from startindex zum Sortieren
     * @param to   endindex zum Sortieren
     * @param <T>  generisch Implementiert
     */
    public static <T> void insertionSort(Comparable<T> data[], int from, int to) {
        for (int outerloop = from + 1; outerloop <= to; outerloop++) {
            for (int innerloop = outerloop; innerloop > 0 && data[innerloop].compareTo((T) data[innerloop - 1]) < 0; innerloop--) {
                exchange(data, innerloop, innerloop - 1); // tauschen des Elements mit dem vorherigen
            }
        }
    }

    /**
     * Sortiert das int-Array aufsteigend, mit direktem Einfügen (insertion sort)
     * Suche nach der nächsten freien stelle wird mit binärer Suche realisiert
     *
     * @param data Zu sortierendes Array.
     */
    public static void binaryInsertionSort(final Integer[] data) {
        for (int i = 1; i < data.length; i++) {
            int x = data[i];

            // Find location to insert using binary search
            int j = Math.abs(Arrays.binarySearch(data, 0, i, x) + 1);

            //Shifting array to one location right
            System.arraycopy(data, j, data, j + 1, i - j);

            //Placing element at its correct location
            data[j] = x;
        }
    }

    /**
     * Sortiert das int-Array aufsteigend, mit direktem Auswählen (selection sort)
     *
     * @param data Zu sortierendes Array.
     */
    public static void selectionSort(final Integer[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            int index = i;

            for (int j = i + 1; j < data.length; j++) {
                if (data[j] < data[index])
                    index = j;
            }

            int smallerNumber = data[index];
            data[index] = data[i];
            data[i] = smallerNumber;
        }
    }

    /**
     * Sortiert das int-Array aufsteigend, mit direktem Austauschen (bubble sort)
     *
     * @param data Zu sortierendes Array.
     */
    public static void bubbleSort(final Integer[] data) {
        int n = data.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    // swap temp and arr[i]
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Sortiert das int-Array aufsteigend, mit shell sort
     *
     * @param data Zu sortierendes Array.
     */
    public static void shellSort(final Integer[] data) {
        int n = data.length;

        // Start with data big gap, then reduce the gap
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Do data gapped insertion sort for this gap size.
            // The first gap elements data[0..gap-1] are already
            // in gapped order keep adding one more element
            // until the entire array is gap sorted
            for (int i = gap; i < n; i += 1) {
                // add data[i] to the elements that have been gap
                // sorted save data[i] in temp and make data hole at
                // position i
                int temp = data[i];

                // shift earlier gap-sorted elements up until
                // the correct location for data[i] is found
                int j;
                for (j = i; j >= gap && data[j - gap] > temp; j -= gap) {
                    data[j] = data[j - gap];
                }

                // put temp (the original data[i]) in its correct
                // location
                data[j] = temp;
            }
        }
    }

    /**
     * Vertauscht zwei bestimmte Zeichen im Array.
     *
     * @param data        Zeichen‐Array
     * @param firstIndex  Index des ersten Zeichens
     * @param secondIndex Index des zweiten Zeichens
     */
    private static <T> void exchange(final T data[], final int firstIndex, final int secondIndex) {
        T tmp = data[firstIndex];
        data[firstIndex] = data[secondIndex];
        data[secondIndex] = tmp;
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

    /**
     * Sortiert ein Array von Chars mit dem quicksort Algorithmus
     *
     * @param data  das Array von Chars (Daten)
     * @param left  die linke Grenze
     * @param right die rechte Grenze
     */
    public static final void quickSort(final Character[] data, final int left, final int right) {
        int up = left; // linke Grenze
        int down = right - 1; // rechte Grenze (ohne Trennelement) -> -1 = Trennelement
        char t = data[right]; //rechtes Element als Trennelement
        boolean allChecked = false;

        do {
            while (data[up] < t) {
                up++; // suche grösseres (>=) Element von links an
            }
            while ((data[down] >= t) && (down > up)) {
                down--; // suche echt kleineres (<) Element von rechts an
            }
            if (down > up) { // solange keine Überschneidung
                exchange(data, up, down);
                up++; // linke Grenze verschieben
                down--; // rechte Grenze verschieben
            } else {
                allChecked = true; // Austauschen beendet
            }
        } while (!allChecked);

        exchange(data, up, right); // Trennelement an endgültige Position (data[up])

        if (left < (up - 1)) {
            quickSort(data, left, (up - 1)); // linke Hälfte
        }

        if ((up + 1) < right) {
            quickSort(data, (up + 1), right); // rechte Hälfte, ohne Trennelement
        }
    }

    /**
     * Sortiert ein Array mit dem quicksort Algorithmus
     *
     * @param data  das Array (Daten)
     * @param left  die linke Grenze
     * @param right die rechte Grenze
     * @param <T>   generisch implementiert
     */
    public static <T extends Comparable<T>> void quickSort(final T[] data, final int left, final int right) {
        int up = left; // linke Grenze
        int down = right - 1; // rechte Grenze (ohne Trennelement) -> -1 = Trennelement
        T t = data[right]; //rechtes Element als Trennelement
        boolean allChecked = false;

        do {
            while (data[up].compareTo(t) < 0) {
                up++; // suche grösseres (>=) Element von links an
            }
            while ((data[down].compareTo(t) >= 0) && (down > up)) {
                down--; // suche echt kleineres (<) Element von rechts an
            }
            if (down > up) { // solange keine Überschneidung
                exchange(data, up, down);
                up++; // linke Grenze verschieben
                down--; // rechte Grenze verschieben
            } else {
                allChecked = true; // Austauschen beendet
            }
        } while (!allChecked);

        exchange(data, up, right); // Trennelement an endgültige Position (data[up])

        if (left < (up - 1)) {
            quickSort(data, left, (up - 1)); // linke Hälfte
        }

        if ((up + 1) < right) {
            quickSort(data, (up + 1), right); // rechte Hälfte, ohne Trennelement
        }
    }

    /**
     * Sortiert ein Array von Chars mit dem quicksort Algorithmus
     *
     * @param data das Array von Chars (Daten)
     */
    public static final void quickSort(final Character[] data) {
        quickSort(data, 0, data.length - 1);
    }

    /**
     * Sortiert ein Array mit dem quicksort Algorithmus
     *
     * @param data das Array (Daten)
     * @param <T>  generisch implementiert
     */
    public static <T extends Comparable<T>> void quickSort(final T[] data) {
        quickSort(data, 0, data.length - 1);
    }

    /**
     * Sortiert ein Array von Chars mit dem quicksort Algorithmus,
     * wenn die Daten einen gewissen Schwellwert erreicht haben wird das Array
     * mit insertionsort sortiert
     *
     * @param data  das Array von Chars (Daten)
     * @param left  die linke Grenze
     * @param right die rechte Grenze
     * @param m     der Schwellwert
     */
    static void quickInsertionSort(final Character[] data, final int left, final int right, final int m) {
        int up = left; // linke Grenze
        int down = right - 1; // rechte Grenze (ohne Trennelement) -> -1 = Trennelement
        char t = data[right]; //rechtes Element als Trennelement
        boolean allChecked = false;
        do {
            while (data[up] < t) {
                up++; // suche grösseres (>=) Element von links an
            }
            while ((data[down] >= t) && (down > up)) {
                down--; // suche echt kleineres (<) Element von rechts an
            }
            if (down > up) { // solange keine Überschneidung
                exchange(data, up, down);
                up++; // linke Grenze verschieben
                down--; // rechte Grenze verschieben
            } else {
                allChecked = true; // Austauschen beendet
            }
        } while (!allChecked);
        exchange(data, up, right); // Trennelement an endgültige Position (data[up])

        // veränderte Rekursionsanweisungen
        if (left < up - 1) {
            int from = left;
            int to = up - 1;
            if (to - from + 1 > m) { // vergleichen der grösse der zu sortierenden daten mit dem Schwellwert
                quickInsertionSort(data, from, to, m);
            } else {
                insertionSort(data, from, to);
            }
        }

        if (right > up + 1) { // vergleichen der grösse der zu sortierenden daten mit dem Schwellwert
            int from = up + 1;
            int to = right;
            if (to - from + 1 > m) {
                quickInsertionSort(data, from, to, m);
            } else {
                insertionSort(data, from, to);
            }
        }
    }

    /**
     * Sortiert ein Array von Integers mit dem quicksort Algorithmus,
     * wenn die Daten einen gewissen Schwellwert erreicht haben wird das Array
     * mit insertionsort sortiert
     *
     * @param data  das Array von Chars (Daten)
     * @param left  die linke Grenze
     * @param right die rechte Grenze
     * @param m     der Schwellwert
     */
    static void quickInsertionSort(final Integer[] data, final int left, final int right, final int m) {
        int up = left; // linke Grenze
        int down = right - 1; // rechte Grenze (ohne Trennelement) -> -1 = Trennelement
        int t = data[right]; //rechtes Element als Trennelement
        boolean allChecked = false;
        do {
            while (data[up] < t) {
                up++; // suche grösseres (>=) Element von links an
            }
            while ((data[down] >= t) && (down > up)) {
                down--; // suche echt kleineres (<) Element von rechts an
            }
            if (down > up) { // solange keine Überschneidung
                exchange(data, up, down);
                up++; // linke Grenze verschieben
                down--; // rechte Grenze verschieben
            } else {
                allChecked = true; // Austauschen beendet
            }
        } while (!allChecked);
        exchange(data, up, right); // Trennelement an endgültige Position (data[up])

        // veränderte Rekursionsanweisungen
        if (left < up - 1) {
            int from = left;
            int to = up - 1;
            if (to - from + 1 > m) { // vergleichen der grösse der zu sortierenden daten mit dem Schwellwert
                quickInsertionSort(data, from, to, m);
            } else {
                insertionSort(data, from, to);
            }
        }

        if (right > up + 1) { // vergleichen der grösse der zu sortierenden daten mit dem Schwellwert
            int from = up + 1;
            int to = right;
            if (to - from + 1 > m) {
                quickInsertionSort(data, from, to, m);
            } else {
                insertionSort(data, from, to);
            }
        }
    }

    /**
     * Sortiert ein Array von Chars mit dem quicksort Algorithmus,
     * wenn die Daten einen gewissen Schwellwert erreicht haben wird das Array
     * mit insertionsort sortiert
     *
     * @param data das Array von Chars (Data)
     * @param m    der Schwellwert
     */
    public static void quickInsertionSort(final Character[] data, final int m) {
        quickInsertionSort(data, 0, data.length - 1, m);
    }

    /**
     * Sortiert ein Array von Integers mit dem quicksort Algorithmus,
     * wenn die Daten einen gewissen Schwellwert erreicht haben wird das Array
     * mit insertionsort sortiert
     *
     * @param data das Array von Chars (Data)
     * @param m    der Schwellwert
     */
    public static void quickInsertionSort(final Integer[] data, final int m) {
        quickInsertionSort(data, 0, data.length - 1, m);
    }
}
