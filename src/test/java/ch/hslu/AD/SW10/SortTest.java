package ch.hslu.AD.SW10;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Übung: Höhere Sortieralgorithmen (A2)
 *
 * @author Fabian Gröger
 * @version 26.04.2018
 */
public class SortTest {

    private static final int ARRAY_SIZE = 50_000;
    private static final Logger LOG = LogManager.getLogger(SortTest.class);

    private Character[] charArray;

    @Before
    public void setup() {
        charArray = SortUtils.randomChars(ARRAY_SIZE);
    }

    @Test
    public void testQuickSort() {
        long startTime = System.currentTimeMillis();
        Sort.quickSort(charArray, 0, charArray.length - 1);
        long endTime = System.currentTimeMillis();

        LOG.info("Sorted " + ARRAY_SIZE + " random elements in " + (endTime - startTime) + "ms");

        boolean sorted = SortUtils.assertIsSorted(charArray, SortUtils.Order.ASCENDING);
        assertTrue(sorted);
    }

    @Test
    public void testQuickSort2() {
        long startTime = System.currentTimeMillis();
        Sort.quickSort(charArray);
        long endTime = System.currentTimeMillis();

        LOG.info("Sorted " + ARRAY_SIZE + " random elements in " + (endTime - startTime) + "ms");

        boolean sorted = SortUtils.assertIsSorted(charArray, SortUtils.Order.ASCENDING);
        assertTrue(sorted);
    }
}