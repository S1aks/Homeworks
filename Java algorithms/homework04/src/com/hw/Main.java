package com.hw;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Задание 4.1");
        System.out.println("Реализация простого стека и его базовых методов: ");
        NumNumbers numNumbers = new NumNumbers(5,20);
        MyStack stack = new MyStack(5);
        for (int arrayNumber : numNumbers.arrayNumbers) {
            TimeCheck.point();
            stack.push(arrayNumber);
            System.out.printf("%-30s", "Элемент " + arrayNumber + " добавлен в стэк.");
            TimeCheck.check();
        }
        while (!stack.isEmpty()) {
            TimeCheck.point();
            System.out.printf("%-30s", "Элемент " + stack.pop() + " взят из стека.");
            TimeCheck.check();
        }
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Задание 4.2");
        System.out.println("Реализация простой очереди и её базовых методов: ");
        numNumbers = new NumNumbers(5,20);
        MyQueue queue = new MyQueue(5);
        for (int arrayNumber : numNumbers.arrayNumbers) {
            TimeCheck.point();
            queue.insert(arrayNumber);
            System.out.printf("%-34s", "Элемент " + arrayNumber + " добавлен в очередь.");
            TimeCheck.check();
        }
        while (!queue.isEmpty()) {
            TimeCheck.point();
            System.out.printf("%-34s", "Элемент " + queue.remove() + " вышел из очереди.");
            TimeCheck.check();
        }
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Задание 4.3");
        System.out.println("Реализация простого дека и его базовых методов: ");
        Deque<NumNumbers> deque = new ArrayDeque<>(5);
        for (int i = 0; i < 5; i++) {
            TimeCheck.point();
            numNumbers = new NumNumbers(3,10);
            deque.add(numNumbers);
            System.out.printf("%-35s", "Объект " + Arrays.toString(numNumbers.arrayNumbers) + " добавлен в дек.");
            TimeCheck.check();
        }
        boolean pollSwitch = false;
        while (!deque.isEmpty()) {
            TimeCheck.point();
            if (pollSwitch) {
                System.out.printf("%-35s", "Объект " +
                        Arrays.toString(Objects.requireNonNull(deque.pollFirst()).arrayNumbers) + " вышел из дека.");
            } else {
                System.out.printf("%-35s", "Объект " +
                        Arrays.toString(Objects.requireNonNull(deque.pollLast()).arrayNumbers) + " вышел из дека.");
            }
            TimeCheck.check();
            pollSwitch = !pollSwitch;
        }
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Задание 4.4");
        System.out.println("Реализация приоритетной очереди на основе ссылочных типов данных: ");
        numNumbers = new NumNumbers(5,20);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(5);
        for (int arrayNumber : numNumbers.arrayNumbers) {
            TimeCheck.point();
            priorityQueue.add(arrayNumber);
            System.out.printf("%-45s", "Элемент " + arrayNumber + " добавлен в приоритетную очередь.");
            TimeCheck.check();
        }
        while (!priorityQueue.isEmpty()) {
            TimeCheck.point();
            System.out.printf("%-45s", "Элемент " + priorityQueue.poll() + " вышел из приоритетной очереди.");
            TimeCheck.check();
        }
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Задание 4.5");
        System.out.println("Реализация стека и очереди на базе связанного списка: ");
        StackList<NumNumbers> stackList = new StackList<>();
        for (int i = 0; i < 5; i++) {
            TimeCheck.point();
            numNumbers = new NumNumbers(3,10);
            stackList.push(numNumbers);
            System.out.printf("%-35s", "Объект " + Arrays.toString(numNumbers.arrayNumbers) + " добавлен в стек.");
            TimeCheck.check();
        }
        while (!stackList.isEmpty()) {
            TimeCheck.point();
            System.out.printf("%-35s", "Объект " + Arrays.toString(stackList.pop().arrayNumbers) + " взят из стека.");
            TimeCheck.check();
        }
        QueueList<NumNumbers> queueList = new QueueList<>();
        for (int i = 0; i < 5; i++) {
            TimeCheck.point();
            numNumbers = new NumNumbers(3,10);
            queueList.insert(numNumbers);
            System.out.printf("%-38s", "Объект " + Arrays.toString(numNumbers.arrayNumbers) + " добавлен в очередь.");
            TimeCheck.check();
        }
        while (!queueList.isEmpty()) {
            TimeCheck.point();
            System.out.printf("%-38s", "Объект " + Arrays.toString(queueList.remove().arrayNumbers) + " вышел из очереди.");
            TimeCheck.check();
        }
        System.out.println("---------------------------------------------------------------------");
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

class NumNumbers {
    int[] arrayNumbers;

    public NumNumbers(int quantity, int maxNumber) {
        arrayNumbers = new int[quantity];
        for (int i = 0; i < quantity; i++) {
            arrayNumbers[i] = (int) ((Math.random() * maxNumber));
        }
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

    public DLink<T> deleteFirst() {
        DLink<T> temp = first;
        first = first.getNext();
        return temp;
    }

    public DLink<T> deleteLast() {
        DLink<T> temp = last;
        last = last.getPrev();
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

class StackList<T> {
    private final MyDoubleLinkedList<T> list;

    public StackList() {
        list = new MyDoubleLinkedList<>();
    }

    public void push(T object) {
        list.add(object);
    }

    public T pop() {
        return list.deleteLast().getValue();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void display() {
        list.display();
    }
}

class QueueList<T> {
    private final MyDoubleLinkedList<T> list;

    public QueueList() {
        list = new MyDoubleLinkedList<>();
    }

    public void insert(T object) {
        list.add(object);
    }

    public T remove() {
        return list.deleteFirst().getValue();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void display() {
        list.display();
    }
}

class MyStack {
    private final int maxStackSize;
    private final int[] stack;
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

class MyQueue {
    private final int maxQueueSize;
    private final int[] queue;
    private int front;
    private int rear;
    private int size;

    public MyQueue(int maxQueueSize) {
        this.maxQueueSize = maxQueueSize;
        queue = new int[maxQueueSize];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void insert(int item) {
        if (rear == maxQueueSize - 1) {
            rear = -1;
        }
        queue[++rear] = item;
        size++;
    }

    public int remove() {
        int temp = queue[front++];
        if (front == maxQueueSize) {
            front = 0;
        }
        size--;
        return temp;
    }

    public int peek() {
        return queue[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == maxQueueSize;
    }

    public int size() {
        return size;
    }
}