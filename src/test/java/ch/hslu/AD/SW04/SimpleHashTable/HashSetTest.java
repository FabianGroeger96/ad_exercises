package ch.hslu.AD.SW04.SimpleHashTable;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashSetTest {

    @Test
    public void testAdd(){
        HashSet<Integer> hashSet = new HashSet<Integer>();
        assertTrue(hashSet.add(1));
        assertTrue(hashSet.add(6));
        assertTrue(hashSet.add(4));
    }

    @Test
    public void testContains(){
        HashSet<Integer> hashSet = new HashSet<Integer>();
        assertFalse(hashSet.contains(1));
        hashSet.add(1);
        assertTrue(hashSet.contains(1));
    }

    @Test
    public void testRemove(){
        HashSet<Integer> hashSet = new HashSet<Integer>();
        hashSet.add(1);
        assertTrue(hashSet.contains(1));
        assertTrue(hashSet.remove(1));
        assertFalse(hashSet.contains(1));
    }

    @Test
    public void testRemoveNull(){
        HashSet<Integer> hashSet = new HashSet<Integer>();
        assertFalse(hashSet.contains(1));
        assertFalse(hashSet.remove(1));
    }

}