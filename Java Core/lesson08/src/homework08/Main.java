package homework08;

public class Main {

    public static void main(String[] args) {
        Sports[] members = new Sports[5];
        members[0] = new Cat("Барсик", 200, 2);
        members[1] = new Human("Павел", 500, 3.5);
        members[2] = new Human("Олег", 50, 1.3);
        members[3] = new Robot("Verter", 100, 0.5);
        members[4] = new Robot("T1000", 1000, 5);

        Experiencing[] barriers = new Experiencing[2];
        barriers[0] = new Track(100);
        barriers[1] = new Wall(3);

        for (Experiencing barrier : barriers) {
            barrier.printOption();
        }
        System.out.println();

        for (Sports member : members) {
            if (member.run(barriers[0].getOption())) {
                member.jump(barriers[1].getOption());
            }
        }
    }
}
