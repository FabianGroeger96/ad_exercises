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
public class App {

    private static final Logger LOGGER = LogManager.getLogger(HashSet.class);

    /**
     * Privater Konstruktor.
     */
    private App() {
    }

    /**
     * Main-Methode.
     *
     * @param args Startargumente.
     */
    public static void main(final String[] args) {
        HashSet hashSet = new HashSet();
        hashSet.add(new HashItem(1));
        hashSet.add(new HashItem(2));
        hashSet.add(new HashItem(3));

        hashSet.print();
    }
}
