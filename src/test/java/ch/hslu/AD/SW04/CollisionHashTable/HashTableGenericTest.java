package ch.hslu.AD.SW04.CollisionHashTable;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashTableGenericTest {

    @Test
    public void testAdd() {
        HashTableGeneric<HashItem> hashSet = new HashTableGeneric<HashItem>();
        assertTrue(hashSet.add(new HashItem(1)));
        assertTrue(hashSet.add(new HashItem(2)));
        assertTrue(hashSet.add(new HashItem(3)));

        assertEquals(3, hashSet.size());
    }

    @Test
    public void testAddFull() {
        HashTableGeneric<HashItem> hashSet = new HashTableGeneric<HashItem>();
        assertTrue(hashSet.add(new HashItem(1)));
        assertTrue(hashSet.add(new HashItem(2)));
        assertTrue(hashSet.add(new HashItem(3)));
        assertTrue(hashSet.add(new HashItem(4)));
        assertTrue(hashSet.add(new HashItem(5)));
        assertTrue(hashSet.add(new HashItem(6)));
        assertTrue(hashSet.add(new HashItem(7)));
        assertTrue(hashSet.add(new HashItem(8)));
        assertTrue(hashSet.add(new HashItem(9)));
        assertTrue(hashSet.add(new HashItem(10)));

        assertFalse(hashSet.add(new HashItem(10)));

        assertEquals(10, hashSet.size());
    }

    @Test
    public void testAddDuplicate() {
        HashTableGeneric<HashItem> hashSet = new HashTableGeneric<HashItem>();
        assertTrue(hashSet.add(new HashItem(1)));
        assertFalse(hashSet.add(new HashItem(1)));
        assertTrue(hashSet.contains(new HashItem(1)));
        assertEquals(1, hashSet.size());
    }

    @Test
    public void testContainsCollisions() {
        HashTableGeneric<HashItem> hashSet = new HashTableGeneric<HashItem>();
        hashSet.add(new HashItem(10, 10));
        hashSet.add(new HashItem(11, 10));
        hashSet.add(new HashItem(12, 10));

        assertTrue(hashSet.contains(new HashItem(10, 10)));
        assertTrue(hashSet.contains(new HashItem(11, 10)));
        assertTrue(hashSet.contains(new HashItem(12, 10)));
    }

    @Test
    public void testContains() {
        HashTableGeneric<HashItem> hashSet = new HashTableGeneric<HashItem>();
        assertFalse(hashSet.contains(new HashItem(1)));
        hashSet.add(new HashItem(1));
        assertTrue(hashSet.contains(new HashItem(1)));
    }

    @Test
    public void testRemove() {
        HashTableGeneric<HashItem> hashSet = new HashTableGeneric<HashItem>();
        hashSet.add(new HashItem(1));
        assertTrue(hashSet.contains(new HashItem(1)));
        assertTrue(hashSet.remove(new HashItem(1)));
        assertFalse(hashSet.contains(new HashItem(1)));
    }

    @Test
    public void testRemoveEmpty() {
        HashTableGeneric<HashItem> hashSet = new HashTableGeneric<HashItem>();

        assertFalse(hashSet.remove(new HashItem(1)));
        assertEquals(0, hashSet.size());
    }

    @Test
    public void testRemoveCollisions() {
        HashTableGeneric<HashItem> hashSet = new HashTableGeneric<HashItem>();
        hashSet.add(new HashItem(10, 10));
        hashSet.add(new HashItem(11, 10));
        hashSet.add(new HashItem(12, 10));

        assertEquals(3, hashSet.size());
        assertTrue(hashSet.remove(new HashItem(12, 10)));
        assertEquals(2, hashSet.size());
    }

    @Test
    public void testRemoveNull() {
        HashTableGeneric<HashItem> hashSet = new HashTableGeneric<HashItem>();
        assertFalse(hashSet.contains(new HashItem(1)));
        assertFalse(hashSet.remove(new HashItem(1)));
    }
}