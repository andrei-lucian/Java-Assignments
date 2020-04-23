package nl.rug.oop.rpg;

public class Player {

    protected String name;
    public Room currentRoom;

    private Player(String name, Room room){
        this.name = name;
    }

    private void inspect(){
        currentRoom.getDescription();
    }
}
