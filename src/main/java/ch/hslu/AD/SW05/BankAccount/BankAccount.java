package ch.hslu.AD.SW05.BankAccount;

/**
 * Übung: Threads & Synchronisation (N1)
 * Aufgabe: Bankgeschäfte
 * <p>
 * Simple bank account, that only holds the balance and the name
 *
 * @author Fabian Gröger
 * @version 21.03.2018
 */
public final class BankAccount {

    private int balance;
    private String name;


    public BankAccount(final int balance, final String name) {
        this.balance = balance;
        this.name = name;
    }

    public int getBalance() {
        return this.balance;
    }

    public synchronized void deposite(final int amount) {
        this.balance += amount;
    }

    public void transfer(final BankAccount target, final int amount) {
        synchronized (this) {
            this.balance -= amount;
        }
        target.deposite(amount);
    }

    /**
     * Returns the name of the bank account
     *
     * @return name of the bank account
     */
    public String getName() {
        return name;
    }

    /**
     * Converts the bank account to a string
     *
     * @return string representation of the bank account
     */
    public String toString() {
        return name + " (" + String.valueOf(balance) + ".-)";
    }
}
