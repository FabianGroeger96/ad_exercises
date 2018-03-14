package ch.hslu.AD.SW04.HashTable;

/**
 * Übung: Hashbasierte Datenstrukturen, Performance, Thirdparty-Datenstrukturen (D3)
 * Aufgabe: Einfache Hashtabelle
 *
 * @author Fabian Gröger
 * @version 14.03.2018
 */
public interface HashTableInterface {

    /**
     * Adds a new hashcode to the hashtable
     * @param hashcode hashcode to add
     * @return if it worked
     */
    public boolean add(int hashcode);

    /**
     * Removes a hashcode at index from the hashtable
     * @param index index of the hashcode
     * @return if it worked
     */
    public boolean remove(int index);

    /**
     * Searches if a hashcode exists in the hashtable
     * @param hashcode the hashcode that will be searched
     * @return if the hashcode exists
     */
    public boolean search(int hashcode);
}
