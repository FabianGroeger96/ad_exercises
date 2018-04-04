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
        // creates a Bank with an amount of accounts and an initial balance
        Bank bank = new Bank(4, 10000);

        // start transfer
        bank.startTransfers(50000);

        // output of the bank with the bank accounts
        System.out.println(bank.toString());
    }
}
