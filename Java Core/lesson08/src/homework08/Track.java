package homework08;

public class Track implements Experiencing {
    private final double distance;

    public Track(double distance) {
        this.distance = distance;
    }

    @Override
    public double getOption() {
        return distance;
    }

    @Override
    public void printOption() {
        System.out.println("Длина беговой дорожки - " + distance + " м.");
    }
}
