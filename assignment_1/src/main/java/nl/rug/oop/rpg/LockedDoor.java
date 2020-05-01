package nl.rug.oop.rpg;

public class LockedDoor extends Door {
    /**
     * set the description of door
     *
     * @param description door description
     */
    public LockedDoor(String description) {
        super(description);
    }

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
