package nl.rug.oop.rpg;

public abstract class NPC implements Inspectable, Interactable {
    protected final String description;
    protected int damage;
    protected int health;
    protected Room room;

    /**
     * Constructor:
     * @param description NPC description
     * @param damage NPC damage
     * @param health NPC health
     */
    public NPC(String description, int damage, int health, Room room){
        this.description = description;
        this.damage = damage;
        this.health = health;
        this.room = room;
    }

    /** inspect an NPC's description */
    @Override
    public void inspect() {
        System.out.println(description);
    }

    /** interact with an NPC */
    @Override
    public abstract void interact(Player player);
}