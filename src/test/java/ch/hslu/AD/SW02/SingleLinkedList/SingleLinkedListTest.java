package ch.hslu.AD.SW02.SingleLinkedList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Übung: Arrays, Listen, Stack und Queue (D1)
 * Aufgabe: Implementation einer einfach verketteten Liste
 *
 * @author Fabian Gröger
 * @version 07.03.2018
 */
public class SingleLinkedListTest {

    @Test
    public void testAddingElements() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        assertEquals(0, list.size());
        list.add(1);
        list.add(2);
        assertEquals(2, list.size());
        list.add(3);
        assertEquals(3, list.size());
    }

    @Test
    public void testAddingAllAtIndex() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        SingleLinkedList<Integer> rootList = new SingleLinkedList<>();
        assertEquals(0, rootList.size());
        rootList.add(11);
        rootList.add(22);
        rootList.add(33);
        assertEquals(3, rootList.size());

        rootList.addAll(0, list);
        assertEquals(6, rootList.size());
    }

    @Test
    public void testSimpleIterating() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.add(1);
        list.add(2);

        Iterator<Integer> it = list.iterator();

        assertEquals(true, it.hasNext());
        assertEquals(new Integer(2), it.next());
        assertEquals(true, it.hasNext());
        assertEquals(new Integer(1), it.next());
        assertEquals(false, it.hasNext());
    }

    @Test
    public void testAddingAtIndex() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> it = list.iterator();
        assertEquals(new Integer(3), it.next());
        assertEquals(new Integer(2), it.next());
        assertEquals(new Integer(1), it.next());
        list.add(1, 4);
        it = list.iterator();
        assertEquals(new Integer(3), it.next());
        assertEquals(new Integer(4), it.next());
        assertEquals(new Integer(2), it.next());
        assertEquals(new Integer(1), it.next());
        list.add(2, 5);
        it = list.iterator();
        assertEquals(new Integer(3), it.next());
        assertEquals(new Integer(4), it.next());
        assertEquals(new Integer(5), it.next());
        assertEquals(new Integer(2), it.next());
        assertEquals(new Integer(1), it.next());
    }

    @Test
    public void testAddingCollection() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        list.addAll(arrayList);
        assertEquals(3, list.size());
        Iterator<Integer> it = list.iterator();
        assertEquals(new Integer(3), it.next());
        assertEquals(new Integer(2), it.next());
        assertEquals(new Integer(1), it.next());
        assertEquals(false, it.hasNext());

        list.addAll(arrayList);

        assertEquals(6, list.size());
        it = list.iterator();
        assertEquals(new Integer(3), it.next());
        assertEquals(new Integer(2), it.next());
        assertEquals(new Integer(1), it.next());
        assertEquals(new Integer(3), it.next());
        assertEquals(new Integer(2), it.next());
        assertEquals(new Integer(1), it.next());
        assertEquals(false, it.hasNext());
    }

    @Test
    public void testClear() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.add(1);
        list.add(2);
        assertEquals(2, list.size());
        assertEquals(false, list.isEmpty());
        list.clear();
        assertEquals(0, list.size());
        assertEquals(true, list.isEmpty());
    }

    @Test
    public void testGetElementAtZero() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.size());

        assertEquals(new Integer(3), list.get());
        assertEquals(2, list.size());
    }

    @Test
    public void testGetElementAtIndex() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        assertEquals(new Integer(4), list.get(0));
        assertEquals(new Integer(3), list.get(1));
        assertEquals(new Integer(2), list.get(2));
        assertEquals(new Integer(1), list.get(3));
    }

    @Test
    public void testRemoveElement() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        list.remove(new Integer(2));
        assertEquals(3, list.size());
        Iterator<Integer> it = list.iterator();
        assertEquals(new Integer(4), it.next());
        assertEquals(new Integer(3), it.next());
        assertEquals(new Integer(1), it.next());

        list.remove(new Integer(1));
        assertEquals(2, list.size());
        it = list.iterator();
        assertEquals(new Integer(4), it.next());
        assertEquals(new Integer(3), it.next());
    }

    @Test
    public void testRemoveNonExistingElement() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        assertFalse(list.remove(new Integer(42)));
        assertEquals(4, list.size());
    }

    @Test
    public void testRemoveElementAtIndex() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);

        assertEquals(new Integer(2), list.remove(1));
        assertEquals(5, list.size());
        Iterator<Integer> it = list.iterator();
        assertEquals(new Integer(1), it.next());
        assertEquals(new Integer(3), it.next());
        assertEquals(new Integer(4), it.next());
        assertEquals(new Integer(5), it.next());
        assertEquals(new Integer(6), it.next());

        assertEquals(new Integer(1), list.remove(0));
        assertEquals(4, list.size());
        it = list.iterator();
        assertEquals(new Integer(3), it.next());
        assertEquals(new Integer(4), it.next());
        assertEquals(new Integer(5), it.next());
        assertEquals(new Integer(6), it.next());

        assertEquals(new Integer(6), list.remove(list.size() - 1));
        assertEquals(3, list.size());
        it = list.iterator();
        assertEquals(new Integer(3), it.next());
        assertEquals(new Integer(4), it.next());
        assertEquals(new Integer(5), it.next());
    }

    @Test
    public void testIndexOf() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        assertEquals(3, list.indexOf(new Integer(1)));
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testIndexOfException() throws NoSuchElementException {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        exception.expect(NoSuchElementException.class);
        list.indexOf(new Integer(10));
    }

    @Test
    public void testContains() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(list.contains(new Integer(1)));
        assertFalse(list.contains(new Integer(5)));
    }

    @Test
    public void testContainsAll() {
        SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<>();
        singleLinkedList.add(1);
        singleLinkedList.add(2);
        singleLinkedList.add(3);
        singleLinkedList.add(4);

        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.add(1);
        list.add(2);

        assertTrue(singleLinkedList.containsAll(list));

        list.add(5);

        assertFalse(singleLinkedList.containsAll(list));
    }

    @Test
    public void testLastIndexOf() {
        SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<>();
        singleLinkedList.add(1);
        singleLinkedList.add(2);
        singleLinkedList.add(1);
        singleLinkedList.add(2);

        assertEquals(2, singleLinkedList.lastIndexOf(new Integer(2)));
        assertEquals(3, singleLinkedList.lastIndexOf(new Integer(1)));
    }

    @Test
    public void testLastIndexOfException() throws NoSuchElementException {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(1);
        list.add(2);

        exception.expect(NoSuchElementException.class);
        list.lastIndexOf(new Integer(10));
    }
}