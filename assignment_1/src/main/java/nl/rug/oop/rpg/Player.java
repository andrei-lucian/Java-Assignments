package nl.rug.oop.rpg;

public class Player {

    protected String name;
    public Room currentRoom;

    public Player(String name){
        this.name = name;
    }

    public void inspect(){
        currentRoom.getDescription();
    }

    public void setCurrentRoom(Room room){
        this.currentRoom = room;
    }
}
