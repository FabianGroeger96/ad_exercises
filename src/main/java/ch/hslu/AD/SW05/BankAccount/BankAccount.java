package ch.hslu.AD.SW05.BankAccount;

/**
 * Übung: Threads & Synchronisation (N1)
 * Aufgabe: Bankgeschäfte
 * <p>
 * Einfaches Bankkonto, das nur den Kontostand beinhaltet.
 *
 * @author Fabian Gröger
 * @version 21.03.2018
 */
public final class BankAccount {

    private int balance;

    /**
     * Erzeugt ein Bankkonto mit einem Anfangssaldo. * @param balance Anfangssaldo
     */
    public BankAccount(final int balance) {
        this.balance = balance;
    }

    /**
     * Erzeugt ein Bankkonto mit Kontostand Null.
     */
    public BankAccount() {
        this(0);
    }

    /**
     * Gibt den aktuellen Kontostand zurück. * @return Kontostand.
     */
    public int getBalance() {
        return this.balance;
    }

    /**
     * Addiert zum bestehen Kontostand einen Betrag hinzu. * @param amount Einzuzahlender Betrag
     */
    public void deposite(final int amount) {
        this.balance += amount;
    }

    /**
     * Überweist einen Betrag vom aktuellen Bankkonto an ein Ziel-Bankkonto. * @param target Bankkonto auf welches der Betrag überwiesen wird.
     *
     * @param amount zu überweisender Betrag.
     */
    public void transfer(final BankAccount target, final int amount) {
        this.balance -= amount;
        target.deposite(amount);
    }
}
