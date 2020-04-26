package nl.rug.oop.rpg;
import java.util.ArrayList;

public class Npc implements Inspectable, Interactable {
    private String description;
    protected int damage;
    protected int health;

    public Npc(String description, int damage, int health){
        this.description = description;
        this.damage = damage;
        this.health = health;
    }

    @Override
    public void inspect() {
        System.out.println(description);
    }

    @Override
    public void interact(Player player) {
        System.out.println("Can you find my soul?");
    }
}