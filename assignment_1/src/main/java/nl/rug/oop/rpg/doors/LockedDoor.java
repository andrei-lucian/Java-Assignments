package nl.rug.oop.rpg.doors;

import nl.rug.oop.rpg.Player;

/**
 * This door only opens once all the NPCs
 * in a room are dead i.e the room is empty.
 */
public class LockedDoor extends Door {

    private static final long serialVersionUID = 22L;

    public LockedDoor(String description) {
        super(description);
    }

    /**
     * This door only opens when the room that a player is in is empty.
     * @param player Player that goes through this door.
     */
    @Override
    public void interact(Player player){
        if(player.getCurrentRoom().emptyRoom()){
            player.setCurrentRoom(nextRoom);
            System.out.println("You go through the door.");
        }
        else{
            System.out.println("The door will open when everyone in this room is dead. ");
        }
    }
}
