package ch.hslu.AD.SW04.CollisionHashTable;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashSetTest {

    @Test
    public void testAdd() {
        HashSet<HashItem> hashSet = new HashSet<HashItem>();
        assertTrue(hashSet.add(new HashItem(1)));
        assertTrue(hashSet.add(new HashItem(2)));
        assertTrue(hashSet.add(new HashItem(3)));

        assertEquals(3, hashSet.size());
    }

    @Test
    public void testAddDuplicate() {
        HashSet<HashItem> hashSet = new HashSet<HashItem>();
        hashSet.add(new HashItem(1));
        hashSet.add(new HashItem(1));
        assertTrue(hashSet.contains(new HashItem(1)));
        assertEquals(1, hashSet.size());
    }

    @Test
    public void testContainsCollisions() {
        HashSet<HashItem> hashSet = new HashSet<HashItem>();
        hashSet.add(new HashItem(10, 10));
        hashSet.add(new HashItem(11, 10));
        hashSet.add(new HashItem(12, 10));

        assertTrue(hashSet.contains(new HashItem(10, 10)));
        assertTrue(hashSet.contains(new HashItem(11, 10)));
        assertTrue(hashSet.contains(new HashItem(12, 10)));
    }

    @Test
    public void testContains() {
        HashSet<HashItem> hashSet = new HashSet<HashItem>();
        assertFalse(hashSet.contains(new HashItem(1)));
        hashSet.add(new HashItem(1));
        assertTrue(hashSet.contains(new HashItem(1)));
    }

    @Test
    public void testRemove() {
        HashSet<HashItem> hashSet = new HashSet<HashItem>();
        hashSet.add(new HashItem(1));
        assertTrue(hashSet.contains(new HashItem(1)));
        assertTrue(hashSet.remove(new HashItem(1)));
        assertFalse(hashSet.contains(new HashItem(1)));
    }

    @Test
    public void testRemoveNull() {
        HashSet<HashItem> hashSet = new HashSet<HashItem>();
        assertFalse(hashSet.contains(new HashItem(1)));
        assertFalse(hashSet.remove(new HashItem(1)));
    }
}