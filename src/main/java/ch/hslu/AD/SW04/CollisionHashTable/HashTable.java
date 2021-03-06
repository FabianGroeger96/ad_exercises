package ch.hslu.AD.SW04.CollisionHashTable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Übung: Hashbasierte Datenstrukturen, Performance, Thirdparty-Datenstrukturen (D3)
 * Aufgabe: Hashtabelle mit Kollisionen
 *
 * @author Fabian Gröger
 * @version 14.03.2018
 */
public class HashTable<T> implements HashTableInterface<T> {

    private static int DEFAULT_ARRAY_SIZE = 10;
    private static final Logger LOGGER = LogManager.getLogger(HashTable.class);

    private T[] items;

    public HashTable() {
        this.items = (T[]) new Object[DEFAULT_ARRAY_SIZE];
    }

    @Override
    public boolean add(T item) {
        // Check if Set is full
        if (isFull()) {
            return false;
        } else {
            int index = getIndex(item); // calculate index in the hash table
            do {
                if (items[index] != null) { // tombstone not implemented
                    if (items[index].equals(item)) {
                        return false;
                    } else {
                        index = (index + 1) % DEFAULT_ARRAY_SIZE;
                    }
                } else {
                    items[index] = item;
                    return true;
                }
            } while (index != getIndex(item));

            return true;
        }
    }

    @Override
    public boolean remove(T item) {
        // Check if Set is empty
        if (size() == 0) {
            return false;
        } else {
            int searchIndex = search(item);
            if (searchIndex >= 0) {
                T nextItem = items[searchIndex + 1];
                if (nextItem != null && nextItem.hashCode() == items[searchIndex].hashCode()) {
                    items[searchIndex] = (T) new HashItem(-1, -1); // tombstone
                } else {
                    items[searchIndex] = null;
                }
                return true;
            } else {
                return false;
            }
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
                    return false;
                }
            } while (index != getIndex(item));
        }
        return items[getIndex(item)] != null;
    }

    @Override
    public int search(T item) {
        T itemArray = items[getIndex(item)];
        if (item.equals(itemArray)) {
            return getIndex(item);
        } else {
            int index = (getIndex(item) + 1) % DEFAULT_ARRAY_SIZE;
            do {
                if (items[index] != null) {
                    if (items[index].equals(item)) {
                        return index;
                    } else {
                        index = (index + 1) % DEFAULT_ARRAY_SIZE;
                    }
                } else {
                    break;
                }
            } while (index != getIndex(item));
        }
        return -1; // item wasn't found
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
    public boolean isFull() {
        return size() == DEFAULT_ARRAY_SIZE;
    }

    @Override
    public void print() {
        for (int i = 0; (i < items.length); i++) {
            if (items[i] != null) {
                LOGGER.info(i + ": " + items[i].toString());
            } else {
                LOGGER.info(i + ": ");
            }
        }
    }

    @Override
    public String toString() {
        return "HashTable[Size:" + DEFAULT_ARRAY_SIZE + "; Actual size:" + size() + "]";
    }
}
