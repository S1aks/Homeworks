package homework4;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Первое число: ");
        int num1 = scanner.nextInt();
        System.out.println("Второе число: ");
        int num2 = scanner.nextInt();
        if (checkSumRange(num1, num2)) {
            System.out.println("Сумма чисел в диапазоне 10..20");
        } else {
            System.out.println("Сумма чисел не в диапазоне 10..20");
        }
    }

    static boolean checkSumRange (int a, int b) {
        if (a+b >= 10 && a+b <= 20) {
            return true;
        }
        return false;
    }
}
