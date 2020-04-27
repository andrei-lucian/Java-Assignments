package nl.rug.oop.rpg;
import java.util.ArrayList;

public class NPC implements Inspectable, Interactable {
    protected String description;

    public NPC(String description){
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
}