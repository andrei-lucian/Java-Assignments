package nl.rug.oop.rpg;
import java.util.ArrayList;

public class NPC implements Inspectable, Interactable {
    private String description;
    protected int damage;
    protected int health;
    protected boolean isFriendly;

    public NPC(String description, int damage, int health){
        this.description = description;
        isFriendly = true;
    }

    @Override
    public void inspect() {
        System.out.println(description);
    }

    @Override
    public void interact(Player player) {
        System.out.println("Can you find my soul?");
    }

    public boolean isFriendly() {
        return true;
    }
}