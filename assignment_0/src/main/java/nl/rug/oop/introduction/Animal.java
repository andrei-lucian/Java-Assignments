package nl.rug.oop.introduction;

public class Animal {

    private String name;
    private int energy;

    public Animal(String name, int energy){
        this.name = name;
        this.energy = energy;
    }

    public Animal(String name) {
        this(name, 5);
    }

    public void run() {
        if (energy > 0) {
            System.out.println(name + ": Running!");
            energy--;
        } else {
            System.out.println(name + ": Out of energy!");
        }
    }

    public void eat() {
        System.out.println(name + ": Eating!");
        energy++;
    }

    public void printHungerLevel() {
        System.out.println(name + " energy level: " + energy);
    }

}
