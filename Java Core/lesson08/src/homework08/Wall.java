package homework08;

public class Wall implements Experiencing {
    private final double height;

    public Wall(double height) {
        this.height = height;
    }

    @Override
    public double getOption() {
        return height;
    }

    @Override
    public void printOption() {
        System.out.println("Высота стены - " + height + " м.");
    }
}
