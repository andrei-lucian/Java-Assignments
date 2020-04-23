package nl.rug.oop.rpg;

public class Room {

    public String description;

    public Room(String roomDescription){
        description = roomDescription;
    }

    public void roomDescription(){
        System.out.println(description);
    }
}
