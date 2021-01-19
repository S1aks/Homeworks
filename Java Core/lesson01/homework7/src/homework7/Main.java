package homework7;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя:");
        printName(scanner.nextLine());
    }

    static void printName(String name) {
        System.out.println("Привет, " + name + "!");
    }
}
