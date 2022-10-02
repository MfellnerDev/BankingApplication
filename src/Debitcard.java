import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
public class Debitcard {

    private Account accountAssociation;

    private int cardNumber;

    private int cardPIN;

    private LocalDate validUntil;

    private int notice;

    private Random random = new Random();

    public Debitcard(Account accountAssaciation) {
        if (accountAssaciation != null) {
            this.accountAssociation = accountAssaciation;
            this.generateDebitcardData();
        }
    }

    public void generateDebitcardData() {
        //generate fake credit card number
        int randomNumberLength = 16 - (this.accountAssociation.getBLZ().length() + 1);

        StringBuilder builder = new StringBuilder(this.accountAssociation.getBLZInt());
        for (int i = 0; i < randomNumberLength; i++) {
            int digit = this.random.nextInt(10);
            builder.append(digit);
        }
        this.cardNumber = Integer.parseInt(builder.toString());

        //generate fake card pin
        randomNumberLength = 4;

        builder = new StringBuilder();
        for (int i = 0; i < randomNumberLength; i++) {
            int digit = this.random.nextInt(10);
            builder.append(digit);
        }
        this.cardPIN = Integer.parseInt(builder.toString());

        //set the validUntil date, its a standard date
        LocalDate validUntilDate = LocalDate.of(2027, 13, 31);
        this.validUntil = validUntilDate;
    }

    public Account getAccountAssaciation() {
        return this.accountAssociation;
    }

    public int getCardNumber() {
        return this.cardNumber;
    }

    public int cardPIN() {
        return this.cardPIN;
    }

    public String getDateValidUntil() {
        return Debitcard.parseDate(this.validUntil);
    }

    public static String parseDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        return date.format(formatter);
    }

    public String toString() {
        String summary = "";
        summary += "Associated Account: " + this.accountAssociation.toString() + "\n";
        summary += "Card number: " + this.cardNumber + "\n";
        summary += "Card PIN: " + this.cardPIN + "\n";
        summary += "Valid until: " + this.getDateValidUntil() + "\n";
        summary += "Valid until: " + this.getDateValidUntil() + "\n";
        if (this.notice > 0)    {
            summary += "Valid until: " + this.getDateValidUntil() + "\n";
        }
        return summary;
    }

}
