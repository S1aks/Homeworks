package com.hw;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("Задания 5.1 - 5.3 описаны в файле HTML к проекту. Вызовы методов есть в теле класса.");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Задание 5.4");
        System.out.println("Реализация простого алгоритма циклом и рекурсивным методом: ");
        System.out.print("Факториал числа 10 циклом = ");
        TimeCheck.point();
        int factorial = 1;
        for (int i = 1; i <= 10; i++) {
            factorial *= i;
        }
        System.out.print(factorial);
        TimeCheck.check();
        System.out.print("Факториал числа 10 методом = ");
        TimeCheck.point();
        System.out.print(factorialMethod(10));
        TimeCheck.check();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Задание 5.5");
        System.out.println("Реализация алгоритма двоичного рекурсивного поиска на основе массива: ");
        int[] array = {11, 17, 7, 6, 15, 14, 14, 14, 0, 14, 6, 19, 4, 2, 13, 1, 11, 11, 3, 12};
        int[] arraySave = array.clone();
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
        System.out.print("Двоичный поиск числа 15 циклом: Индекс - ");
        TimeCheck.point();
        System.out.print(arrayBinarySearch(array, 15));
        TimeCheck.check();
        System.out.print("Двоичный поиск числа 15 методом: Индекс - ");
        TimeCheck.point();
        System.out.print(recursionBinarySearch(15, 0, array.length - 1, array));
        TimeCheck.check();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Задание 5.6");
        System.out.println("Реализация алгоритма сортировки слиянием на основе массива: ");
        array = arraySave.clone();
        System.out.println(Arrays.toString(array));
        System.out.println("Сортировка слиянием: ");
        TimeCheck.point();
        System.out.println(Arrays.toString(recursionSortMerge(array)));
        TimeCheck.check();
        array = arraySave.clone();
        System.out.println("Сортировка массива методом Arrays.sort():");
        TimeCheck.point();
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
        TimeCheck.check();
    }

    public static int recursionInfinite(int element) { // Бесконечная рекурсия
        System.out.println(element);
        return recursionInfinite(element - 1);
    }

    public static int recursionFinite(int element) { // Рекурсия с условием выхода
        System.out.println(element);
        return element > 0 ? recursionFinite(element - 1) : 0;
    }

    public static int factorialMethod(int element) {
        return element > 1 ? element * factorialMethod(element - 1) : 1;
    }
    public static int arrayBinarySearch(int[] array, int number) {
        int findIndex = -1;
        int firstIndex = 0;
        int lastIndex = array.length - 1;
        while (firstIndex <= lastIndex) {
            int middleIndex = (firstIndex + lastIndex) / 2;
            if (number == array[middleIndex]) {
                findIndex = middleIndex;
                break;
            } else if (number > array[middleIndex]) {
                firstIndex = middleIndex + 1;
            } else if (number < array[middleIndex]) {
                lastIndex = middleIndex - 1;
            }
        }
        return findIndex;
    }

    public static int recursionBinarySearch(int searchKey, int indexFrom, int indexTo, int[] array) {
        if (indexFrom > indexTo) {
            return -1;
        }
        int indexMiddle = (indexFrom + indexTo) / 2;
        if (searchKey == array[indexMiddle]) {
            return indexMiddle;
        } else if (searchKey < array[indexMiddle]) {
            return recursionBinarySearch(searchKey, indexFrom, indexMiddle - 1, array);
        } else {
            return recursionBinarySearch(searchKey, indexMiddle + 1, indexTo, array);
        }
    }

    public static int[] recursionSortMerge(int[] array) {
        if (array.length < 2) {
            return array;
        }
        int halfArray = array.length / 2;
        return mergeArraysToOne(recursionSortMerge(Arrays.copyOfRange(array, 0, halfArray)),
                recursionSortMerge(Arrays.copyOfRange(array, halfArray, array.length)));
    }

    private static int[] mergeArraysToOne(int[] arrayA, int[] arrayB) {
        int[] resultArray = new int[arrayA.length + arrayB.length];
        int iA = 0;
        int iB = 0;
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = arrayA[iA] < arrayB[iB] ? arrayA[iA++] : arrayB[iB++];
            if (iA == arrayA.length) {
                System.arraycopy(arrayB, iB, resultArray, ++i, arrayB.length - iB);
                break;
            }
            if (iB == arrayB.length) {
                System.arraycopy(arrayA, iA, resultArray, ++i, arrayA.length - iA);
                break;
            }
        }
        return resultArray;
    }
}

class TimeCheck {
    static long timePoint;
    public static void point() {
        timePoint = System.nanoTime();
    }
    public static void check() {
        long secondPoint = System.nanoTime();
        System.out.println(" \u26a0 Время выполнения: " + (secondPoint - timePoint) + " наносекунд");
    }
}
