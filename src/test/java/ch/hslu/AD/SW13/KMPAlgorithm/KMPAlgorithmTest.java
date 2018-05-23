package ch.hslu.AD.SW13.KMPAlgorithm;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Übung: Suchalgorithmen (A4)
 * Aufgabe: Musterautomat und KMP-Algorithmus
 *
 * @author Fabian Gröger
 * @version 23.05.2018
 */
public class KMPAlgorithmTest {
    private Map<Search, Integer> searches;
    private Map<String, int[]> edges;

    @Before
    public void initSearches() {
        searches = new HashMap<>();
        searches.put(new Search("ananas", "ananas"), 0);
        searches.put(new Search("anananas", "ananas"), 2);
        searches.put(new Search("ananAs", "ananas"), -1);
        searches.put(new Search("dasisteinTest", "Test"), 9);

        edges = new HashMap<>();
        edges.put("EISBEIN", new int[]{-1, 0, 0, 0, 0, 1, 2});
        edges.put("ANANAS", new int[]{-1, 0, 0, 1, 2, 3});
    }

    @Test
    public void testKMPSearch() {
        for (Search search : searches.keySet()) {
            int expected = searches.get(search);
            int actual = KMPAlgorithm.kmpSearch(search.haystack, search.pattern);
            assertEquals(expected, actual);
        }
    }

    @Test
    public void testEdges() {
        for (String searchWord : edges.keySet()) {
            int[] expected = edges.get(searchWord);
            int[] actual = KMPAlgorithm.initNext(searchWord);
            assertArrayEquals(expected, actual);
        }
    }
}

class Search {
    protected String haystack;
    protected String pattern;

    Search(String haystack, String pattern) {
        this.haystack = haystack;
        this.pattern = pattern;
    }

    public int hashCode() {
        return Objects.hash(haystack, pattern);
    }
}