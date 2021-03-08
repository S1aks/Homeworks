package org.example;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // Задание 1
        int[] array1 = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        try {
            System.out.println(Arrays.toString(arraySliceAfterLast4(array1)));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        // Задание 2
        int[] array2 = {1, 1, 4, 1, 1, 4, 1, 1, 4, 4};
        try {
            System.out.println(checkCompositionArrayFrom1And4Numbers(array2));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public static int[] arraySliceAfterLast4(int[] array) throws RuntimeException{
        int last4Index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 4) {
                last4Index = i;
            }
        }
        if (last4Index == -1) {
            throw new IllegalArgumentException("Неверные элементы массива. Отсутствует хотя бы один элемент 4. ");
        }
        return (last4Index < array.length - 1 ? Arrays.copyOfRange(array, last4Index + 1, array.length) : new int[0]);
    }

    public static boolean checkCompositionArrayFrom1And4Numbers(int[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Отсутствуют элементы массива.");
        }
        boolean number1Present = false;
        boolean number4Present = false;
        for (int element : array) {
            if (element != 1 && element != 4) {
                return false;
            }
            if (element == 1) {
                number1Present = true;
            }
            if (element == 4) {
                number4Present = true;
            }
        }
        return number1Present && number4Present;
    }
}
