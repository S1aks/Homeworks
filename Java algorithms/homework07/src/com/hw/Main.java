package com.hw;

public class Main {

    public static void main(String[] args) {
        System.out.println("Задание 7.1");
        System.out.println("Приведите пример графа.: ");
        System.out.println();
        System.out.println("Пример графов в жизни: схема железных дорог, созвездия, " +
                "\n схемы авиалиний, генеалогическое древо.");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Задание 7.2");
        System.out.println("Реализуйте базовые методы графа.: ");
        System.out.println();
        System.out.println("Добавление вершин графов и рёбер..");
        Graph graph = new Graph();
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');
        graph.addVertex('F');
        graph.addVertex('H');
        graph.addEdge(0,1);
        graph.addEdge(1,3);
        graph.addEdge(1,2);
        graph.addEdge(2,4);
        graph.addEdge(3,5);
        graph.addEdge(3,6);
        graph.addEdge(4,6);
        System.out.println("Получена система графов:");
        graph.DFS(0);
        graph.clearWasVisited();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Задание 7.3");
        System.out.println("В программный код из задания 7.2 добавьте реализацию метода обхода в глубину.\n" +
                "    Выполните оценку времени с помощью System.nanoTime().: ");
        System.out.println();
        TimeCheck.point();
        graph.DFS(0);
        TimeCheck.check();
        graph.clearWasVisited();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Задание 7.3");
        System.out.println("В базовом графе из задания 7.2 реализуйте метод обхода в ширину.\n" +
                "    Выполните оценку времени с помощью System.nanoTime().: ");
        System.out.println();
        TimeCheck.point();
        graph.BFS(0);
        TimeCheck.check();

    }
}

class Vertex {
    public char label;
    public boolean wasVisited;

    public Vertex(char label) {
        this.label = label;
        wasVisited = false;
    }
}

class Graph {
    private final int MAX_VERTS = 32;
    private Vertex[] vertexList;
    private int[][] adjMat;
    private int size;

    public Graph() {
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        size = 0;
        for (int i = 0; i < MAX_VERTS; i++) {
            for (int j = 0; j < MAX_VERTS; j++) {
                adjMat[i][j] = 0;
            }
        }
    }

    public void addVertex(char label) {
        vertexList[size++] = new Vertex(label);
    }

    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
    }

    private int getAdjUnvisitedVertex(int vertex) {
        for (int i = 0; i < size; i++) {
            if (adjMat[vertex][i] == 1 && !vertexList[i].wasVisited) {
                return i;
            }
        }
        return -1;
    }

    public void DFS(int f) {
        vertexList[f].wasVisited = true;
        for (int i = 0; i < size; i++) {
            int v = getAdjUnvisitedVertex(f);
            if (!vertexList[i].wasVisited && v != -1 && i == v) {
                fullDisplayVertex(f, v);
                DFS(i);
            }
        }
    }

    public void BFS(int v) {
        int[] queue = new int[size];
        int qH = 0;
        int qT = 0;
        vertexList[v].wasVisited = true;
        queue[qT++] = v;
        displayVertex(v);
        int v2;
        while (qH < qT) {
            v = queue[qH++];
            for (int i = 0; i < size; i++) {
                v2 = getAdjUnvisitedVertex(v);
                if (!vertexList[i].wasVisited && v2 != -1 && v2 == i) {
                    vertexList[i].wasVisited = true;
                    displayVertex(qT);
                    queue[qT++] = i;
                }
            }
        }
    }

    public void clearWasVisited() {
        for (int i = 0; i < size; i++) {
            vertexList[i].wasVisited = false;
        }
    }

    private void fullDisplayVertex(int f, int v) {
        System.out.println(vertexList[f].label + " - " + vertexList[v].label);
    }

    public void displayVertex(int vertex) {
        System.out.println(vertexList[vertex].label);
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