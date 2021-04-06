package com.hw;

public class Main {

    public static void main(String[] args) {
        System.out.println("Задание 8.1");
        System.out.println("Приведите пример использование хеш-таблиц.: ");
        System.out.println();
        System.out.println("Хэш-таблицы структура для хранения ассоциативных массивов. Пример - словарь иностранного языка.");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Задание 8.2");
        System.out.println("Приведите примеры ключей и коллизий.: ");
        System.out.println();
        System.out.println("Ключ - это индекс по которому производится поиск элемента. Коллизия - это когда несколько элементов\n" +
                " могут иметь одинаковые ключи. Пример коллизии - когда одному иностранному слову могут соответствовать\n" +
                " несколько значений.");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Задание 8.3");
        System.out.println("Приведите примеры популярных и эффективных хеш-функций.: ");
        System.out.println();
        System.out.println("Популярные хэш-функции: CRC32.\nЭффективные хэш-функции: MD5, SHA-2.");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Задание 8.4");
        System.out.println("На основе данных массива из задания 2.3 реализуйте хеш-таблицу с помощью открытой адресации,\n" +
                " а конкретнее метода линейного пробирования.: ");
        System.out.println();
        System.out.println("Добавление элементов в хэш-таблицу.");
        NumNumbers numNumbers = new NumNumbers("Числа", 400, 100);
        HashTable hashTable = new HashTable(800);
        TimeCheck.point();
        for (int i = 0; i < 400; i++) {
            hashTable.insert(new Item(numNumbers.arrayNumbers[i]));
        }
        TimeCheck.check();
        System.out.println("Вывод элементов хэш-таблицы:");
        TimeCheck.point();
        hashTable.display();
        TimeCheck.check();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Задание 8.5");
        System.out.println("Перестройте программный код задания 8.4 из алгоритма линейного пробирования в алгоритм\n" +
                "двойного хеширования. Сравните отличительные черты двух алгоритмов.: ");
        System.out.println();
        System.out.println("Добавление элементов в хэш-таблицу.");
        hashTable.clear();
        hashTable = new HashTable(800);
        TimeCheck.point();
        for (int i = 0; i < 400; i++) {
            hashTable.insertDoubleHashed(new Item(numNumbers.arrayNumbers[i]));
        }
        TimeCheck.check();
        System.out.println("Вывод элементов хэш-таблицы:");
        TimeCheck.point();
        hashTable.display();
        TimeCheck.check();
        System.out.println();
        System.out.println("Двойное хэширование производится медленнее, но при этом случается меньше коллизий.");
        System.out.println("---------------------------------------------------------------------");

    }
}

class Item {
    private final int data;

    public Item(int data) {
        this.data = data;
    }

    public int getKey() {
        return this.data;
    }
}

class HashTable {
    private final Item[] hashArr;
    private final int arrSize;
    private final Item nonItem;

    public HashTable(int size) {
        this.arrSize = size;
        hashArr = new Item[arrSize];
        nonItem = new Item(-1);
    }

    public void clear() {
        for (int i = 0; i < arrSize; i++) {
            hashArr[i] = null;
        }
    }

    public void display() {
        int counterPrintInLine = 0;
        for (int i = 0; i < arrSize; i++) {
            if (hashArr[i] != null) {
                System.out.print(hashArr[i].getKey() + "\t");
            } else {
                System.out.print("***\t");
            }
            if (counterPrintInLine++ == 20) {
                System.out.println();
                counterPrintInLine = 0;
            }
        }
        System.out.println();
    }

    public int hashFunc(int key) {
        return key % arrSize;
    }

    public int hashFuncDouble(int key) {
        return 5 - key % 5;
    }

    public void insert(Item item) {
        int key = item.getKey();
        int hashVal = hashFunc(key);
        while (hashArr[hashVal] != null && hashArr[hashVal].getKey() != -1) {
            ++hashVal;
            hashVal %= arrSize;
        }
        hashArr[hashVal] = item;
    }

    public void insertDoubleHashed(Item item) {
        int key = item.getKey();
        int hashVal = hashFunc(key);
        int stepSize = hashFuncDouble(key);
        while (hashArr[hashVal] != null && hashArr[hashVal].getKey() != -1) {
            hashVal += stepSize;
            hashVal %= arrSize;
        }
        hashArr[hashVal] = item;
    }

    public Item delete(int key) {
        int hashVal = hashFunc(key);
        while (hashArr[hashVal] != null) {
            if (hashArr[hashVal].getKey() == key) {
                Item temp = hashArr[hashVal];
                hashArr[hashVal] = nonItem;
                return temp;
            }
            ++hashVal;
            hashVal %= arrSize;
        }
        return nonItem;
    }

    public Item deleteDoubleHashed(int key) {
        int hashVal = hashFunc(key);
        int stepSize = hashFuncDouble(key);
        while (hashArr[hashVal] != null) {
            if (hashArr[hashVal].getKey() == key) {
                Item temp = hashArr[hashVal];
                hashArr[hashVal] = nonItem;
                return temp;
            }
            hashVal += stepSize;
            hashVal %= arrSize;
        }
        return nonItem;
    }

    public Item find(int key) {
        int hashVal = hashFunc(key);
        while (hashArr[hashVal] != null) {
            if (hashArr[hashVal].getKey() == key) {
                return hashArr[hashVal];
            }
            ++hashVal;
            hashVal %= arrSize;
        }
        return nonItem;
    }

    public Item findDoubleHashed(int key) {
        int hashVal = hashFunc(key);
        int stepSize = hashFuncDouble(key);
        while (hashArr[hashVal] != null) {
            if (hashArr[hashVal].getKey() == key) {
                return hashArr[hashVal];
            }
            hashVal += stepSize;
            hashVal %= arrSize;
        }
        return nonItem;
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