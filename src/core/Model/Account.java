import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;

public class Account {

    private Person owner;

    private int balance = 0;

    private Transaction[] transactionHistory;

    private Debitcard debitCardAssaciation;

    private String IBAN;

    private static int BLZ = 43000;
    private Random random = new Random();


    public Account(Person personInCharge) {
        //register person to account
        if (personInCharge != null) {
            this.owner = personInCharge;
        }
        //generate fake IBAN
        this.IBAN = this.generateIBAN();

        //create a debit card associated with account
        Debitcard debitcard = new Debitcard(this);
    }

    public String generateIBAN() {
        //generate fake IBAN
        int randomNumberLength = 12 - (this.getBLZ().length() + 1);

        StringBuilder builder = new StringBuilder(this.getBLZInt());
        for (int i = 0; i < randomNumberLength; i++) {
            int digit = this.random.nextInt(10);
            builder.append(digit);
        }
        String IBAN = "AT95" + builder.toString();
        return IBAN;
    }

    public void updatePersonInCharge(Person newPersonInCharge) {
        if (newPersonInCharge != null) {
            this.owner = newPersonInCharge;
        }
    }

    public void updateBalance(char operation, Transaction transaction) {
        switch (operation) {
            case '+' -> {
                if (transaction.getSum() > 0) {
                    this.balance += transaction.getSum();
                    transaction.setTransactionAccepted(true);
                }
            }
            case '-' -> {
                if (this.balance - transaction.getSum() < 0) {
                    transaction.setTransactionAccepted(false);
                    System.out.println("Warnung! Transaktion konnte nicht bearbeitet werden." + "\n" + "Grund: Zu wenig Guthaben.");
                } else if (this.balance - transaction.getSum() >= 0) {
                    this.balance -= transaction.getSum();
                    transaction.setTransactionAccepted(true);
                }
            }
            default -> {
                transaction.setTransactionAccepted(false);
            }
        }

    }
    public void addTransaction(Transaction newTransaction) {
        transactionHistory = Arrays.copyOf(this.transactionHistory, this.transactionHistory.length + 1);
        transactionHistory[transactionHistory.length - 1] = newTransaction;
    }

    public boolean deposit(int amount) {
        Transaction trDeposit;
        if (amount > 0) {
            this.balance += amount;
            trDeposit = new Transaction(this, null, "Deposing credit on account.",
                    LocalDate.now(), amount, true);
            this.addTransaction(trDeposit);
            return true;
        }
        trDeposit = new Transaction(this, null, "Deposing credit on account.",
                LocalDate.now(), amount, false);
        this.addTransaction(trDeposit);
        return false;
    }

    public boolean withDraw(int amount) {
        Transaction trWithdraw;
        if (this.balance - amount >= 0)  {
            this.balance -= amount;
            trWithdraw = new Transaction(this, null, "Withdrawing credit from account.",
                    LocalDate.now(), amount, true);
            this.addTransaction(trWithdraw);
            return true;
        }
        trWithdraw = new Transaction(this, null, "Withdrawing credit from account.",
                LocalDate.now(), amount, false);
        this.addTransaction(trWithdraw);
        return false;
    }

    public void generateNewDebitcard(String reason) {
        this.debitCardAssaciation.generateDebitcardData();
    }

    public Person getOwner() {
        return this.owner;
    }

    public int getBalance() {
        return this.balance;
    }

    public Transaction[] getTransactionHistory() {
        return transactionHistory;
    }

    public Debitcard getDebitcard() {
        return this.debitCardAssaciation;
    }

    public String getIBAN() {
        return IBAN;
    }

    public String getBLZ() {
        return "" + Account.BLZ;
    }

    public int getBLZInt() {
        return Account.BLZ;
    }

    public Transaction getPrevousTransaction() {
        return this.transactionHistory[this.transactionHistory.length - 1];
    }

    public String showDetails() {
        String details = "";
        details += "Account holder: " + "\n";
        details += this.owner.toString() + "\n";
        details += "Account balance: " + this.balance + "\n";
        details += "Account balance: " + this.balance + "\n";
        details += "Transaction history: " + "\n";
        for (int i = 0; i < this.transactionHistory.length; i++) {
            if (this.transactionHistory[i] != null) {
                details += this.transactionHistory[i].toString();
            }
        }
        details += "Debit card: " + "\n";
        details += this.debitCardAssaciation.toString();
        details += "IBAN: " + this.IBAN + "\n";
        details += "BLZ: " + Account.BLZ + "\n";
        return details;
    }
}
