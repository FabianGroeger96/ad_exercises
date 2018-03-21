package ch.hslu.AD.SW05.BankAccount;

import java.util.ArrayList;
import java.util.List;

/**
 * Übung: Threads & Synchronisation (N1)
 * Aufgabe: Bankgeschäfte
 *
 * @author Fabian Gröger
 * @version 21.03.2018
 */
public class Bank {
    private List<BankAccount> sourceAccounts = new ArrayList<>(); // list of the source accounts
    private List<BankAccount> targetAccounts = new ArrayList<>(); // list of the target accounts

    /**
     * Constructor to create a bank
     *
     * @param amountAccounts the amount of accounts to create
     * @param initialBalance the initial balance of every bank account
     */
    public Bank(final int amountAccounts, final int initialBalance) {
        this.createAccounts(amountAccounts, initialBalance);
    }

    /**
     * Creates all bank accounts
     *
     * @param amountAccounts the amount of accounts to create
     * @param initialBalance the initial balance of every bank account
     */
    private void createAccounts(final int amountAccounts, final int initialBalance) {
        for (int i = 0; i < amountAccounts; i++) {
            sourceAccounts.add(new BankAccount(initialBalance));
            targetAccounts.add(new BankAccount(initialBalance));
        }
    }

    /**
     * Starts the transactions
     *
     * @param maxThreads the max. number of "mini" threads
     * @param amount     the total amount to transfer
     */
    public void startTransfers(final int maxThreads, final int amount) {
        List<Thread> threads = new ArrayList<>();

        final int transferAmount = amount / maxThreads;

        //Setup threads
        for (int i = 0; i < sourceAccounts.size(); i++) {
            BankAccount sourceAccount = sourceAccounts.get(i);
            BankAccount targetAccount = targetAccounts.get(i);

            for (int j = 0; i < maxThreads; i++) {
                threads.add(new Thread(() -> sourceAccount.transfer(targetAccount, transferAmount)));
                threads.add(new Thread(() -> targetAccount.transfer(sourceAccount, transferAmount)));
            }
        }

        threads.stream().forEach((thread) -> thread.start());
        threads.stream().forEach((thread) -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Returns a string representation of the class
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (BankAccount bankAccount : this.sourceAccounts) {
            stringBuilder.append(bankAccount + "\n");
        }

        for (BankAccount bankAccount : this.targetAccounts) {
            stringBuilder.append(bankAccount + "\n");
        }

        return stringBuilder.toString();
    }
}
