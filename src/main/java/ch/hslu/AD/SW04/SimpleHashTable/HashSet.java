package ch.hslu.AD.SW04.SimpleHashTable;

/**
 * Übung: Hashbasierte Datenstrukturen, Performance, Thirdparty-Datenstrukturen (D3)
 * Aufgabe: Einfache Hashtabelle
 *
 * @author Fabian Gröger
 * @version 14.03.2018
 */
public class HashSet implements HashTableInterface {

    private static int DEFAULT_ARRAY_SIZE = 10;

    private HashItem[] items;

    public HashSet() {
        this.items = new HashItem[DEFAULT_ARRAY_SIZE];
    }

    @Override
    public boolean add(HashItem item) {
        items[getIndex(item)] = item;
        return true;
    }

    @Override
    public boolean remove(HashItem item) {
        if (search(item)) {
            items[getIndex(item)] = null;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean search(HashItem item) {
        return items[getIndex(item)] != null;
    }

    @Override
    public int getIndex(HashItem item) {
        return item.hashCode() % DEFAULT_ARRAY_SIZE;
    }

    @Override
    public String toString() {
        return items.toString();
    }
}
