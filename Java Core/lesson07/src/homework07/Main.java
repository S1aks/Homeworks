package homework07;

public class Main {

    public static void main(String[] args) {
        Cat[] cat = new Cat[5];
        cat[0] = new Cat("Barsik", 30);
        cat[1] = new Cat("Semion", 15);
        cat[2] = new Cat("Tisha", 25);
        cat[3] = new Cat("Makar", 25);
        cat[4] = new Cat("Murzik", 20);
        Plate plate = new Plate(100);
        plate.info();
        for (Cat cat1 : cat) {
            cat1.eat(plate);
            plate.info();
        }
        plate.fillPlate(80);
    }
}
