package nl.rug.oop.rpg;

import java.util.ArrayList;

public class Room implements Inspectable {

    private String description;
    public ArrayList<Door> doorList;

    public Room(String description){
        this.description = description;
        doorList = new ArrayList<>();
    }

    public void inspect(){
        System.out.println(description);
    }

    public void addDoor(Door door){
        if (door != null) {
            doorList.add(door);
        }
        else {
            System.out.println("Cannot add null door object");
        }
    }
}
