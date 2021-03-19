package com.hw;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Задание 3.1");
        System.out.println("Реализация простого списка и коллекции из массива: ");
        NumNumbers numNumbers = new NumNumbers(20, 20);
        System.out.println("Массив: \t" + Arrays.toString(numNumbers.arrayNumbers));
        long startTime = System.nanoTime();
        List<Integer> linkedList = new LinkedList<>();
        for (int arrayNumber : numNumbers.arrayNumbers) {
            linkedList.add(arrayNumber);
        }
        long endTime = System.nanoTime();
        System.out.println("LinkedList:\t" + linkedList.toString() + " --> Наносекунд затрачено: " + (endTime - startTime));
        startTime = System.nanoTime();
        List<Integer> arrayList = new ArrayList<>();
        for (int arrayNumber : numNumbers.arrayNumbers) {
            arrayList.add(arrayNumber);
        }
        endTime = System.nanoTime();
        System.out.println("ArrayList:\t" + arrayList.toString() + " --> Наносекунд затрачено: " + (endTime - startTime));
        System.out.println("------------------------------------------------------------------");

        System.out.println("Задание 3.2");
        System.out.println("Реализация основных методов добавления, удаления и получения объекта или элемента из списка:");
        List<Integer> linkedListCopy = new LinkedList<>(linkedList);
        List<Integer> arrayListCopy = new ArrayList<>(arrayList);
        System.out.println("Массив: " + linkedListCopy.toString());
        System.out.println("\nДобавлен элемент 99 в позицию 5:");
        startTime = System.nanoTime();
        linkedListCopy.add(5, 99);
        endTime = System.nanoTime();
        System.out.println("LinkedList --> Наносекунд затрачено: " + (endTime - startTime));
        startTime = System.nanoTime();
        arrayListCopy.add(5, 99);
        endTime = System.nanoTime();
        System.out.println("ArrayList --> Наносекунд затрачено: " + (endTime - startTime));
        System.out.println("Массив: " + linkedListCopy.toString());
        System.out.println("\nУдалён элемент в позиции 15:");
        startTime = System.nanoTime();
        linkedListCopy.remove(15);
        endTime = System.nanoTime();
        System.out.println("LinkedList --> Наносекунд затрачено: " + (endTime - startTime));
        startTime = System.nanoTime();
        arrayListCopy.remove(15);
        endTime = System.nanoTime();
        System.out.println("ArrayList --> Наносекунд затрачено: " + (endTime - startTime));
        System.out.println("Массив: " + linkedListCopy.toString());
        startTime = System.nanoTime();
        int number = linkedListCopy.get(8);
        endTime = System.nanoTime();
        System.out.println("\nПолучен элемент " + number + " из позиции 8:");
        System.out.println("LinkedList --> Наносекунд затрачено: " + (endTime - startTime));
        startTime = System.nanoTime();
        number = arrayListCopy.get(8);
        endTime = System.nanoTime();
        System.out.println("ArrayList --> Наносекунд затрачено: " + (endTime - startTime));
        System.out.println("Массив: " + linkedListCopy.toString());
        System.out.println("------------------------------------------------------------------");

        System.out.println("Задание 3.3");
        System.out.println("Реализация простого односвязного списка и его базовых методов.:");
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        System.out.println("Массив: \t" + Arrays.toString(numNumbers.arrayNumbers));
        for (int arrayNumber : numNumbers.arrayNumbers) {
            myLinkedList.add(arrayNumber);
        }
        System.out.print("Вывод: ");
        myLinkedList.display();
        System.out.println();
        System.out.println("------------------------------------------------------------------");

        System.out.println("Задание 3.4");
        System.out.println("Реализация простого двустороннего списка и его базовых методов:");
        MyDoubleLinkedList<Integer> myDoubleLinkedList = new MyDoubleLinkedList<>();
        System.out.println("Массив: \t" + Arrays.toString(numNumbers.arrayNumbers));
        for (int arrayNumber : numNumbers.arrayNumbers) {
            myDoubleLinkedList.add(arrayNumber);
        }
        System.out.print("Вывод: ");
        myDoubleLinkedList.display();
        System.out.println();
        System.out.println("------------------------------------------------------------------");

        System.out.println("Задание 3.5");
        System.out.println("Реализация итераторов на основе связанных списков из задания 3.4:");

        System.out.println("Массив: \t" + Arrays.toString(numNumbers.arrayNumbers));
        System.out.print("Вывод: ");
        startTime = System.nanoTime();
        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        endTime = System.nanoTime();
        System.out.println(" --> Наносекунд затрачено: " + (endTime - startTime));
        System.out.println("------------------------------------------------------------------");
    }
}

class NumNumbers {
    int[] arrayNumbers;

    public NumNumbers(int quantity, int maxNumber) {
        arrayNumbers = new int[quantity];
        for (int i = 0; i < quantity; i++) {
            arrayNumbers[i] = (int) ((Math.random() * maxNumber));
        }
    }
}

class Link<T> {
    private T value;
    private Link<T> next;

    public Link(T value) {
        this.value = value;
    }

    public Link<T> getNext() {
        return next;
    }

    public void setNext(Link<T> next) {
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}

class MyLinkedList<T> {
    private Link<T> first;
    private Link<T> current;

    public MyLinkedList() {
        first = null;
        current = null;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void add(T value) {
        Link<T> link = new Link<>(value);
        link.setNext(null);
        if (isEmpty()) {
            first = link;
        } else {
            current.setNext(link);
        }
        current = link;
    }

    public Link<T> delete() {
        Link<T> temp = first;
        first = first.getNext();
        return temp;
    }

    public void display() {
        Link<T> current = first;
        while (current != null) {
            System.out.print(current.getValue() + " ");
            current = current.getNext();
        }
    }

    public T find(T searchNode) {
        Link<T> findNode = new Link<>(searchNode);
        Link<T> current = first;
        while (current != null) {
            if (current.getValue().equals(findNode.getValue())) {
                return findNode.getValue();
            }
            current = current.getNext();
        }
        return null;
    }
}

class DLink<T> {
    private T value;
    private DLink<T> prev;
    private DLink<T> next;

    public DLink(T value) {
        this.value = value;
    }

    public DLink<T> getPrev() {
        return prev;
    }

    public DLink<T> getNext() {
        return next;
    }

    public void setPrev(DLink<T> prev) {
        this.prev = prev;
    }

    public void setNext(DLink<T> next) {
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}

class MyDoubleLinkedList<T> {
    private DLink<T> first;
    private DLink<T> last;
    private DLink<T> current;

    public MyDoubleLinkedList() {
        first = null;
        last = null;
        current = null;
    }

    public boolean isEmpty() {
        return (first == null || last == null);
    }

    public void add(T value) {
        DLink<T> link = new DLink<>(value);
        link.setNext(null);
        link.setPrev(current);
        if (isEmpty()) {
            first = link;
        } else {
            current.setNext(link);
        }
        current = link;
        last = link;
    }

    public DLink<T> delete() {
        DLink<T> temp = first;
        first = first.getNext();
        return temp;
    }

    public void display() {
        DLink<T> current = first;
        while (current != null) {
            System.out.print(current.getValue() + " ");
            current = current.getNext();
        }
    }

    public T find(T searchNode) {
        DLink<T> findNode = new DLink<>(searchNode);
        DLink<T> current = first;
        while (current != null) {
            if (current.getValue().equals(findNode.getValue())) {
                return findNode.getValue();
            }
            current = current.getNext();
        }
        return null;
    }
}
