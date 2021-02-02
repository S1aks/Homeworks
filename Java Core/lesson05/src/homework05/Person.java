package homework05;

public class Person {
    private String name;
    private String post;
    private String email;
    private String phone;
    private int salary;
    private int age;

    public Person (String name, String post, String email, String phone, int salary, int age) {
        this.name = name;
        this.post = post;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void printPerson() {
        System.out.println( "ФИО: " + this.name +
                " Должность: " + this.post +
                " E-mail: " + this.email +
                " Телефон: " + this.phone +
                " Зарплата: " + this.salary +
                " Возраст: " + this.age);
    }
}
