package ch.hslu.AD.SW04.SimpleHashTable;

/**
 * Übung: Hashbasierte Datenstrukturen, Performance, Thirdparty-Datenstrukturen (D3)
 * Aufgabe: Einfache Hashtabelle
 *
 * @author Fabian Gröger
 * @version 14.03.2018
 */
public class HashSet<T> implements HashTableInterface<T> {

    private static int DEFAULT_ARRAY_SIZE = 10;

    private T[] items;

    public HashSet(){
         final T[] items = (T[])  new Object[DEFAULT_ARRAY_SIZE];
         this.items = items;
    }

    @Override
    public boolean add(T item) {
        items[getIndex(item)] =  item;
        return true;
    }

    @Override
    public boolean remove(T item) {
        items[getIndex(item)] = null;
        return false;
    }

    @Override
    public boolean contains(T item) {
        return items[getIndex(item)] != null ;
    }

    @Override
    public int getIndex(T item) {
        return item.hashCode() % DEFAULT_ARRAY_SIZE ;
    }
}
