package homework06;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        int catCount;
        int dogCount;
        String name;
        int maxRunDistance;
        int maxSwimDistance;
        System.out.print("Введите количество кошек: ");
        catCount = Integer.parseInt(scanner.nextLine());
        Cat[] cat = new Cat[catCount];
        System.out.print("Введите количество собак: ");
        dogCount = Integer.parseInt(scanner.nextLine());
        System.out.println();
        Dog[] dog = new Dog[dogCount];
        for (int i = 0; i < catCount; i++) {
            System.out.print("Введите имя " + (i + 1) + "-го кота: ");
            name = scanner.nextLine();
            System.out.print("Сколько метров он может пробежать? ");
            maxRunDistance = Integer.parseInt(scanner.nextLine());
            System.out.print("Сколько метров он может проплыть? ");
            maxSwimDistance = Integer.parseInt(scanner.nextLine());
            cat[i] = new Cat(name, maxRunDistance, maxSwimDistance);
        }
        System.out.println();
        for (int i = 0; i < dogCount; i++) {
            System.out.print("Введите имя " + (i + 1) + "-й собаки: ");
            name = scanner.nextLine();
            System.out.print("Сколько метров он может пробежать? ");
            maxRunDistance = Integer.parseInt(scanner.nextLine());
            System.out.print("Сколько метров он может проплыть? ");
            maxSwimDistance = Integer.parseInt(scanner.nextLine());
            dog[i] = new Dog(name, maxRunDistance, maxSwimDistance);
        }
        System.out.println();
        System.out.print("Введите длину дистанции забега: ");
        maxRunDistance = Integer.parseInt(scanner.nextLine());
        System.out.print("Введите длину дистанции заплыва: ");
        maxSwimDistance = Integer.parseInt(scanner.nextLine());
        System.out.println();
        System.out.println("Забег:");
        for (Cat cat1 : cat) {
            System.out.print("Кот ");
            cat1.run(maxRunDistance);
        }
        for (Dog dog1 : dog) {
            System.out.print("Собака ");
            dog1.run(maxRunDistance);
        }
        System.out.println();
        System.out.println("Заплыв:");
        for (Cat cat1 : cat) {
            System.out.print("Кот ");
            cat1.swim(maxSwimDistance);
        }
        for (Dog dog1 : dog) {
            System.out.print("Собака ");
            dog1.swim(maxSwimDistance);
        }
        scanner.close();
    }
}
