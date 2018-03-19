package ch.hslu.AD.SW04.CollisionHashTable;

import ch.hslu.demo.DemoApp;
import ch.hslu.demo.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {

    private static final Logger LOGGER = LogManager.getLogger(HashSet.class);

    /**
     * Privater Konstruktor.
     */
    private App() {
    }

    /**
     * Main-Methode.
     * @param args Startargumente.
     */
    public static void main(final String[] args) {
        HashSet<HashItem> hashSet = new HashSet<HashItem>();
        hashSet.add(new HashItem(10, 10));
        hashSet.add(new HashItem(11, 10));
        hashSet.add(new HashItem(12, 10));

        hashSet.add(new HashItem(17, 17));

        hashSet.print();
    }
}
