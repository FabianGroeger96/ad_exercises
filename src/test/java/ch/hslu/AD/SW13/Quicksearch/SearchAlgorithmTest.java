package ch.hslu.AD.SW13.Quicksearch;

import ch.hslu.AD.SW13.SearchTestUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

/**
 * Übung: Suchalgorithmen (A4)
 * Aufgabe: Quicksearch und Optimal-Mismatch
 *
 * @author Fabian Gröger
 * @version 23.05.2018
 */
public class SearchAlgorithmTest {

    private Map<Search, Integer> searches;

    @Before
    public void initSearches() {
        searches = new HashMap<>();

        // small tests
        searches.put(new Search("abcabcxyz", "xyz"), 6);
        searches.put(new Search("ananas", "ananas"), 0);
        searches.put(new Search("anananas", "ananas"), 2);
        searches.put(new Search("ananAs", "ananas"), -1);
        searches.put(new Search("dasisteinTest", "Test"), 9);

        // bigger tests
        String haystack = SearchTestUtils.createRandomString(100000, 'a', 'z');
        String pattern = "xyz";
        Integer position = haystack.indexOf(pattern);
        searches.put(new Search(haystack, pattern), position);

        haystack = SearchTestUtils.createRandomString(100, 'a', 'b');
        pattern = "abba";
        position = haystack.indexOf(pattern);
        searches.put(new Search(haystack, pattern), position);
    }

    @Test
    public void testQuickSearch() {
        for (Search search : searches.keySet()) {
            int expected = searches.get(search);
            int actual = QuickSearch.quickSearch(search.haystack, search.pattern);
            assertEquals(expected, actual);
            System.out.printf("expected: %d, actual: %d\n", expected, actual);
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
}