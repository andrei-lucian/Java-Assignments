package nl.rug.oop.rpg;

import java.util.ArrayList;

public class Room implements Inspectable {

    private String description;
    private ArrayList<Door> doorList; //create the ArrayList

    public Room(String description){
        this.description = description;
        doorList = new ArrayList<>(); //initialise the ArrayList in the constructor
    }

    public void inspect(){
        System.out.println(description);
    }

    //add a door as long as it is not null
    public void addDoor(Door door){
        if (door != null) {
            doorList.add(door);
        }
        else {
            System.out.println("Cannot add null door object");
        }
    }

    public ArrayList<Door> getDoorList(Room room){
        return room.doorList;
    }

}
