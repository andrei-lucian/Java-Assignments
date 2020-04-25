package nl.rug.oop.rpg;
import java.util.ArrayList;

public class Npc implements Inspectable, Interactable {
    private String description;

    public Npc(String description){
        this.description = description;
    }

    @Override
    public void inspect() {
        System.out.println(description);
    }

    @Override
    public void interact(Player player) {
        System.out.println("hello");
    }
}