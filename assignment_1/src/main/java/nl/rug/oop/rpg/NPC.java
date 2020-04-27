package nl.rug.oop.rpg;

public class NPC implements Inspectable, Interactable {
    protected final String description;
    protected int damage;
    protected int health;

    public NPC(String description, int damage, int health){
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

    public boolean isFriendly() {
        return true;
    }
}