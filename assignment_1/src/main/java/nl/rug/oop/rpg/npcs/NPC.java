package nl.rug.oop.rpg.npcs;

import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.Room;
import nl.rug.oop.rpg.util.Inspectable;
import nl.rug.oop.rpg.util.Interactable;

import java.io.Serializable;

/**
 * Class to make an NPC (non-player character) in a game.
 * This is an abstract class.
 */
public abstract class NPC implements Inspectable, Interactable, Serializable {
    private static final long serialVersionUID = 4L;

    protected final String description;
    protected Room room;
    protected int interact = 0;
    protected boolean currentlyInteracting;
    protected String uniqueLine;
    protected boolean isDead = false;

    protected boolean isEnemy = false;

    /**
     * Constructor:
     * @param description NPC description.
     * @param room The room that an NPC is in.
     */
    public NPC(String description, Room room){
        this.description = description;
        this.room = room;
    }

    public boolean isEnemy() {
        return isEnemy;
    }

    protected void removeFromRoom(Room room){
        room.removeNPC(this);
    }

    @Override
    public void inspect() {
        System.out.println(description);
    }

    @Override
    public abstract void interact(Player player);
}