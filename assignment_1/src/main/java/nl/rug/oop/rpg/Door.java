package nl.rug.oop.rpg;
import java.util.ArrayList;

public class Door implements Inspectable, Interactable {

    private String description;
    private Room nextRoom;

    //set the description of door
    public Door(String description) {
        this.description = description;
    }

    //inspect a door
    public void inspect() {
        System.out.println(description);
    }

    //move the player to the next room behind the selected door
    public void interact(Player player) {
        player.setCurrentRoom(nextRoom);
    }

    //connect a door to its next room
    public void setNextRoom(Room room) {
        this.nextRoom = room;
    }
}
