package nl.rug.oop.rpg;

public class Player {

    private String name;
    private Room currentRoom;
    private Room previousRoom;

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
