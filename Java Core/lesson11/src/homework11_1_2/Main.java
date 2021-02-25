package homework11_1_2;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Integer Array:");
        Integer[] arrayInt = {1, 5, 8, 3, 6, 2, 1, 0, 4};
        System.out.println(Arrays.toString(arrayInt));
        if (swapArrayElements(arrayInt,3,7)) {
            System.out.println(Arrays.toString(arrayInt));
        } else {
            System.err.println("Неверные данные для замены. Проверьте наличие элементов " +
                    "массива и верных индексов заменяемых элементов");
        }
        System.out.println("\nString Array:");
        String[] arrayString = {"Look", "Team", "Run", "Sun", "Question", "Cool", "Line", "Final", "Dump"};
        System.out.println(Arrays.toString(arrayString));
        if (swapArrayElements(arrayString,2,6)) {
            System.out.println(Arrays.toString(arrayString));
        } else {
            System.err.println("Неверные данные для замены. Проверьте наличие элементов " +
                    "массива и верных индексов заменяемых элементов");
        }
        System.out.println("\nПреобразование массивов в ArrayList и вывод в консоль:");
        List<Integer> listInt = convertToArrayList(arrayInt);
        for (Integer integer : listInt) {
            System.out.print(integer + ", ");
        }
        System.out.print("\b\b\n");
        List<String> listString = convertToArrayList(arrayString);
        for (String string : listString) {
            System.out.print(string + ", ");
        }
        System.out.println("\b\b");
    }

    public static <T> boolean swapArrayElements(T[] array, int firstElementIndex, int secondElementIndex) {
        if (firstElementIndex < 0 || firstElementIndex >= array.length ||
                secondElementIndex <0 || secondElementIndex >= array.length) {
            return false;
        }
        T buffer = array[firstElementIndex];
        array[firstElementIndex] = array[secondElementIndex];
        array[secondElementIndex] = buffer;
        return true;
    }

    @Contract("_ -> new") // Очень интересная вещица. Контракт на создание нового обьекта и передача ссылки на него. Так?
    public static <T> ArrayList<T> convertToArrayList (T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }
}
