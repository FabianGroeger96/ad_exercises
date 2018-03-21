package ch.hslu.AD.SW04.CollisionHashTable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {

    private static final Logger LOGGER = LogManager.getLogger(HashTable.class);

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
        /*
          Hash table generic
          Not full implemented
          Problems with tombstones
         */
        HashTable<HashItem> hashSet = new HashTable<>();
        hashSet.add(new HashItem(10, 10));
        hashSet.add(new HashItem(11, 10));
        hashSet.add(new HashItem(12, 10));

        hashSet.add(new HashItem(17, 17));

        hashSet.print();

        LOGGER.info("----------");

        hashSet.remove(new HashItem(11, 10));

        hashSet.print();

        LOGGER.info("----------");

        hashSet.remove(new HashItem(12, 10));

        hashSet.print();

        LOGGER.info("----------");

        hashSet.add(new HashItem(11, 10));

        hashSet.print();
    }
}
