package ch.hslu.AD.SW04.CollisionHashTable;

/**
 * Übung: Hashbasierte Datenstrukturen, Performance, Thirdparty-Datenstrukturen (D3)
 * Aufgabe: Hashtabelle mit Kollisionen
 *
 * @author Fabian Gröger
 * @version 14.03.2018
 */
public class HashSet<T> implements HashTableInterface<T> {

    private static int DEFAULT_ARRAY_SIZE = 10;

    private T[] items;

    public HashSet() {
        final T[] items = (T[]) new Object[DEFAULT_ARRAY_SIZE];
        this.items = items;
    }

    @Override
    public boolean add(T item) {
        int index = getIndex(item);
        do {
            if (items[index] != null) {
                index = (index + 1) % DEFAULT_ARRAY_SIZE;
            } else {
                items[index] = item;
                break;
            }
        } while (index != getIndex(item));

        return true;
    }

    @Override
    public boolean remove(T item) {
        if (contains(item)) {
            items[getIndex(item)] = null;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean contains(T item) {
        T itemArray = items[getIndex(item)];
        if (item.equals(itemArray)) {
            return true;
        } else {
            int index = (getIndex(item) + 1) % DEFAULT_ARRAY_SIZE;
            do {
                if (items[index] != null) {
                    if (items[index].equals(item)) {
                        return true;
                    } else {
                        index = (index + 1) % DEFAULT_ARRAY_SIZE;
                    }
                } else {
                    break;
                }
            } while (index != getIndex(item));
        }
        return items[getIndex(item)] != null;
    }

    @Override
    public int getIndex(T item) {
        return item.hashCode() % DEFAULT_ARRAY_SIZE;
    }

    @Override
    public int size() {
        int count = 0;
        for (T item : items) {
            if (item != null) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return items.toString();
    }
}
