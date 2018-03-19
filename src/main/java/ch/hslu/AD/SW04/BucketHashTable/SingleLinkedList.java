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

    public void add(Object value) {
        if (start == null) {
            start = new Node(value);
        } else {
            Node node = new Node(value);
            node.setNext(start);
            start = node;
        }
        size++;
    }

    public boolean contains(Object value) {
        if (start == null) {
            return false;
        }
        Node tmp = start;
        do {
            if (tmp.getValue().equals(value)) {
                return true;
            }
            tmp = tmp.getNext();
        } while (tmp != null);
        return false;
    }

    public int getSize() {
        return size;
    }

    public boolean remove(Object value) {
        Node tmp = start;
        Node last = null;
        do {
            if (tmp.getValue().equals(value)) {
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

    @Override
    public Iterator<Object> iterator() {
        return new SingleLinkedListIterator(start);
    }
}
