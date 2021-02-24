package homework10_2;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private final List<Contact> contactList;

    public PhoneBook() {
        contactList = new ArrayList<>();
    }

    public void add(String surname, String phoneNumber) {
        contactList.add(new Contact(surname, phoneNumber));
    }

    public void get(String surname) {
        for (Contact contact : contactList) {
            if (contact.getSurname().equals(surname)) {
                System.out.println(contact.getSurname() + ": " + contact.getPhoneNumber());
            }
        }
    }
}
