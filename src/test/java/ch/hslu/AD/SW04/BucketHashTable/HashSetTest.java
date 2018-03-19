package ch.hslu.AD.SW04.BucketHashTable;

import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Übung: Hashbasierte Datenstrukturen, Performance, Thirdparty-Datenstrukturen (D3)
 * Aufgabe: Hashtabelle mit Buckets (Listen für Kollisionen)
 *
 * @author Fabian Gröger
 * @version 19.03.2018
 */
public class HashSetTest {

    @Test
    public void testPutEntry() {
        HashTable table = new HashTable();
        assertTrue(table.add("a"));
        assertTrue(table.add("b"));
        assertFalse(table.add("a")); // duplicate
    }

    @Test
    public void testRemoveEntry() {
        HashTable table = new HashTable();
        table.add("a");
        table.add("b");

        assertEquals(2, table.getSize());

        assertTrue(table.remove("a"));
        assertEquals(1, table.getSize());

        assertFalse(table.remove("a")); // non existing object
        assertEquals(1, table.getSize());
    }

    @Test
    public void testGetEntry() {
        HashTable table = new HashTable();
        String input = "a";
        table.add(input);

        assertEquals(input, table.get(input.hashCode()));
        assertNull(table.get("b".hashCode()));
    }

    @Test
    public void testSizeEmpty() {
        HashTable table = new HashTable();
        assertEquals(0, table.getSize());
    }

    @Test
    public void testSizeFull() {
        HashTable table = new HashTable();
        final int fill = HashTable.DEFAULT_ARRAY_SIZE;
        for (char c = 'a'; c < ('a' + fill); c++) {
            table.add(String.valueOf(c));
        }

        assertEquals(fill, table.getSize());
    }

    @Test
    public void createIndexCollision() {
        Character first = 'a';
        Character second = 'a' + HashTable.DEFAULT_ARRAY_SIZE;
        int firstIndex = first.hashCode() % HashTable.DEFAULT_ARRAY_SIZE;
        int secondIndex = second.hashCode() % HashTable.DEFAULT_ARRAY_SIZE;

        assertEquals(firstIndex, secondIndex);
    }

    @Test
    public void testPutCollidingEntries() {
        HashTable table = new HashTable();
        Character first = 'a';
        Character second = 'a' + HashTable.DEFAULT_ARRAY_SIZE;
        Character last = 'c';
        table.add(first);
        table.add(last);

        assertTrue(table.add(second));
        assertEquals(3, table.getSize());

        Character third = 'a' + HashTable.DEFAULT_ARRAY_SIZE * 2;
        assertTrue(table.add(third));
        assertEquals(4, table.getSize());
    }

    @Test
    public void testGetWithCollidingEntries() {
        HashTable table = new HashTable();
        Character first = 'a';
        table.add(first);
        Character last = 'd';
        table.add(last);
        Character second = 'a' + HashTable.DEFAULT_ARRAY_SIZE;
        table.add(second);
        Character third = 'a' + HashTable.DEFAULT_ARRAY_SIZE * 2;
        table.add(third);

        assertEquals(first, table.get(first.hashCode()));
        assertEquals(second, table.get(second.hashCode()));
        assertEquals(third, table.get(third.hashCode()));
        assertEquals(last, table.get(last.hashCode()));
    }

    @Test
    public void testGetAllElements() {
        HashTable table = new HashTable();
        table.add('a');
        table.add('b');
        table.add('c');
        Collection<Object> allElements = table.getAllElements();

        assertEquals(3, allElements.size());
        assertTrue(allElements.contains('a'));
        assertTrue(allElements.contains('b'));
        assertTrue(allElements.contains('c'));
    }

    @Test
    public void breakCollisionDomain() {
        HashTable table = new HashTable();
        Character first = 'a';
        Character second = 'a' + HashTable.DEFAULT_ARRAY_SIZE;
        Character third = 'a' + HashTable.DEFAULT_ARRAY_SIZE * 2;
        table.add(first);
        table.add(second);
        table.add(third);

        assertEquals(3, table.getSize());
        assertEquals(third, table.get(third.hashCode()));

        table.remove(second);
        assertNotNull(table.get(third.hashCode()));
    }

}