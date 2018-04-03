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

    private int balance; // balance of the bank account
    private String name; // name of the bank account

    /**
     * Default constructor, that creates a bank account with balance: 0 and name: default
     */
    public BankAccount() {
        this(0, "default");
    }

    /**
     * Constructor that creates a new bank account with the passed balance and name
     *
     * @param balance balance of the new bank account
     * @param name    name of the new bank account
     */
    public BankAccount(final int balance, final String name) {
        this.balance = balance;
        this.name = name;
    }

    /**
     * Returns the balance of the bank account
     *
     * @return balance of the account
     */
    public int getBalance() {
        return this.balance;
    }

    /**
     * Deposits an amount in the bank account
     *
     * @param amount amount to add to the bank account
     */
    public synchronized void deposite(final int amount) {
        this.balance += amount;
    }

    /**
     * Transfers an amount from the bank account to the target bank account
     *
     * @param target the target bank account that will get the amount
     * @param amount the amount that is transferred
     */
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
    @Override
    public String toString() {
        return "BankAccount [name: " + this.name + ", balance: " + this.balance + "]";
    }
}
