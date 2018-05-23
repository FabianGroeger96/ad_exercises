package ch.hslu.AD.SW13;

import java.util.Random;

/**
 * Übung: Suchalgorithmen (A4)
 * Aufgabe: Quicksearch und Optimal-Mismatch
 *
 * @author Fabian Gröger
 * @version 23.05.2018
 */
public class SearchTestUtils {

    public static String createRandomString(int length, char from, char to) {
        if (from >= to) {
            return "";
        }
        Random random = new Random(System.currentTimeMillis());
        char[] chars = new char[length];
        for (int i = 0; i < length; i++) {
            chars[i] = (char) (from + random.nextInt(to - from + 1));
        }
        return new String(chars);
    }
}
