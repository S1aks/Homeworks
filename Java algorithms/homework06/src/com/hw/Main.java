package com.hw;

public class Main {
    public static void main(String[] args) {

       MyTreeSet myTree = new MyTreeSet();

        myTree.addNewEmployee(new Personal(123, "Дима", "Продавец"));
        myTree.addNewEmployee(new Personal(345, "Саша", "Продавец"));
        myTree.addNewEmployee(new Personal(567, "Коля", "Кассир"));
        myTree.addNewEmployee(new Personal(789, "Лена", "Кассир"));
        myTree.addNewEmployee(new Personal(910, "Света", "Заместитель Директора"));
        myTree.addNewEmployee(new Personal(911, "Леша", "Заместитель Директора"));
        myTree.addNewEmployee(new Personal(912, "Марина", "Директор"));

        myTree.maxValue().show();
        myTree.minValue().show();

        myTree.findByKeyId(789).show();

    }

    static class Personal {
        int id;
        String nameEmployee;
        String positionEmployee;

        Personal(int id, String nameEmployee, String positionEmployee) {
            this.id = id;
            this.nameEmployee = nameEmployee;
            this.positionEmployee = positionEmployee;
        }
    }

    static class Node {
        Personal personal;
        Node leftLink;
        Node rightLink;

        public void show() {
            System.out.printf("Имя: %s, Должность: %s, Универсальный ID: %s\n", personal.nameEmployee, personal.positionEmployee, personal.id);
        }
    }

    static class MyTreeSet {

        private Node rootLink;

        public void addNewEmployee(Personal personal) {
            Node node = new Node();
            node.personal = personal;
            if (rootLink == null) {
                rootLink = node;
            } else {
                Node currentLink = rootLink;
                Node parent;
                while (true) {
                    parent = currentLink;
                    if (personal.id < currentLink.personal.id) {
                        currentLink = currentLink.leftLink;
                        if (currentLink == null) {
                            parent.leftLink = node;
                            return;
                        }
                    } else {
                        currentLink = currentLink.rightLink;
                        if (currentLink == null) {
                            parent.rightLink = node;
                            return;
                        }
                    }
                }
            }
        }

        public Node findByKeyId(int keyID) {
            Node currentLink = rootLink;
            while (currentLink.personal.id != keyID) {
                if (keyID < currentLink.personal.id) {
                    currentLink = currentLink.leftLink;
                } else {
                    currentLink = currentLink.rightLink;
                }
                if (currentLink == null) {
                    return null;
                }
            }
            return currentLink;
        }

        public Node minValue() {
            Node currentLink = rootLink;
            Node last = null;
            while (currentLink != null) {
                last = currentLink;
                currentLink = currentLink.leftLink;
            }
            return last;
        }

        public Node maxValue() {
            Node currentLink = rootLink;
            Node last = null;
            while (currentLink != null) {
                last = currentLink;
                currentLink = currentLink.rightLink;
            }
            return last;
        }
    }
}

