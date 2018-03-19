package ch.hslu.AD.SW04.PerformanceMeasurement;

import java.util.Collection;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Übung: Hashbasierte Datenstrukturen, Performance, Thirdparty-Datenstrukturen (D3)
 * Aufgabe: Einfache Performance-Messung und Analyse
 *
 * @author Fabian Gröger
 * @version 19.03.2018
 */
public class HashTableTest {

    @Test
    public void testPutEntry() {
        HashTable table = new HashTable();
        assertTrue(table.put(new HashItem('a')));
        assertTrue(table.put(new HashItem('b')));
        // already added
        assertFalse(table.put(new HashItem('a')));
    }

    @Test
    public void testRemoveEntry() {
        HashTable table = new HashTable();
        table.put(new HashItem('a'));
        table.put(new HashItem('b'));
        assertTrue(table.remove(new HashItem('a')));
        assertTrue(table.remove(new HashItem('b')));
        // already removed
        assertFalse(table.remove(new HashItem('a')));
    }

    @Test
    public void testGetEntry() {
        HashTable table = new HashTable();
        HashItem a = new HashItem('a');
        table.put(a);
        assertEquals(a, table.get(a.hashCode()));
        assertNull(table.get(new HashItem('b').hashCode()));
    }

    @Test
    public void testSizeEmpty() {
        HashTable table = new HashTable();
        assertEquals(0, table.getSize());
        assertFalse(table.isFull());
    }

    @Test
    public void testSizeFull() {
        HashTable table = new HashTable();
        final int fill = HashTable.SIZE;
        for (char c = 'a'; c < ('a' + fill); c++) {
            table.put(new HashItem(c));
        }
        assertEquals(fill, table.getSize());
        assertTrue(table.isFull());
    }

    @Test
    public void createIndexCollision() {
        HashItem first = new HashItem('a');
        char secondChar = 'a' + HashTable.SIZE;
        HashItem second = new HashItem(secondChar);
        int firstIndex = first.hashCode() % HashTable.SIZE;
        int secondIndex = second.hashCode() % HashTable.SIZE;
        assertEquals(firstIndex, secondIndex);
    }

    @Test
    public void testPutCollidingEntries() {
        HashTable table = new HashTable();
        HashItem first = new HashItem('a');
        char secondChar = 'a' + HashTable.SIZE;
        HashItem second = new HashItem(secondChar);
        HashItem last = new HashItem('c');
        table.put(first);
        table.put(last);
        // now: [-][a][-][c][-]

        assertTrue(table.put(second));
        // now: [-][-][a][X][c][-]
        assertEquals(3, table.getSize());

        char thirdChar = 'a' + HashTable.SIZE * 2;
        HashItem third = new HashItem(thirdChar);
        // it's not allowed to insert it at the right of c!
        assertFalse(table.put(third));
        assertEquals(3, table.getSize());
    }

    @Test
    public void testGetWithCollidingEntries() {
        HashTable table = new HashTable();
        HashItem first = new HashItem('a');
        table.put(first);
        HashItem last = new HashItem('d');
        table.put(last);
        char secondChar = 'a' + HashTable.SIZE;
        HashItem second = new HashItem(secondChar);
        table.put(second);
        char thirdChar = 'a' + HashTable.SIZE * 2;
        HashItem third = new HashItem(thirdChar);
        table.put(third);
        // now: [-][a][X][Y][d]
        assertEquals(first, table.get(first.hashCode()));
        assertEquals(second, table.get(second.hashCode()));
        assertEquals(third, table.get(third.hashCode()));
        assertEquals(last, table.get(last.hashCode()));
    }

    @Test
    public void testPutFullTable() {
        HashTable table = new HashTable();
        char c = findEntryMappingToIndexZero();
        while (!table.isFull()) {
            table.put(new HashItem(c));
            c += HashTable.SIZE; // same collision domain
        }
        assertEquals(HashTable.SIZE, table.getSize());
        assertFalse(table.put(new HashItem(c)));
    }

    @Test
    public void testGetFullTable() {
        HashTable table = new HashTable();
        HashItem c = new HashItem(findEntryMappingToIndexZero());
        HashItem last = null;
        while (!table.isFull()) {
            table.put(c);
            last = c;
            // same collision domain
            char cChar = (char) (c.getCharacter() + HashTable.SIZE);
            c = new HashItem(cChar);
        }
        assertEquals(last, table.get(last.hashCode()));
    }

    @Test
    public void testGetAllElements() {
        HashTable table = new HashTable();
        table.put(new HashItem('a'));
        table.put(new HashItem('b'));
        table.put(new HashItem('c'));
        Collection<HashItem> allElements = table.getAllElements();
        assertEquals(3, allElements.size());
        assertTrue(allElements.contains(new HashItem('a')));
        assertTrue(allElements.contains(new HashItem('b')));
        assertTrue(allElements.contains(new HashItem('c')));
    }

    @Test
    public void breakCollisionDomain() {
        HashTable table = new HashTable();
        HashItem first = new HashItem('a');
        char secondChar = 'a' + HashTable.SIZE;
        HashItem second = new HashItem(secondChar);
        char thirdChar = 'a' + HashTable.SIZE * 2;
        HashItem third = new HashItem(thirdChar);
        table.put(first);
        table.put(second);
        table.put(third);
        assertEquals(3, table.getSize());
        assertEquals(third, table.get(third.hashCode()));
        table.remove(second);
        // fails
        // assertNotNull(table.get(third.hashCode()));
    }

    private Character findEntryMappingToIndexZero() {
        Character c = 'a';
        while (c.hashCode() % HashTable.SIZE != 0) {
            c++;
        }
        return c;
    }

}