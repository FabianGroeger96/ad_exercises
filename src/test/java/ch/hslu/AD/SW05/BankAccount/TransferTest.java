package ch.hslu.AD.SW05.BankAccount;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TransferTest {

    public static final int AMOUNT = 1_000;

    public static final int ACCOUNTS = 10000;

    private static final Random random = new Random(System.currentTimeMillis());

    private List<BankAccount> sourceAccounts;
    private List<BankAccount> targetAccounts;

    private int totalSourceStartBalance = 0;
    private int totalTargetStartBalance = 0;

    @Before
    public void createScenario() {
        this.sourceAccounts = createBankAccounts(ACCOUNTS, "source accounts");
        this.targetAccounts = createBankAccounts(ACCOUNTS, "target accounts");

        this.totalSourceStartBalance = calculateTotalBalance(sourceAccounts);
        this.totalTargetStartBalance = calculateTotalBalance(targetAccounts);
    }

    @Test
    public void testOneWayTransfer() {
        List<Transfer> transfers = createTransfers(sourceAccounts, targetAccounts);

        for (Transfer transfer : transfers) {
            transfer.start();
        }

        for (Transfer transfer : transfers) {
            try {
                transfer.join();
            } catch (InterruptedException e) {
                Assert.fail("could not join the threads");
            }
        }

        int totalSourceEndBalance = calculateTotalBalance(sourceAccounts);
        int totalTargetEndBalance = calculateTotalBalance(targetAccounts);
        Assert.assertEquals(totalSourceStartBalance + totalTargetStartBalance,
                totalSourceEndBalance + totalTargetEndBalance);
    }

    @Test
    public void testTwoWayTransfer() {
        List<Transfer> forward = createTransfers(sourceAccounts, targetAccounts);
        List<Transfer> backward = createTransfers(targetAccounts, sourceAccounts);

        for (int n = 0; n < ACCOUNTS; n++) {
            forward.get(n).start();
            backward.get(n).start();
        }

        for (int n = 0; n < ACCOUNTS; n++) {
            try {
                forward.get(n).join();
                backward.get(n).join();
            } catch (InterruptedException e) {
                Assert.fail("could not join the threads");
            }
        }

        int totalSourceEndBalance = calculateTotalBalance(sourceAccounts);
        int totalTargetEndBalance = calculateTotalBalance(targetAccounts);

        Assert.assertEquals(totalSourceStartBalance, totalSourceEndBalance);
        Assert.assertEquals(totalTargetStartBalance, totalTargetEndBalance);
    }

    private static List<BankAccount> createBankAccounts(int accounts, String prefix) {
        List<BankAccount> bankAccounts = new ArrayList<>();
        for (int n = 0; n < accounts; n++) {
            int initialBalance = random.nextInt(AMOUNT) + AMOUNT;
            bankAccounts.add(new BankAccount(initialBalance, prefix + (n + 1)));

        }
        return bankAccounts;
    }

    private static List<Transfer> createTransfers(List<BankAccount> sourceAccounts, List<BankAccount> targetAccounts) {
        List<Transfer> transfers = new ArrayList<>();
        for (int i = 0; i < ACCOUNTS; i++) {
            Transfer transfer = new Transfer(sourceAccounts.get(i), targetAccounts.get(i), AMOUNT);
            transfers.add(transfer);
        }
        return transfers;
    }

    private int calculateTotalBalance(List<BankAccount> accounts) {
        int balance = 0;
        for (BankAccount account : accounts) {
            balance += account.getBalance();
        }
        return balance;
    }

}