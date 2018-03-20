package ch.hslu.AD.SW04.PerformanceMeasurement;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Übung: Hashbasierte Datenstrukturen, Performance, Thirdparty-Datenstrukturen (D3)
 * Aufgabe: Einfache Performance-Messung und Analyse
 *
 * @author Fabian Gröger
 * @version 19.03.2018
 */
public class HashTable {

    public static final int SIZE = 1000;

    private HashItem entries[];

    private int size = 0;

    /**
     * Default constructor
     */
    public HashTable() {
        entries = new HashItem[SIZE];
    }

    /**
     * Adds a hash item to the hash table
     *
     * @param entry the hash item to add
     * @return if the item could be added
     */
    public boolean add(final HashItem entry) {
        int index = calculateIndex(entry);
        int collisionDomain = index;

        // look for next empty space
        while (index < SIZE && entries[index] != null) {
            if (entries[index].equals(entry)) {
                // already contained: abort
                return false;
            }
            if (calculateIndex(entries[index]) != collisionDomain) {
                // end of chain reached: abort
                return false;
            }
            index++;
        }

        if (index == SIZE) {
            return false;
        }

        entries[index] = entry;
        size++;
        return true;
    }

    /**
     * Removes a hash item from the hash table
     *
     * @param entry the hash item to remove
     * @return if the item could be removed
     */
    public boolean remove(HashItem entry) {
        int index = calculateIndex(entry);
        if (entries[index] == null) {
            return false;
        }
        entries[index] = null;
        size--;
        return true;
    }

    /**
     * Returns the hash item with the given hash code
     *
     * @param hashCode hash code of the item to return
     * @return the item with the given hash code
     */
    public HashItem get(int hashCode) {
        int index = calculateIndex(hashCode);
        int collisionDomain = index;

        while (index < SIZE && entries[index] != null) {
            if (entries[index].hashCode() == hashCode) {
                // found element by hashCode: return it
                return entries[index];
            }
            if (calculateIndex(entries[index]) != collisionDomain) {
                // end of chain reached: abort
                return null;
            }
            index++;
        }

        if (index == SIZE) {
            return null;
        }

        return entries[index];
    }

    /**
     * Used size of the hash table
     *
     * @return size used
     */
    public int getSize() {
        return size;
    }

    /**
     * Checks if the hash table is full
     *
     * @return if the hash table is full
     */
    public boolean isFull() {
        return size == SIZE;
    }

    /**
     * Returns all elements in a collection from the hash table
     *
     * @return the collection with all elements from the hash table
     */
    public Collection<HashItem> getAllElements() {
        Collection<HashItem> allElements = new ArrayList<>(getSize());
        for (int i = 0; i < SIZE; i++) {
            if (entries[i] != null) {
                allElements.add(entries[i]);
            }
        }
        return allElements;
    }

    /**
     * Returns the calculated index from the given hash item
     * Index is calculated from the hashcode and the array size
     *
     * @param entry the hash item to calculate the index
     * @return the index of the given object
     */
    private int calculateIndex(HashItem entry) {
        return entry.hashCode() % SIZE;
    }

    /**
     * Returns the calculated index from the given hashcode
     * Index is calculated from the hashcode and the array size
     *
     * @param hashCode the hashcode to calculate the index
     * @return the index of the given hashcode
     */
    private int calculateIndex(int hashCode) {
        return hashCode % SIZE;
    }
}
