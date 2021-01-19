package homework6;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число:");
        int num = scanner.nextInt();
        if (isNegativeNum(num)) {
            System.out.println("Число отрицательное!");
        } else {
            System.out.println("Число положительное!");
        }
    }

    static boolean isNegativeNum(int n) {
        if (n < 0) {
            return true;
        }
        return false;
    }
}
