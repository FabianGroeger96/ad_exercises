package ch.hslu.AD.SW04.BucketHashTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Übung: Hashbasierte Datenstrukturen, Performance, Thirdparty-Datenstrukturen (D3)
 * Aufgabe: Hashtabelle mit Buckets (Listen für Kollisionen)
 *
 * @author Fabian Gröger
 * @version 19.03.2018
 */
public class HashTable {

    public static final int DEFAULT_ARRAY_SIZE = 10; // the default size of hash table

    private SingleLinkedList items[]; // the array of the hash table

    private int size = 0; // the actual used size of the hash table

    /**
     * Default constructor
     */
    public HashTable() {
        items = new SingleLinkedList[DEFAULT_ARRAY_SIZE];
    }

    /**
     * Adds an object to the hash table
     *
     * @param entry the object to add
     * @return if the object could be added
     */
    public boolean add(final Object entry) {
        int index = calculateIndex(entry);
        if (items[index] == null) { // if there is nothing at index
            items[index] = new SingleLinkedList(); // create new empty list at index
        } else if (items[index].contains(entry)) { // if given object already exists
            return false;
        }

        items[index].add(entry);
        size++;
        return true;
    }

    /**
     * Removes an object from the hash table
     *
     * @param entry the object to remove
     * @return if the object could be removed
     */
    public boolean remove(final Object entry) {
        int index = calculateIndex(entry);
        if (items[index] == null || !items[index].contains(entry)) { // item doesn't exist
            return false;
        } else {
            size--;
            return items[index].remove(entry);
        }
    }

    /**
     * Returns the object with the given hashcode
     *
     * @param hashCode the hashcode from the object to search
     * @return the object that was found, or null if nothing is found
     */
    public Object get(final int hashCode) {
        int index = calculateIndex(hashCode);
        if (items[index] == null) { // when nothing is at index, return null
            return null;
        }

        Iterator<Object> iterator = items[index].iterator();
        while (iterator.hasNext()) {
            Object entry = iterator.next();
            if (entry.hashCode() == hashCode) { // if hash codes and the given one are the same, return entry
                return entry;
            }
        }
        return null; // if the hashcode wasn't found
    }

    /**
     * Returns all elements from the hash table
     *
     * @return a collection of all elements
     */
    public Collection<Object> getAllElements() {
        Collection<Object> allElements = new ArrayList<>();
        for (SingleLinkedList item : items) {
            if (item != null) { // if there is a list at the current index
                Iterator<Object> listEntries = item.iterator(); // iterator of the list at the index
                while (listEntries.hasNext()) {
                    allElements.add(listEntries.next()); // adding the element to the collection
                }
            }
        }
        return allElements;
    }

    /**
     * Returns the size of the hash table
     *
     * @return the size of the hash table
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the calculated index from the given object
     * Index is calculated from the hashcode and the array size
     *
     * @param entry the object to calculate the index
     * @return the index of the given object
     */
    private int calculateIndex(final Object entry) {
        return entry.hashCode() % DEFAULT_ARRAY_SIZE;
    }

    /**
     * Returns the calculated index from the given hashcode
     * Index is calculated from the hashcode and the array size
     *
     * @param hashCode the hashcode to calculate the index
     * @return the index of the given hashcode
     */
    private int calculateIndex(final int hashCode) {
        return hashCode % DEFAULT_ARRAY_SIZE;
    }
}
