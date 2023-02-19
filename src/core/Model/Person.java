import java.time.LocalDate;

public class Person {

    private final String name;

    private final String address;

    private final LocalDate dateOfBirth;

    private final String email;

    private final String occupation;

    private final int phoneNumber;

    private int passportNumber;

    public Person(String name, String address, LocalDate dateOfBirth, String email, String occupation, int phoneNumber, int passportNumber) {
        if (name == null || address == null || dateOfBirth == null || email == null ||occupation == null || phoneNumber <= 0 ||passportNumber <= 0) {
            throw new IllegalArgumentException("Kein Wert darf null sein!");
        }
        if (name.equals("") || address.equals("") || email.equals("") || occupation.equals(""))  {
            throw new IllegalArgumentException("Bitte geben Sie echte Werte an!");
        }
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.occupation = occupation;
        this.phoneNumber = phoneNumber;
        this.passportNumber = passportNumber;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getDateOfBirth() {
        return Debitcard.parseDate(this.dateOfBirth);
    }

    public String getEmail() {
        return this.email;
    }

    public String getOccupation() {
        return this.occupation;
    }

    public int getPhoneNumber() {
        return this.phoneNumber;
    }

    public int getPassportNumber() {
        return this.passportNumber;
    }

    public String toString() {
        String pToString = "";
        pToString += "Name: " + this.name + "\n";
        pToString += "Address: " + this.address + "\n";
        pToString += "Dafe of Birth: : " + this.getDateOfBirth() + "\n";
        pToString += "Email: " + this.email + "\n";
        pToString += "Occupation: " + this.occupation + "\n";
        pToString += "Phone number: " + this.phoneNumber + "\n";
        pToString += "Passwort number: " + this.passportNumber + "\n";
        return pToString;
    }

}
