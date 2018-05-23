package ch.hslu.AD.SW13.Quicksearch;

/**
 * Übung: Suchalgorithmen (A4)
 * Aufgabe: Quicksearch und Optimal-Mismatch
 *
 * @author Fabian Gröger
 * @version 23.05.2018
 */
public class QuickSearch {

    /**
     * Durchsucht eine Zeichenkette mittels quickSearch.
     *
     * @param a Zeichenkette, die durchsucht wird.
     * @return Index der Fundstelle oder ‐1, falls Pattern in a nicht gefunden wurde.
     */
    public static int quickSearch(String a, String p) {
        int n = a.length();
        int m = p.length();
        int range = 256; // ASCII range
        int[] shift = new int[range];

        // init shift array
        for (int i = 0; i < range; i++) {
            shift[i] = m + 1;
        }

        // overwrite fields according to pattern
        for (int i = 0; i < m; i++) {
            shift[p.charAt(i)] = m - i;
        }

        // search
        int i = 0; // index to string
        int j = 0; // index to pattern p
        do {
            if (a.charAt(i + j) == p.charAt(j)) { // match
                j++;
            } else { // mismatch
                if (i + m < n) { // a.charAt(i1+m) is not outside a
                    i += shift[a.charAt(i + m)]; // jump forward
                    j = 0;
                } else {
                    break; // (mismatch) && (no shift is possible)
                }
            }
        } while (j < m && i + m <= n);

        // (pattern p not found) && (end of a not reached)
        if (j == m) {
            return i; // pattern found, starting at i
        } else {
            return -1; // pattern not found
        }
    }
}
