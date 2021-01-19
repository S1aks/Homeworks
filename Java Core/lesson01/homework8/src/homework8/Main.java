package homework8;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите год:");
        int year = scanner.nextInt();
        printIsLeapYear(year);
    }

    static void printIsLeapYear (int year) {
        if (year % 4 != 0) {
            System.out.println("Год не високосный!");
        } else if (year % 100 == 0 && year % 400 != 0) {
            System.out.println("Год не високосный!");
        } else {
            System.out.println("Год високосный!");
        }
    }
}
