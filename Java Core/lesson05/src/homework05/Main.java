package homework05;

public class Main {
    private static final int SLICE_AGE = 40;

    public static void main(String[] args) {
        Person[] persArray = new Person[5];
        persArray[0] = new Person("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 30000, 30);
        persArray[1] = new Person("Petrov Petr", "Manager", "petropetr@mailbox.com", "892876551", 50000, 42);
        persArray[2] = new Person("Kulikova Olga", "Teacher", "kolga@mailbox.com", "890928772", 15000, 28);
        persArray[3] = new Person("Alieva Irina", "Doctor", "irinal@mailbox.com", "895176276", 20000, 45);
        persArray[4] = new Person("Putin Nikolay", "Driver", "pupun@mailbox.com", "890192988", 35000, 49);
        System.out.println("-------------------------------------------");
        System.out.println("Список сотрудников с возрастом более " + SLICE_AGE + " лет");
        System.out.println("-------------------------------------------");
        for (Person person : persArray) {
            if (person.getAge() > SLICE_AGE) {
                person.printPerson();
            }
        }
        System.out.println("-------------------------------------------");
    }
}
