package com.hw;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("Задание 6.1");
        System.out.println("Приведите пример использования древовидной структуры.: ");
        System.out.println();
        System.out.println("Примером древовидной структуры являются: генеалогическое древо, \n" +
                "биологическое древо видов, файловая система на жестком диске.");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Задания 6.2 - 6.5");
        System.out.println("Реализация класса узла дерева и дерева с базовыми методами. А так же оценка времени" +
                "\n работы базовых методов дерева с помощью System.nanoTime(): ");
        System.out.println();
        MyTree tree = new MyTree();
        for (int i = 0; i < 10; i++) {
            Person person = new Person((int) (Math.random() * 20), "Person #" + i);
            System.out.print("Добавление Person #" + i);
            TimeCheck.point();
            tree.insert(person);
            TimeCheck.check();
        }
        System.out.println();
        System.out.print("Поиск Person #" + 8);
        TimeCheck.point();
        tree.findNode("Person #8");
        TimeCheck.check();
        System.out.println();
        System.out.print("Минимальный Id: ");
        TimeCheck.point();
        System.out.print(tree.minIdNode().person.id);
        TimeCheck.check();
        System.out.println();
        System.out.print("Максимальный Id: ");
        TimeCheck.point();
        System.out.print(tree.maxIdNode().person.id);
        TimeCheck.check();
        System.out.println();
        System.out.println("Печать дерева:");
        TimeCheck.point();
        tree.inOrder(tree.getRootNode());
        System.out.print("Печать дерева закончена.");
        TimeCheck.check();
        System.out.println();
        int numNode = 9;
        while (tree.findNode(numNode) == null) {
            numNode++;
        }
        System.out.print("Удаление узла с Id = " + numNode);
        TimeCheck.point();
        tree.delete(numNode);
        TimeCheck.check();
        System.out.println("Результат:");
        tree.inOrder(tree.getRootNode());
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Задание 6.6");
        System.out.println("Реализация на основе массива из задания 2.1 алгоритма пирамидальной " +
                "\n сортировки с реализацией бинарной пирамиды: ");
        System.out.println();
        int[] array = {11, 17, 7, 6, 15, 14, 14, 14, 0, 14, 6, 19, 4, 2, 13, 1, 11, 11, 3, 12};
        System.out.println("Начальный массив:");
        System.out.println(Arrays.toString(array));
        System.out.print("Сортировка..");
        TimeCheck.point();
        HeapSort.sort(array);
        TimeCheck.check();
        System.out.println("Конечный массив:");
        System.out.println(Arrays.toString(array));
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Задание 6.7");
        System.out.println("Приведите пример сбалансированного дерева и его применения: ");
        System.out.println();
        System.out.println("Дерево является балансированным в следующем смысле: для любого узла дерева высота его правого поддерева\n" +
                "    отличается от высоты левого поддерева не более чем на единицу.\n" +
                "Самобалансирующиеся двоичные деревья поиска — неплохая структура данных для реализации ассоциативных массивов,\n" +
                "    очередей с приоритетами, и, в первую очередь, множеств, где важно упорядочение элементов.\n" +
                "Главная сильная черта деревьев поиска — гибкость в плане области применения, т.к. нельзя сказать,\n" +
                "    что для любой задачи деревья поиска обеспечат лучшую производительность, чем специализированные структуры.");
    }
}

class Person {
    int id;
    String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class Node {
    Person person;
    Node leftChild;
    Node rightChild;

    public void display() {
        System.out.println("ID: " + person.id + ", Name: " + person.name);
    }
}

class MyTree {
    private Node rootNode;

    public Node getRootNode() {
        return rootNode;
    }

    public void insert(Person person) {
        Node node = new Node();
        node.person = person;
        if (rootNode == null) {
            rootNode = node;
        } else {
            Node currentNode = rootNode;
            Node parent;
            while (true) {
                parent = currentNode;
                if (person.id < currentNode.person.id) {
                    currentNode = currentNode.leftChild;
                    if (currentNode == null) {
                        parent.leftChild = node;
                        return;
                    }
                } else {
                    currentNode = currentNode.rightChild;
                    if (currentNode == null) {
                        parent.rightChild = node;
                        return;
                    }
                }
            }
        }
    }

    public Node findNode(int id) {
        Node currentNode = rootNode;
        while (currentNode.person.id != id) {
            if (id < currentNode.person.id) {
                currentNode = currentNode.leftChild;
            } else {
                currentNode = currentNode.rightChild;
            }
            if (currentNode == null) {
                return null;
            }
        }
        return currentNode;
    }

    public Node findParentNode(int id) {
        Node currentNode = rootNode;
        Node parentNode = null;
        while (currentNode.person.id != id) {
            if (id < currentNode.person.id) {
                parentNode = currentNode;
                currentNode = currentNode.leftChild;
            } else {
                parentNode = currentNode;
                currentNode = currentNode.rightChild;
            }
            if (currentNode == null) {
                return null;
            }
        }
        return parentNode;
    }

    public Node findNode(String name) {
        return findNode(rootNode, name);
    }

    public Node findNode(Node node, String name) {
        if (node == null) {
            return null;
        } else if (node.person.name.equals(name)) {
            return node;
        } else {
            Node nextFindNode = findNode(node.leftChild, name);
            if (nextFindNode != null) {
                return nextFindNode;
            }
            nextFindNode = findNode(node.rightChild, name);
            return nextFindNode;
        }
    }

    public Node minIdNode() {
        return minIdNode(rootNode);
    }

    public Node minIdNode(Node node) {
        if (node == null) {
            return null;
        } else if (node.leftChild == null) {
            return node;
        }
        return minIdNode(node.leftChild);
    }

    public Node maxIdNode() {
        return maxIdNode(rootNode);
    }

    public Node maxIdNode(Node node) {
        if (node == null) {
            return null;
        } else if (node.rightChild == null) {
            return node;
        }
        return minIdNode(node.rightChild);
    }

    public void parentNodeChangeLink(Node node, Node newLink) {
        Node parentNode = findParentNode(node.person.id);
        if (parentNode.leftChild == node) {
            parentNode.leftChild = newLink;
        } else {
            parentNode.rightChild = newLink;
        }
    }

    public boolean delete(int id) {
        Node deletedNode = findNode(id);
        if (deletedNode == null) {
            return false;
        } else if (deletedNode == rootNode) {
            rootNode = null;
            return true;
        } else if (deletedNode.rightChild == null) {
            if (deletedNode.leftChild == null) {
                parentNodeChangeLink(deletedNode, null);
            } else {
                parentNodeChangeLink(deletedNode, deletedNode.leftChild);
            }
            return true;
        } else {
            if (deletedNode.leftChild == null) {
                parentNodeChangeLink(deletedNode, deletedNode.rightChild);
            } else {
                Node lastLeftNode = minIdNode(deletedNode.rightChild);
                parentNodeChangeLink(lastLeftNode, null);
                parentNodeChangeLink(deletedNode, lastLeftNode);
                lastLeftNode.leftChild = deletedNode.leftChild;
                maxIdNode(lastLeftNode).rightChild = deletedNode.rightChild;
            }
            return true;
        }
    }

    public boolean delete(String name) {
        return delete(findNode(name).person.id);
    }

    public void inOrder(Node node) {
        if (node != null) {
            inOrder(node.leftChild);
            node.display();
            inOrder(node.rightChild);
        }
    }

    public void preOrder(Node node) {
        if (node != null) {
            node.display();
            preOrder(node.leftChild);
            preOrder(node.rightChild);
        }
    }

    public void postOrder(Node node) {
        if (node != null) {
            postOrder(node.leftChild);
            postOrder(node.rightChild);
            node.display();
        }
    }

    public void displayTree() {
        System.out.println("Симметричная: ");
        inOrder(rootNode);
        System.out.println();
        System.out.println("Прямая: ");
        preOrder(rootNode);
        System.out.println();
        System.out.println("Обратная: ");
        postOrder(rootNode);
        System.out.println();
    }
}

class HeapSort {
    private static int heapSize;

    public static void sort(int[] a) {
        buildHeap(a);
        while (heapSize > 1) {
            swap(a, 0, heapSize - 1);
            heapSize--;
            heapify(a, 0);
        }
    }

    private static void buildHeap(int[] a) {
        heapSize = a.length;
        for (int i = a.length / 2; i >= 0; i--) {
            heapify(a, i);
        }
    }

    private static void heapify(int[] a, int i) {
        int l = left(i);
        int r = right(i);
        int largest = i;
        if (l < heapSize && a[i] < a[l]) {
            largest = l;
        }
        if (r < heapSize && a[largest] < a[r]) {
            largest = r;
        }
        if (i != largest) {
            swap(a, i, largest);
            heapify(a, largest);
        }
    }

    private static int left(int i) {
        return 2 * i + 1;
    }

    private static int right(int i) {
        return 2 * i + 2;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
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
