package ch.hslu.AD.SW05.BankAccount;

public class Transfer extends Thread implements Runnable {

    // when logging is active, threads will synchronize properly
    private static final boolean LOGGING = false;

    private final static int PARTIAL_AMOUNT = 1;

    private final BankAccount sourceAccount;
    private final BankAccount targetAccount;
    private int amountDue;

    public Transfer(BankAccount sourceAccount, BankAccount targetAccount, int amount) {
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.amountDue = amount;
    }

    @Override
    public void run() {
        while (amountDue > PARTIAL_AMOUNT) {
            sourceAccount.transfer(targetAccount, PARTIAL_AMOUNT);
            amountDue -= PARTIAL_AMOUNT;
            if (LOGGING) {
                String logMsg = String.format("%d.- from %s to %s", PARTIAL_AMOUNT, sourceAccount, targetAccount);
                System.out.println(logMsg);
            }
        }
    }
}
