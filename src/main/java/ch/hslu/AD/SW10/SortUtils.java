package ch.hslu.AD.SW10;

import java.util.Random;

/**
 * Übung: Höhere Sortieralgorithmen (A2)
 * <p>
 * Klasse mit allen Hilfsmethoden zum sortieren
 *
 * @author Fabian Gröger
 * @version 26.04.2018
 */
public class SortUtils {

    public enum Order {
        ASCENDING, DESCENDING
    }

    public static final <T> boolean isSorted(Comparable<T> a[], Order order) {
        for (int i = 0; i < a.length - 1; i++) {
            Comparable<T> current = a[i];
            Comparable<T> next = a[i + 1];

            int result = current.compareTo((T) next); // Vergleichen des aktuellen Buchstabens mit dem nächsten
            switch (order) {
                case ASCENDING:
                    if (result > 0) { // wenn der nächste Buchstabe kleiner als der Vorgänger ist (result positiv)
                        return false; // abbruch, denn array ist falsch sortiert
                    } else {
                        break;
                    }
                case DESCENDING:
                    if (result < 0) { // wenn der nächste Buchstabe grösser als der Vorgänger ist (result negativ)
                        return false; // abbruch, denn array ist falsch sortiert
                    } else {
                        break;
                    }
            }
        }

        return true;
    }

    public static final Character[] randomChars(final int length) {
        Random random = new Random();
        Character[] randomChar = new Character[length];
        for (int i = 0; i < length; i++) {
            randomChar[i] = (char) (random.nextInt(26) + 'a'); // speichert und generiert einen zufälligen Char-Wert
        }

        return randomChar;
    }
}
