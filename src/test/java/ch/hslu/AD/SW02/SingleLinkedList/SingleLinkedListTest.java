package ch.hslu.AD.SW02.SingleLinkedList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class SingleLinkedListTest extends TestCase{

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SingleLinkedListTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(SingleLinkedListTest.class);
    }

    /**
     * Test Cases
     */
    public void testAddingElements() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        assertEquals(0, list.size());
        list.add(1);
        list.add(2);
        assertEquals(2, list.size());
        list.add(3);
        assertEquals(3, list.size());
    }

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

    public void testGetElementAtZero() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.size());

        assertEquals(new Integer(3), list.get());
        assertEquals(2, list.size());
    }

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

    public void testRemoveNonExistingElement() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        assertFalse(list.remove(new Integer(42)));
        assertEquals(4, list.size());
    }

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
}