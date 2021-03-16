package com.hw;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        NumNumbers numNumbers = new NumNumbers("Числа", 20, 20);
        numNumbers.printArray();
        long startTime = System.nanoTime();
        numNumbers.findNumberInArray(15);
        long endTime = System.nanoTime();
        System.out.println("Наносекунд затрачено на поиск: " + (endTime - startTime));
    }
}

class NumNumbers {
    int minimalNumber;
    boolean findNumber;
    String name;
    int[] arrayNumbers;

    public NumNumbers(String name, int quantity, int maxNumber) {
        minimalNumber = maxNumber - 1;
        findNumber = false;
        this.name = name;
        arrayNumbers = new int[quantity];
        for (int i = 0; i < quantity; i++) {
            arrayNumbers[i] = (int) ((Math.random() * maxNumber));
            if (minimalNumber > arrayNumbers[i]) {
                minimalNumber = arrayNumbers[i];
            }
        }
    }

    public void printArray() {
        System.out.println("Массив данных: " + Arrays.toString(arrayNumbers));
    }

    public void findNumberInArray(int number) {
        for (int arrayNumber : arrayNumbers) {
            if (arrayNumber == number) {
                findNumber = true;
                break;
            }
        }
        if (findNumber) {
            System.out.println("Число " + number + " найдено в массиве.");
        } else {
            System.out.println("Число " + number + " не найдено в массиве.");
        }
    }
}
