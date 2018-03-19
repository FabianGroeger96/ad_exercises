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

    public static final int SIZE = 20000;

    private HashItem entries[] = new HashItem[SIZE];

    private int size = 0;

    public boolean put(HashItem entry) {
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

    public boolean remove(HashItem entry) {
        int index = calculateIndex(entry);
        if (entries[index] == null) {
            return false;
        }
        entries[index] = null;
        size--;
        return true;
    }

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

    public int getSize() {
        return size;
    }

    public boolean isFull() {
        return size == SIZE;
    }

    public Collection<HashItem> getAllElements() {
        Collection<HashItem> allElements = new ArrayList<>(getSize());
        for (int i = 0; i < SIZE; i++) {
            if (entries[i] != null) {
                allElements.add(entries[i]);
            }
        }
        return allElements;
    }

    private int calculateIndex(HashItem entry) {
        return entry.hashCode() % SIZE;
    }

    private int calculateIndex(int hashCode) {
        return hashCode % SIZE;
    }
}
