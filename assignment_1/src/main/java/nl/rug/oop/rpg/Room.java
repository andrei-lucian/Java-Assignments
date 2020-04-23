package nl.rug.oop.rpg;

public class Room {

    public String description;

    public Room(String roomDescription){
        description = roomDescription;
    }

    public void getDescription(){
        System.out.println(description);
    }
}
