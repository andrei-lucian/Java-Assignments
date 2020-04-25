package nl.rug.oop.rpg;

public class Player {

    protected String name;
    public Room currentRoom;

    public Player(String name){
        this.name = name;
    }

    public void inspect(){
        currentRoom.inspect();
    }

    //set a player's current room
    public void setCurrentRoom(Room room){
        this.currentRoom = room;
    }

    //return a player's current room
    public Room getCurrentRoom(){
        return this.currentRoom;
    }
}
