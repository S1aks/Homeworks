package com.hw;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        NumNumbers numNumbers = new NumNumbers("Числа", 20, 20);

        System.out.println("Задание 2.1\n");
        System.out.println("Вывод массива методом Arrays.toString (используя класс Arrays): ");
        long startTime = System.nanoTime();
        System.out.println(Arrays.toString(numNumbers.arrayNumbers));
        long endTime = System.nanoTime();
        System.out.println("Наносекунд затрачено: " + (endTime - startTime) + "\n");

        System.out.println("Сортировка массива методом Arrays.sort (используя класс Arrays): ");
        startTime = System.nanoTime();
        Arrays.sort(numNumbers.arrayNumbers);
        endTime = System.nanoTime();
        System.out.println(Arrays.toString(numNumbers.arrayNumbers));
        System.out.println("Наносекунд затрачено: " + (endTime - startTime) + "\n");

        System.out.println("Заполнение массива методом Arrays.fill (используя класс Arrays): ");
        startTime = System.nanoTime();
        Arrays.fill(numNumbers.arrayNumbers,8);
        endTime = System.nanoTime();
        System.out.println(Arrays.toString(numNumbers.arrayNumbers));
        System.out.println("Наносекунд затрачено: " + (endTime - startTime));
        System.out.println("------------------------------------------------------------------");

        System.out.println("Задание 2.2\n");
        numNumbers = new NumNumbers("Числа", 20, 20);

        System.out.println("Линейный поиск в массиве числа 15: ");
        System.out.println(Arrays.toString(numNumbers.arrayNumbers));
        startTime = System.nanoTime();
        numNumbers.findNumberInArrayLinear(15);
        endTime = System.nanoTime();
        System.out.println("Наносекунд затрачено: " + (endTime - startTime) + "\n");

        System.out.println("Бинарный поиск в массиве числа 15 (с предварительной сортировкой): ");
        System.out.println(Arrays.toString(numNumbers.arrayNumbers));
        startTime = System.nanoTime();
        Arrays.sort(numNumbers.arrayNumbers);
        numNumbers.findNumberInArrayBinary(15);
        endTime = System.nanoTime();
        System.out.println("Массив после сортировки: ");
        System.out.println(Arrays.toString(numNumbers.arrayNumbers));
        System.out.println("Наносекунд затрачено: " + (endTime - startTime) + "\n");
        System.out.println("------------------------------------------------------------------");

        System.out.println("Задание 2.3\n");
        numNumbers = new NumNumbers("Числа", 400, 100);
        int[] array400Elements = numNumbers.arrayNumbers.clone(); // Запоминаем первоначальное состояние массива
        System.out.println("Сортировка массива из 400 элементов методом Arrays.sort: ");
        startTime = System.nanoTime();
        Arrays.sort(numNumbers.arrayNumbers);
        endTime = System.nanoTime();
        System.out.println("Наносекунд затрачено: " + (endTime - startTime) + "\n");
        System.out.println("------------------------------------------------------------------");

        System.out.println("Задание 2.4\n");
        numNumbers.arrayNumbers = array400Elements.clone();
        System.out.println("Сортировка пузырьком массива из 400 элементов: ");
        startTime = System.nanoTime();
        numNumbers.bubbleSort();
        endTime = System.nanoTime();
        System.out.println("Наносекунд затрачено: " + (endTime - startTime) + "\n");
        System.out.println("------------------------------------------------------------------");

        System.out.println("Задание 2.5\n");
        numNumbers.arrayNumbers = array400Elements.clone();
        System.out.println("Сортировка методом выбора массива из 400 элементов: ");
        startTime = System.nanoTime();
        numNumbers.selectSort();
        endTime = System.nanoTime();
        System.out.println("Наносекунд затрачено: " + (endTime - startTime) + "\n");
        System.out.println("------------------------------------------------------------------");

        System.out.println("Задание 2.6\n");
        numNumbers.arrayNumbers = array400Elements.clone();
        System.out.println("Сортировка методом вставки массива из 400 элементов: ");
        startTime = System.nanoTime();
        numNumbers.insertSort();
        endTime = System.nanoTime();
        System.out.println("Наносекунд затрачено: " + (endTime - startTime) + "\n");
        System.out.println("------------------------------------------------------------------");
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

    public void findNumberInArrayLinear(int number) {
        int findIndex = -1;
        for (int i = 0; i < arrayNumbers.length; i++) {
            int arrayNumber = arrayNumbers[i];
            if (arrayNumber == number) {
                findNumber = true;
                findIndex = i;
                break;
            }
        }
        if (findNumber) {
            System.out.println("Число " + number + " найдено в массиве под индексом " + findIndex);
        } else {
            System.out.println("Число " + number + " не найдено в массиве.");
        }
    }

    public void findNumberInArrayBinary(int number) {
        int findIndex = -1;
        int firstIndex = 0;
        int lastIndex = arrayNumbers.length - 1;
        while (firstIndex <= lastIndex) {
            int middleIndex = (firstIndex + lastIndex) / 2;
            if (number == arrayNumbers[middleIndex]) {
                findNumber = true;
                findIndex = middleIndex;
                break;
            } else if (number > arrayNumbers[middleIndex]) {
                firstIndex = middleIndex + 1;
            } else if (number < arrayNumbers[middleIndex]) {
                lastIndex = middleIndex - 1;
            }
        }
        if (findNumber) {
            System.out.println("Число " + number + " найдено в массиве под индексом " + findIndex);
        } else {
            System.out.println("Число " + number + " не найдено в массиве.");
        }
    }

    public void swapElements(int firstIndex, int lastIndex) {
        int buffer = arrayNumbers[firstIndex];
        arrayNumbers[firstIndex] = arrayNumbers[lastIndex];
        arrayNumbers[lastIndex] = buffer;
    }

    public void bubbleSort() {
        for (int i = arrayNumbers.length - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (arrayNumbers[j] > arrayNumbers[j+1]) {
                    swapElements(j, j+1);
                }
            }
        }
    }

    public void selectSort() {
        for (int i = 0; i < arrayNumbers.length - 1; i++) {
            int minNumberIndex = i;
            for (int j = i+1; j < arrayNumbers.length; j++) {
                if(arrayNumbers[j] < arrayNumbers[minNumberIndex]) {
                    minNumberIndex = j;
                }
            }
            swapElements(i, minNumberIndex);
        }
    }

    public void insertSort() {
        int index;
        for (int i = 1; i < arrayNumbers.length; i++) {
            int buffer = arrayNumbers[i];
            index = i;
            while (index > 0 && arrayNumbers[index - 1] >= buffer) {
                arrayNumbers[index] = arrayNumbers[index - 1];
                --index;
            }
            arrayNumbers[index] = buffer;
        }
    }
}