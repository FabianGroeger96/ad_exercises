package ch.hslu.AD.SW04.CollisionHashTable;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashTableTest {

    /**
     * Add - Test cases
     */
    @Test
    public void testAdd() {
        HashTable<HashItem> hashTable = new HashTable<>();
        assertTrue(hashTable.add(new HashItem(1)));
        assertTrue(hashTable.add(new HashItem(2)));
        assertTrue(hashTable.add(new HashItem(3)));

        assertEquals(3, hashTable.size());
    }

    @Test
    public void testAddFull() {
        HashTable<HashItem> hashTable = new HashTable<>();
        assertTrue(hashTable.add(new HashItem(1)));
        assertTrue(hashTable.add(new HashItem(2)));
        assertTrue(hashTable.add(new HashItem(3)));
        assertTrue(hashTable.add(new HashItem(4)));
        assertTrue(hashTable.add(new HashItem(5)));
        assertTrue(hashTable.add(new HashItem(6)));
        assertTrue(hashTable.add(new HashItem(7)));
        assertTrue(hashTable.add(new HashItem(8)));
        assertTrue(hashTable.add(new HashItem(9)));
        assertTrue(hashTable.add(new HashItem(10)));

        assertFalse(hashTable.add(new HashItem(10)));

        assertEquals(10, hashTable.size());
    }

    @Test
    public void testAddDuplicate() {
        HashTable<HashItem> hashTable = new HashTable<>();
        assertTrue(hashTable.add(new HashItem(1)));
        assertFalse(hashTable.add(new HashItem(1)));

        assertTrue(hashTable.contains(new HashItem(1)));
        assertEquals(1, hashTable.size());
    }

    @Test
    public void testAddCollisions() {
        HashTable<HashItem> hashTable = new HashTable<>();
        assertTrue(hashTable.add(new HashItem(20, 10)));
        assertTrue(hashTable.add(new HashItem(21, 10)));
        assertTrue(hashTable.add(new HashItem(22, 10)));
    }

    /**
     * Contains - Test cases
     */
    @Test
    public void testContainsCollisions() {
        HashTable<HashItem> hashTable = new HashTable<>();
        hashTable.add(new HashItem(10, 10));
        hashTable.add(new HashItem(11, 10));
        hashTable.add(new HashItem(12, 10));

        assertTrue(hashTable.contains(new HashItem(10, 10)));
        assertTrue(hashTable.contains(new HashItem(11, 10)));
        assertTrue(hashTable.contains(new HashItem(12, 10)));
    }

    @Test
    public void testContains() {
        HashTable<HashItem> hashTable = new HashTable<>();
        assertFalse(hashTable.contains(new HashItem(1)));

        hashTable.add(new HashItem(1));
        assertTrue(hashTable.contains(new HashItem(1)));
    }

    /**
     * Remove - Test cases
     */
    @Test
    public void testRemove() {
        HashTable<HashItem> hashTable = new HashTable<>();
        hashTable.add(new HashItem(1));

        assertTrue(hashTable.contains(new HashItem(1)));
        assertTrue(hashTable.remove(new HashItem(1)));
        assertFalse(hashTable.contains(new HashItem(1)));
    }

    @Test
    public void testRemoveEmpty() {
        HashTable<HashItem> hashTable = new HashTable<>();

        assertFalse(hashTable.remove(new HashItem(1)));
        assertEquals(0, hashTable.size());
    }

    @Test
    public void testRemoveCollisions() {
        HashTable<HashItem> hashTable = new HashTable<>();
        hashTable.add(new HashItem(10, 10));
        hashTable.add(new HashItem(11, 10));
        hashTable.add(new HashItem(12, 10));

        assertEquals(3, hashTable.size());
        assertTrue(hashTable.remove(new HashItem(12, 10)));
        assertEquals(2, hashTable.size());
    }

    @Test
    public void testRemoveNull() {
        HashTable<HashItem> hashTable = new HashTable<>();
        assertFalse(hashTable.contains(new HashItem(1)));
        assertFalse(hashTable.remove(new HashItem(1)));
    }
}