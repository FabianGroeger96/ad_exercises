package ch.hslu.AD.SW13;

import ch.hslu.AD.SW13.KMPAlgorithm.KMPAlgorithm;
import ch.hslu.AD.SW13.Quicksearch.QuickSearch;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Übung: Suchalgorithmen (A4)
 * Aufgabe: Quicksearch und Optimal-Mismatch
 *
 * @author Fabian Gröger
 * @version 23.05.2018
 */
public class SearchBenchmark {

    private String haystack;
    private String needle;
    private int expected;

    @Before
    public void initTest() {
        haystack = SearchTestUtils.createRandomString(100_000_000, 'a', 'z');
        needle = "abcde";
        expected = haystack.indexOf(needle);
        System.out.printf("needle at: %d\n", expected);
    }

    @Test
    public void benchmarkSearch() {
        long kmpStart = System.currentTimeMillis();
        int actual = KMPAlgorithm.kmpSearch(haystack, needle);
        long kmpEnd = System.currentTimeMillis();
        assertEquals(expected, actual);

        long qsStart = System.currentTimeMillis();
        actual = QuickSearch.quickSearch(haystack, needle);
        long qsEnd = System.currentTimeMillis();
        assertEquals(expected, actual);

        System.out.printf("KMP: %6d ms\n", kmpEnd - kmpStart);
        System.out.printf("QS:  %6d ms\n", qsEnd - qsStart);

    }
}
