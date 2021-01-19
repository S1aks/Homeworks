package homework3;

public class Main {

    public static void main(String[] args) {
        System.out.println("Result: " + funcCalc(12.88f, 3.6f, 567.885f, 1.345f));
    }

    static float funcCalc (float a, float b, float c, float d) {
        return a * (b + (c / d));
    }
}
