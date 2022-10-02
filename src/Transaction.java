import java.time.LocalDate;

public class Transaction {

    private Account accountInCharge;

    private Account destinationAccount;

    private String intendedPurpose;

    private int sum;

    private LocalDate transactionDate;
    private boolean transactionAccepted;

    public Transaction(Account accountInCharge, Account desinationAccount, String intendedPurpose, LocalDate transactionDate, int sum, boolean transactionAccepted) {
        if (accountInCharge == null || intendedPurpose == null || transactionDate == null || sum <= 0)   {
            throw new IllegalArgumentException("Kein Wert darf null / 0 sein!");
        }
        this.accountInCharge = accountInCharge;
        if (desinationAccount != null)  {
            this.destinationAccount = desinationAccount;
        }
        this.sum = sum;
        this.intendedPurpose = intendedPurpose;
        this.transactionDate = transactionDate;
        this.transactionAccepted = transactionAccepted;
    }

    public boolean pay(char type, String purpose, int sum, Account destinationAccount) {
        Transaction transaction;
        /* n = normal pay (like paying online, in a supermarket, etc.
        *  t = a real transaction between two bank accounts
        */
        switch (type) {
            case 'n' -> {
                transaction = new Transaction(this.accountInCharge, null, purpose, LocalDate.now(), sum, true);
                this.accountInCharge.updateBalance('-', transaction);
            }
            case 't' ->     {
                //more coming soon.
            }

        }
    }

    public void deleteTransaction() {

    }

    public Account getAccountInCharge() {
        return accountInCharge;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public String getIntendedPurpose() {
        return intendedPurpose;
    }

    public int getSum() {
        return sum;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionAccepted(boolean transactionAccepted) {
        this.transactionAccepted = transactionAccepted;
    }
}
