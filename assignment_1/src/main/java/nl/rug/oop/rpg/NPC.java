package nl.rug.oop.rpg;

public abstract class NPC implements Inspectable, Interactable {
    protected final String description;
    protected int damage;
    protected int health;
    protected Room room;
    protected int interact = 0;
    protected boolean currentlyInteracting;

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

    /** Remove npc from room*/
    protected void removeFromRoom(Room room){
        room.removeNPC(this);
    }

    /** interact with an NPC */
    @Override
    public abstract void interact(Player player);
}