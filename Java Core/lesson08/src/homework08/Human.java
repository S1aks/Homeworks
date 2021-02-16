package homework08;

public class Human implements Sports {
    private final String name;
    private final double distanceRun;
    private final double heightJump;

    public Human(String name, double distanceRun, double heightJump) {
        this.name = name;
        this.distanceRun = distanceRun;
        this.heightJump = heightJump;
    }

    @Override
    public boolean run(double distance) {
        if (distanceRun >= distance) {
            System.out.println("Человек " + name + " удачно пробежал дистанцию");
            return true;
        } else {
            System.out.println("Человек " + name + " не смог пробежать дистанцию");
            return false;
        }
    }

    @Override
    public boolean jump(double height) {
        if (heightJump >= height) {
            System.out.println("Человек " + name + " удачно перепрыгнул стену");
            return true;
        } else {
            System.out.println("Человек " + name + " не смог перепрыгнуть стену");
            return false;
        }
    }
}
