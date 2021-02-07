package homework06;

public class Animal {
    protected String name;
    protected int maxRunDistance;
    protected int maxSwimDistance;

    public Animal(String name, int maxRunDistance, int maxSwimDistance) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxSwimDistance = maxSwimDistance;
    }

    public void run (int Distance) {
        if (Distance > 0) {
            if (Distance <= maxRunDistance) {
                System.out.println(name + " пробежал " + Distance + "м.");
            } else {
                System.out.println(name + " пробежал " + maxRunDistance + "м. из " + Distance + " и устал.");
            }
        } else {
            System.out.println(name + " не бежал.");
        }
    }

    public void swim (int Distance) {
        if (Distance > 0) {
            if (Distance <= maxSwimDistance) {
                System.out.println(name + " проплыл " + Distance + "м.");
            } else {
                System.out.println(name + " проплыл " + maxSwimDistance + "м. из " + Distance + " и устал. Подобрали спасатели.");
            }
        } else {
            System.out.println(name + " не плыл.");
        }
    }
}
