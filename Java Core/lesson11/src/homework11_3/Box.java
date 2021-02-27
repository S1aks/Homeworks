package homework11_3;

import java.util.ArrayList;
import java.util.List;

public class Box <T extends Fruit> {
    private final String name;
    private final List<T> boxContent;
    private float weight;

    public Box(String name) {
        this.name = name;
        boxContent = new ArrayList<>();
        weight = 0;
    }

    public void add(T newFruit) {
        boxContent.add(newFruit);
        System.out.println("В короб \"" + name + "\" добавлен" // Ниже операторы для красивого вывода
                + (newFruit.getName().charAt(newFruit.getName().length() - 1) == 'о' ? "о " :
                  (newFruit.getName().charAt(newFruit.getName().length() - 1) == 'а' ? "а " : " ")) // На случай поямления груши
                + newFruit.getName());
    }

    public void getWeight() {
        this.weight = (boxContent.size() > 0 ? boxContent.get(0).getWeight() * boxContent.size() : 0.0f);
        System.out.println("Вес короба \"" + name + "\": " + weight);
    }

    public boolean compare(Box anotherBox) {
        if (Math.abs(weight - anotherBox.weight) < 0.001) {
            System.out.println("Вес короба \"" + name + "\" равен весу короба \"" + anotherBox.name + "\" и равен: "
                    + weight);
            return true;
        } else {
            System.out.println("Вес короба \"" + name + "\" не равен весу короба \"" + anotherBox.name + "\"");
            return false;
        }

    }

    public void intersperseFruitsToBox (Box<T> newBox) {
        newBox.boxContent.addAll(this.boxContent);
        boxContent.clear();
        System.out.println("Короб \"" + name + "\" пересыпали в короб \"" + newBox.name + "\"");
    }
}
