package ch.hslu.AD.SW10.Sorting;

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

    public static final String[] randomStrings(final int length) {
        Random random = new Random();
        String[] randomString = new String[length];
        for (int i = 0; i < length; i++) {
            String tmp = (char) (random.nextInt(26) + 'a') + "" + (char) (random.nextInt(26) + 'a') + (char) (random.nextInt(26) + 'a') + (char) (random.nextInt(26) + 'a');
            randomString[i] = tmp; // speichert und generiert einen zufälligen String-Wert
        }

        return randomString;
    }

    public static final Integer[] randomIntegers(final int length) {
        Random random = new Random();
        Integer[] randomInteger = new Integer[length];
        for (int i = 0; i < length; i++) {
            randomInteger[i] = random.nextInt(length); // speichert und generiert einen zufälligen Char-Wert
        }

        return randomInteger;
    }
}
