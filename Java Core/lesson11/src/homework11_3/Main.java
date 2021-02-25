package homework11_3;

public class Main {

    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>("Яблоки1");
        Box<Orange> orangeBox = new Box<>("Апельсины1");
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.getWeight();
        orangeBox.add(new Orange());
        orangeBox.add(new Orange());
        orangeBox.getWeight();
        System.out.println(appleBox.compare(orangeBox));
        Box<Orange> nextOrangeBox = new Box<>("Апельсины2");
        nextOrangeBox.add(new Orange());
        orangeBox.intersperseFruitsToBox(nextOrangeBox);
        nextOrangeBox.getWeight();
        nextOrangeBox.compare(appleBox);
    }
}
