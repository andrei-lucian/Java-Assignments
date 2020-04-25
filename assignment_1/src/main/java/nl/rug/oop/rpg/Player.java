package nl.rug.oop.rpg;

public class Player {

    private String name;
    private Room currentRoom;
    private Room previousRoom;

    public Player(String name){
        this.name = name;
    }

    //inspect the room that the player is currently in
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

    //setter for the previous room
    public void setPreviousRoom(Room room){
        this.previousRoom = room;
    }

    //go back to the previous room
    public void goBack(){
        this.setCurrentRoom(previousRoom);
    }
}
