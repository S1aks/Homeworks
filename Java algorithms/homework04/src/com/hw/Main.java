package com.hw;

public class Main {

    public static void main(String[] args) {
        System.out.println("Задание 4.1");
        System.out.println("Реализация простого стека и его базовыех методов: ");
        NumNumbers numNumbers = new NumNumbers(5,20);
        MyStack stack = new MyStack(5);
        for (int arrayNumber : numNumbers.arrayNumbers) {
            TimeCheck.point();
            stack.push(arrayNumber);
            System.out.print("Элемент " + arrayNumber + " добавлен в стэк.");
            TimeCheck.check();
        }
        while (!stack.isEmpty()) {
            TimeCheck.point();
            System.out.print("Элемент " + stack.pop() + " взят из стека.");
            TimeCheck.check();
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
        System.out.println("\t --- Время выполнения: " + (secondPoint - timePoint) + " наносекунд");
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
    private Link<T> prev;
    private Link<T> next;

    public Link(T value) {
        this.value = value;
    }

    public Link<T> getPrev() {
        return prev;
    }

    public Link<T> getNext() {
        return next;
    }

    public void setPrev(Link<T> prev) {
        this.prev = prev;
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

class MyDoubleLinkedList<T> {
    private Link<T> first;
    private Link<T> last;
    private Link<T> current;

    public MyDoubleLinkedList() {
        first = null;
        last = null;
        current = null;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void add(T value) {
        Link<T> link = new Link<>(value);
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

class MyStack {
    private int maxStackSize;
    private int[] stack;
    private int top;

    public MyStack(int maxStackSize) {
        this.maxStackSize = maxStackSize;
        this.stack = new int[maxStackSize];
        this.top = -1;
    }

    public void push (int i) {
        this.stack[++this.top] = i;
    }

    public int pop() {
        return this.stack[this.top--];
    }

    public boolean isEmpty() {
        return this.top == -1;
    }

    public boolean isFull() {
        return this.top == this.maxStackSize - 1;
    }
}