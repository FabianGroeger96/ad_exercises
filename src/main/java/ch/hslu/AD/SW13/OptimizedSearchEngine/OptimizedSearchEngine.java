package ch.hslu.AD.SW13.OptimizedSearchEngine;

/**
 * Übung: Suchalgorithmen (A4)
 * Aufgabe: Optimierter Suchautomat
 *
 * @author Fabian Gröger
 * @version 23.05.2018
 */
public class OptimizedSearchEngine {

    /**
     * Durchsucht eine Zeichenkette mittels optimiertem Suchautomaten.
     * Sucht nach dem Pattern `ANANAS`
     *
     * @param a Zeichenkette, die durchsucht wird.
     * @return Index der Fundstelle oder ‐1, falls Pattern in a nicht gefunden wurde.
     */
    public static int stateSearch(final String a) {
        int i = 0; //index to string a
        String state = "";
        final int notFound = -1;
        char[] c = a.toCharArray();

        do {
            switch (state) {
                case "":
                    if (c[i] == 'A') {
                        state = "A";
                    } else {
                        state = "";
                    }
                    break;

                case "A":
                    if (c[i] == 'N') {
                        state = "AN";
                    } else if (c[i] == 'A') {
                        state = "A";
                    } else {
                        state = "";
                    }
                    break;

                case "AN":
                    if (c[i] == 'A') {
                        state = "ANA";
                    } else {
                        state = "";
                    }
                    break;

                case "ANA":
                    if (c[i] == 'N') {
                        state = "ANAN";
                    } else if (c[i] == 'A') {
                        state = "A";
                    } else {
                        state = "";
                    }
                    break;

                case "ANAN":
                    if (c[i] == 'A') {
                        state = "ANANA";
                    } else {
                        state = "";
                    }
                    break;

                case "ANANA":
                    if (c[i] == 'S') {
                        state = "ANANAS";
                    } else if (c[i] == 'N') {
                        state = "ANAN";
                    } else if (c[i] == 'A') {
                        state = "A";
                    }
                    break;
            }
            i++;
        } while (!state.equals("ANANAS") && i < a.length());

        if (state.equals("ANANAS")) {
            return i - "ANANAS".length();
        }

        return notFound;
    }
}
