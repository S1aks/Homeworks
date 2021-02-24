package homework10_2;

public class Main {

    public static void main(String[] args) {
        PhoneBook myPhoneBook = new PhoneBook();
        myPhoneBook.add("Петров", "89656334223");
        myPhoneBook.add("Иванов", "89471009921");
        myPhoneBook.add("Петров", "89088277177");
        myPhoneBook.add("Блохин", "84553342293");
        myPhoneBook.add("Чернов", "83565775530");
        myPhoneBook.add("Белов", "84345359123");

        myPhoneBook.get("Петров");
    }
}
