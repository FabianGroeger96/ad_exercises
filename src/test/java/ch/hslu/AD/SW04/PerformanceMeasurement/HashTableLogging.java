package ch.hslu.AD.SW04.PerformanceMeasurement;

import org.junit.Test;

/**
 * Übung: Hashbasierte Datenstrukturen, Performance, Thirdparty-Datenstrukturen (D3)
 * Aufgabe: Einfache Performance-Messung und Analyse
 *
 * @author Fabian Gröger
 * @version 19.03.2018
 */
public class HashTableLogging {

    @Test
    public void testAddRemoveOneEntry() {
        HashTable table = new HashTable();
        table.put(new HashItem('a'));
        table.remove(new HashItem('a'));
    }

    @Test
    public void testAddRemoveTwoEntriesNoCollision() {
        HashTable table = new HashTable();
        table.put(new HashItem('a'));
        table.put(new HashItem('b'));
        table.remove(new HashItem('a'));
        table.remove(new HashItem('b'));
    }

    @Test
    public void testAddRemoveThreeEntriesNoCollision() {
        HashTable table = new HashTable();
        table.put(new HashItem('a'));
        table.put(new HashItem('b'));
        table.put(new HashItem('c'));
        table.remove(new HashItem('a'));
        table.remove(new HashItem('b'));
        table.remove(new HashItem('c'));
    }

    @Test
    public void testAddRemoveTwoEntriesWithCollision() {
        HashTable table = new HashTable();
        char first = 'a';
        char second = 'a' + HashTable.SIZE;
        table.put(new HashItem(first));
        table.put(new HashItem(second));
        table.remove(new HashItem(first));
        table.remove(new HashItem(second));
    }

    @Test
    public void testAddRemoveThreeEntriesWithCollision() {
        HashTable table = new HashTable();
        char first = 'a';
        char second = 'a' + HashTable.SIZE;
        char third = 'a' + HashTable.SIZE * 2;
        table.put(new HashItem(first));
        table.put(new HashItem(second));
        table.put(new HashItem(third));
        table.remove(new HashItem(first));
        table.remove(new HashItem(second));
        table.remove(new HashItem(third));
    }

    @Test
    public void testAddRemoveFourEntriesWithCollision() {
        long start = System.currentTimeMillis();
        HashTable table = new HashTable();
        char first = 'a';
        char second = 'a' + HashTable.SIZE;
        char third = 'a' + HashTable.SIZE * 2;
        char fourth = 'a' + HashTable.SIZE * 3;
        table.put(new HashItem(first));
        table.put(new HashItem(second));
        table.put(new HashItem(third));
        table.put(new HashItem(fourth));
        table.remove(new HashItem(first));
        table.remove(new HashItem(second));
        table.remove(new HashItem(third));
        table.remove(new HashItem(fourth));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}
