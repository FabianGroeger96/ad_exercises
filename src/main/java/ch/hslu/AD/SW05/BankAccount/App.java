package ch.hslu.AD.SW05.BankAccount;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Übung: Threads & Synchronisation (N1)
 * Aufgabe: Bankgeschäfte
 *
 * @author Fabian Gröger
 * @version 21.03.2018
 */
public class App {

    private static final Logger LOGGER = LogManager.getLogger(App.class);

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
        Bank bank = new Bank(100, 10000);

        // start transfer
        bank.startTransfers(1000, 5000);

        System.out.println(bank);
    }
}
