package ch.hslu.AD.SW04.BucketHashTable;

import java.util.Iterator;

/**
 * Übung: Hashbasierte Datenstrukturen, Performance, Thirdparty-Datenstrukturen (D3)
 * Aufgabe: Hashtabelle mit Buckets (Listen für Kollisionen)
 *
 * @author Fabian Gröger
 * @version 19.03.2018
 */
public class SingleLinkedList implements Iterable<Object> {
    private Node start;

    private int size = 0;

    /**
     * Adds an object to the list
     *
     * @param object the object to add
     */
    public void add(final Object object) {
        if (start == null) {
            start = new Node(object);
        } else {
            Node node = new Node(object);
            node.setNext(start);
            start = node;
        }
        size++;
    }

    /**
     * Removes the given object from the list
     *
     * @param object the object to remove
     * @return if the object could be removed
     */
    public boolean remove(final Object object) {
        Node tmp = start;
        Node last = null;
        do {
            if (tmp.getValue().equals(object)) {
                if (last == null) {
                    start = tmp.getNext();
                } else {
                    last.setNext(tmp.getNext());
                }
                tmp.setNext(null);
                size--;
                return true;
            } else {
                last = tmp;
            }
            tmp = tmp.getNext();
        } while (tmp != null);
        return false;
    }

    /**
     * Checks if the given object exists in the list
     *
     * @param object the object to search
     * @return if the object exists
     */
    public boolean contains(final Object object) {
        if (start == null) {
            return false;
        }
        Node tmp = start;
        do {
            if (tmp.getValue().equals(object)) {
                return true;
            }
            tmp = tmp.getNext();
        } while (tmp != null);
        return false;
    }

    /**
     * Returns the used size of the list
     *
     * @return size of the list
     */
    public int getSize() {
        return size;
    }

    @Override
    public Iterator<Object> iterator() {
        return new SingleLinkedListIterator(start);
    }
}
