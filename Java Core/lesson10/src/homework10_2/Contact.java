package homework10_2;

public class Contact {
    private final String surname;
    private final String phoneNumber;

    public Contact(String surname, String phoneNumber) {
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
