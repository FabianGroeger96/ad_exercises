package ch.hslu.AD.SW04.SimpleHashTable;

/**
 * Übung: Hashbasierte Datenstrukturen, Performance, Thirdparty-Datenstrukturen (D3)
 * Aufgabe: Einfache Hashtabelle
 *
 * @author Fabian Gröger
 * @version 14.03.2018
 */
public interface HashTableInterface {

    /**
     * Adds a new item to the hashtable
     *
     * @param item item with the hashcode
     * @return if it worked
     */
    public boolean add(HashItem item);

    /**
     * Removes a item the hashtable
     *
     * @param item item with the hashcode
     * @return if it worked
     */
    public boolean remove(HashItem item);

    /**
     * Searches if a item exists in the hashtable
     *
     * @param item the item with the hashcode that will be searched
     * @return if the hashcode exists
     */
    public boolean contains(HashItem item);

    /**
     * Searches a item and returns its index
     *
     * @param item the item to search
     * @return the index of the item
     */
    public int getIndex(HashItem item);
}
