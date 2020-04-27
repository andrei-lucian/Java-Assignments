package nl.rug.oop.rpg;
import java.util.ArrayList;

public class NPC implements Inspectable, Interactable {
    private String description;
    protected int damage;
    protected int health;

    public NPC(String description, int damage, int health){
        this.description = description;
    }

    @Override
    public void inspect() {
        System.out.println(description);
    }

    @Override
    public void interact(Player player) {
        System.out.println("Can you find my soul?");
    }

    public static boolean isFriendly() {
        return true;
    }
}