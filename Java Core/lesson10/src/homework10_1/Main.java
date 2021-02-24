package homework10_1;

import java.util.*;

public class Main {
//      Использовал ArrayList, LinkedHashSet и LinkedHashMap чтобы сохранить наглядно порядок слов
    public static void main(String[] args) {
        List<String> arrayList = new ArrayList<>(Arrays.asList("Весна", "Дерево", "Лес", "Енот", "Лопата",
                "Лес", "Гнездо", "Трава", "Цветок", "Лес", "Белка", "Дерево", "Трава", "Гнездо", "Листья",
                "Цветок", "Поляна", "Лес", "Кора", "Олень"));
        System.out.println("Первоначальный массив:");
        printList(arrayList);
        Set<String> clearedArrayList = new LinkedHashSet<>(arrayList);
        System.out.println("Массив уникальных слов:");
        printList(new ArrayList<>(clearedArrayList));
        Map<String,Integer> mapList = new LinkedHashMap<>();
        for (String stringOfClearedArray : clearedArrayList) {
            int counter = 0;
            for (String stringOfArray : arrayList) {
                if (stringOfArray.equals(stringOfClearedArray)) {
                    counter++;
                }
            }
            mapList.put(stringOfClearedArray,counter);
        }
        System.out.println("Массив уникальных слов со счетчиками:");
        printMap(mapList);
    }

    public static void printList(List<String> list) {
        for (String stringOfList : list) {
            System.out.print(stringOfList + ", ");
        }
        System.out.println("\b\b\n");
    }

    public static void printMap(Map<String,Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.print(entry.getKey() + " = " + entry.getValue() + ", ");
        }
        System.out.println("\b\b\n");
    }
}
