package ch.hslu.AD.SW04.SimpleHashTable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Übung: Hashbasierte Datenstrukturen, Performance, Thirdparty-Datenstrukturen (D3)
 * Aufgabe: Einfache Hashtabelle
 *
 * @author Fabian Gröger
 * @version 14.03.2018
 */
public class HashSet implements HashTableInterface {

    private static int DEFAULT_ARRAY_SIZE = 10;
    private static final Logger LOGGER = LogManager.getLogger(HashSet.class);

    private HashItem[] items;

    public HashSet() {
        this.items = new HashItem[DEFAULT_ARRAY_SIZE];
    }

    @Override
    public boolean add(final HashItem item) {
        items[getIndex(item)] = item;
        return true;
    }

    @Override
    public boolean remove(final HashItem item) {
        if (search(item)) {
            items[getIndex(item)] = null;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean search(final HashItem item) {
        return items[getIndex(item)] != null;
    }

    @Override
    public int getIndex(final HashItem item) {
        return item.hashCode() % DEFAULT_ARRAY_SIZE;
    }

    @Override
    public String toString() {
        return "HashSet[Size:" + DEFAULT_ARRAY_SIZE + "; Actual size:" + size() + "]";
    }

    public void print() {
        for (int i = 0; (i < items.length); i++) {
            if (items[i] != null) {
                LOGGER.info(i + ": " + items[i].toString());
            } else {
                LOGGER.info(i + ": ");
            }
        }
    }

    public int size() {
        int count = 0;
        for (HashItem item : items) {
            if (item != null) {
                count++;
            }
        }
        return count;
    }
}
