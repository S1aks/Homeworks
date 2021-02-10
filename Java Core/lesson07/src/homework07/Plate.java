package homework07;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public boolean decreaseFood(int n) {
        if (food - n >= 0) {
            food -= n;
            return true;
        }
        return false;
    }

    public void fillPlate (int food) {
        this.food += food;
        System.out.println("Food added in plate.");
        info();
    }

    public void info() {
        System.out.println("Plate: " + food);
    }
}