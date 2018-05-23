package ch.hslu.AD.SW13.OptimizedSearchEngine;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Übung: Suchalgorithmen (A4)
 * Aufgabe: Optimierter Suchautomat
 *
 * @author Fabian Gröger
 * @version 23.05.2018
 */
public class optimizedSearchEngineTest {

    private Map<String, Integer> searches;

    @Before
    public void initSearches() {
        searches = new HashMap<>();
        searches.put("ANANAS", 0);
        searches.put("ANANANAS", 2);
        searches.put("ANANANANAS", 4);
        searches.put("I don't like ANANAS.", 13);
        searches.put("ANANAs", -1);
    }

    @Test
    public void testStateSearchANANAS() {
        for (String haystack : searches.keySet()) {
            int expexted = searches.get(haystack);
            int actual = OptimizedSearchEngine.stateSearch(haystack);
            assertEquals(expexted, actual);
        }
    }

}