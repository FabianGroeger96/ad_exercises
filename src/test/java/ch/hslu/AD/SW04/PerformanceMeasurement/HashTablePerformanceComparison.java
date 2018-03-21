package ch.hslu.AD.SW04.PerformanceMeasurement;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

/**
 * Übung: Hashbasierte Datenstrukturen, Performance, Thirdparty-Datenstrukturen (D3)
 * Aufgabe: Einfache Performance-Messung und Analyse
 *
 * @author Fabian Gröger
 * @version 19.03.2018
 */
public class HashTablePerformanceComparison {

    private final static int TEST_SIZE = 1000;

    @Test
    public void testHashTablePerformance() {
        long start = System.currentTimeMillis();
        HashTable hashTable = new HashTable();
        for (char c = 0; c < TEST_SIZE; c++) {
            hashTable.add(new HashItem(c));
        }
        Assert.assertEquals(TEST_SIZE, hashTable.getSize());
        for (char c = 0; c < TEST_SIZE; c++) {
            hashTable.remove(new HashItem(c));
        }
        Assert.assertEquals(0, hashTable.getSize());
        long end = System.currentTimeMillis();
        System.out.println("HashTable duration for " + TEST_SIZE + " items: " + (end - start) + "ms");
    }

    @Test
    public void testHashSetPerformance() {
        long start = System.currentTimeMillis();
        HashSet<HashItem> hashSet = new HashSet<>(TEST_SIZE);
        for (char c = 0; c < TEST_SIZE; c++) {
            hashSet.add(new HashItem(c));
        }
        Assert.assertEquals(TEST_SIZE, hashSet.size());
        for (char c = 0; c < TEST_SIZE; c++) {
            hashSet.remove(new HashItem(c));
        }
        Assert.assertEquals(0, hashSet.size());
        long end = System.currentTimeMillis();
        System.out.println("HashSet duration for " + TEST_SIZE + " items: " + (end - start) + "ms");
    }
}
