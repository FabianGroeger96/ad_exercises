package ch.hslu.AD.SW04.BucketHashTable;

import java.util.Iterator;

/**
 * Übung: Hashbasierte Datenstrukturen, Performance, Thirdparty-Datenstrukturen (D3)
 * Aufgabe: Hashtabelle mit Buckets (Listen für Kollisionen)
 *
 * @author Fabian Gröger
 * @version 19.03.2018
 */
public class SingleLinkedListIterator implements Iterator<Object> {

    private Node current;

    public SingleLinkedListIterator(Node start) {
        current = start;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public Object next() {
        Node node = current;
        current = node.getNext();
        return node.getValue();
    }
}
