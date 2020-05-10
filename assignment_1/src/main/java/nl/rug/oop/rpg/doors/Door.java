package nl.rug.oop.rpg.doors;

import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.Room;
import nl.rug.oop.rpg.util.Inspectable;
import nl.rug.oop.rpg.util.Interactable;
import java.io.Serializable;

/**
 * Class for making doors.
 */
public class Door implements Inspectable, Interactable, Serializable{

    private static final long serialVersionUID = 2L;
    protected String description;
    protected Room nextRoom;
    protected boolean isPowerDoor = false;

    /**
     * Constructor: Creates a door.
     * @param description Description of a door.
     */
    public Door(String description) {
        this.description = description;
    }

    public boolean isPowerDoor() {
        return isPowerDoor;
    }

    public void inspect() {
        System.out.println(description);
    }

    public void setNextRoom(Room room) {
        this.nextRoom = room;
    }

    /**
     * Move a player through to this door's next room.
     * @param player Player that goes through this door.
     */
    public void interact(Player player) {
        player.setCurrentRoom(nextRoom);
        System.out.println("You go through the door.");
    }
}