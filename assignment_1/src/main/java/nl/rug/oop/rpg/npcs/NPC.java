package nl.rug.oop.rpg.npcs;

import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.Room;
import nl.rug.oop.rpg.util.Inspectable;
import nl.rug.oop.rpg.util.Interactable;

import java.io.Serializable;

public abstract class NPC implements Inspectable, Interactable, Serializable {
    private static final long serialVersionUID = 4L;

    protected final String description;
    protected int damage;
    protected int health;
    protected Room room;
    protected int interact = 0;
    protected boolean currentlyInteracting;
    protected String uniqueLine;
    protected boolean isDead = false;

    public boolean isEnemy() {
        return isEnemy;
    }

    protected boolean isEnemy = false;

    /**
     * Constructor:
     * @param description NPC description
     */
    public NPC(String description, Room room){
        this.description = description;
        this.damage = 0;
        this.health = 0;
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