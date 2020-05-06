package nl.rug.oop.rpg.doors;

import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.Room;
import nl.rug.oop.rpg.util.Inspectable;
import nl.rug.oop.rpg.util.Interactable;
import java.io.Serializable;

public class Door implements Inspectable, Interactable, Serializable {

    private static final long serialVersionUID = 2L;
    protected String description;
    protected Room nextRoom;
    protected boolean isPowerDoor = false;

    public boolean isPowerDoor() {
        return isPowerDoor;
    }

    /** set the description of door */
    public Door(String description) {
        this.description = description;
    }

    /** inspect a door */
    public void inspect() {
        System.out.println(description);
    }

    /** move the player to the next room behind the selected door */
    public void interact(Player player) {
        player.setCurrentRoom(nextRoom);
        System.out.println("You go through the door.");
    }

    /** connect a door to its next room */
    public void setNextRoom(Room room) {
        this.nextRoom = room;
    }
}