package nl.rug.oop.introduction;
import java.util.*;

public class Main {

    private static Animal[] initAnimals() {

        Animal dog = new Animal("Dog", 8);
        Animal ant = new Animal("Ant", 2);
        Animal lion = new Animal("Mufasa", 600);

        Animal[] animals = {dog, ant, lion};
        return animals;
    }

    private static Animal[] initAnimals(String[] args) {
        Animal[] animals = new Animal[args.length];
        for (int i = 0; i < args.length; i++) {
            String name = args[i];
            animals[i] = new Animal(name);
        }
        return animals;
    }

    public static void main(String[] args) {

        Scanner bob = new Scanner(System.in);

        Animal[] animals;

        if (args.length == 0) {
            animals = initAnimals();
        } else {
            animals = initAnimals(args);
        }

        while (bob.hasNextLine()) {

            String line = bob.nextLine();

            for (Animal animal : animals) {
                if (line.equals("eat")) {
                    animal.eat();
                } else if (line.equals("run")) {
                    animal.run();
                }
                animal.printHungerLevel();
            }
        }
    }
}



