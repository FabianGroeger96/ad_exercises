package ch.hslu.AD.SW05.BankAccount;

import java.util.ArrayList;
import java.util.List;

/**
 * Übung: Hashbasierte Datenstrukturen, Performance, Thirdparty-Datenstrukturen (D3)
 * Aufgabe: Hashtabelle mit Buckets (Listen für Kollisionen)
 *
 * @author Fabian Gröger
 * @version 19.03.2018
 */
public class Bank {
    private List<BankAccount> sourceAccounts = new ArrayList<>();
    private List<BankAccount> targetAccounts = new ArrayList<>();

    public Bank(final int amountAccounts, final int initialBalance){}
}
