package ch.hslu.AD.SW04.SimpleHashTable;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashSetTest {

    @Test
    public void testAdd() {
        HashSet hashSet = new HashSet();
        assertTrue(hashSet.add(new HashItem(1)));
        assertTrue(hashSet.add(new HashItem(2)));
        assertTrue(hashSet.add(new HashItem(3)));
    }

    @Test
    public void testAddDuplicate() {
        HashSet hashSet = new HashSet();
        hashSet.add(new HashItem(1));
        hashSet.add(new HashItem(1));
        assertTrue(hashSet.search(new HashItem(1)));
    }

    @Test
    public void testContains() {
        HashSet hashSet = new HashSet();
        assertFalse(hashSet.search(new HashItem(1)));
        hashSet.add(new HashItem(1));
        assertTrue(hashSet.search(new HashItem(1)));
    }

    @Test
    public void testRemove() {
        HashSet hashSet = new HashSet();
        hashSet.add(new HashItem(1));
        assertTrue(hashSet.search(new HashItem(1)));
        assertTrue(hashSet.remove(new HashItem(1)));
        assertFalse(hashSet.search(new HashItem(1)));
    }

    @Test
    public void testRemoveNull() {
        HashSet hashSet = new HashSet();
        assertFalse(hashSet.search(new HashItem(1)));
        assertFalse(hashSet.remove(new HashItem(1)));
    }

}