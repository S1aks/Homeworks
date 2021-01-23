package homework02;

import java.util.Arrays;

class HWork1 {
//    Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
//    С помощью цикла и условия заменить 0 на 1, 1 на 0;
    public static int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

    public static void invertArray () {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = 1;
            } else {
                arr[i] = 0;
            }
        }
    }
}

class HWork2 {
//    Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
    public static int[] arr = new int[8];

    public static void fillArray() {
        for (int i = 0; i < 8; i++) {
            arr[i] = i * 3;
        }
    }
}

class HWork3 {
//    Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
    public static int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

    public static void updateArrayElements () {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }
    }
}

class HWork4 {
    static int arrWidth = 5;
    public static int[][] arr = new int[arrWidth][arrWidth];

    public static void fillArrayDiagonal() {
        for (int i = 0; i < arrWidth; i++) {
            for (int j = 0; j < arrWidth; j++) {
                if (i+j == 4 || i == j) {
                    arr[i][j] = 1;
                }
            }
        }
    }

    public static void printSquareArray() {
        for (int i = 0; i < arrWidth; i++) {
                System.out.println(Arrays.toString(arr[i]));
        }
        System.out.println();
    }
}

class HWork5 {
//    Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета)
    public static int[] arr = new int[10];

    public static void fillArray() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
    }

    public static int minNumberInArray () {
        int minNum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < minNum) {
                minNum = arr[i];
            }
        }
        return minNum;
    }

    public static int maxNumberInArray () {
        int maxNum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxNum) {
                maxNum = arr[i];
            }
        }
        return maxNum;
    }
}

class HWork6 {
//    Написать метод, в который передается не пустой одномерный целочисленный массив,
//    метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны.
//    Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true,
//    checkBalance([1, 1, 1, || 2, 1]) → true,
//    граница показана символами ||, эти символы в массив не входят.
    public static int[] arr = {2, 4, 8, 2, 10, 2};

    public static boolean checkBalance(int[] arr) {
        int sumLeft, sumRight;
        sumLeft = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            sumLeft += arr[i];
            sumRight = 0;
            for (int j = i+1; j < arr.length; j++) {
                sumRight += arr[j];
            }
            if (sumLeft == sumRight) {
                return true;
            }
        }
        return false;
    }
}

class HWork7 {
//    Написать метод, которому на вход подается одномерный массив и число n (может быть положительным,
//    или отрицательным), при этом метод должен сместить все элементы массива на n позиций.
//    Элементы смещаются циклично. Для усложнения задачи нельзя пользоваться вспомогательными массивами.
//    Примеры: [ 1, 2, 3 ] при n = 1 (на один вправо) -> [ 3, 1, 2 ];
//    [ 3, 5, 6, 1] при n = -2 (на два влево) -> [ 6, 1, 3, 5 ].
//    При каком n в какую сторону сдвиг можете выбирать сами.
    public static int[] arr = {5, 9, 2, 5, 8, 1, 3};

    public static void shiftArray(int[] arr, int shift) {
        int buffer;
        int counter = 0;
        int lastNumPos = arr.length - 1;
        if (shift > 0) {
            while (counter < shift) {
                buffer = arr[lastNumPos];
                for (int i = lastNumPos; i > 0; i--) {
                    arr[i] = arr[i-1];
                }
                arr[0] = buffer;
                counter++;
            }
        } else if (shift < 0) {
            while (counter > shift) {
                buffer = arr[0];
                for (int i = 0; i < lastNumPos; i++) {
                    arr[i] = arr[i+1];
                }
                arr[lastNumPos] = buffer;
                counter--;
            }
        }
    }
}

public class Main {

    static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        System.out.println("Задание 1:");
        printArray(HWork1.arr);
        HWork1.invertArray();
        printArray(HWork1.arr);

        System.out.println("\nЗадание 2:");
        printArray(HWork2.arr);
        HWork2.fillArray();
        printArray(HWork2.arr);

        System.out.println("\nЗадание 3:");
        printArray(HWork3.arr);
        HWork3.updateArrayElements();
        printArray(HWork3.arr);

        System.out.println("\nЗадание 4:");
        HWork4.printSquareArray();
        HWork4.fillArrayDiagonal();
        HWork4.printSquareArray();

        System.out.println("\nЗадание 5:");
        HWork5.fillArray();
        printArray(HWork5.arr);
        System.out.println("Минимальное число: " + HWork5.minNumberInArray() +
                ", Максимальное число: " + HWork5.maxNumberInArray());

        System.out.println("\nЗадание 6:");
        printArray(HWork6.arr);
        System.out.println("Результат: " + HWork6.checkBalance(HWork6.arr));

        System.out.println("\nЗадание 7:");
        int shift = -2;
        printArray(HWork7.arr);
        HWork7.shiftArray(HWork7.arr, shift);
        System.out.println("Сдвиг: " + shift);
        printArray(HWork7.arr);
    }
}
