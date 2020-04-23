package nl.rug.oop.rpg;

public class Player {

    protected String name;
    public Room currentRoom;

    public Player(String name){
        this.name = name;
    }

    private void inspect(){
        currentRoom.getDescription();
    }
}
