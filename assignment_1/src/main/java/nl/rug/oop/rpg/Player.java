package nl.rug.oop.rpg;

public class Player {

    protected String name;
    public Room currentRoom;

    public Player(String name){
        this.name = name;
    }

    public void inspect(){
        System.out.print("You see ");
        currentRoom.inspect();
    }

    public void setCurrentRoom(Room room){
        this.currentRoom = room;
    }
}
