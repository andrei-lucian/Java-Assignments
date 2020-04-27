package nl.rug.oop.rpg;

import java.util.ArrayList;

public class Room implements Inspectable {

    private String description;
    private ArrayList<Door> doorList; //create Door list
    private ArrayList<NPC> npcList; //create NPC list

    public Room(String description) {
        this.description = description;
        doorList = new ArrayList<>(); //initialise the ArrayList in the constructor
        npcList = new ArrayList<>();
    }

    public String getDescription() {
        return this.description;
    }

    public void inspect() {
        System.out.println("You see:\n" + this.description);
    }

    //add a door as long as it is not null
    public void addDoor(Door door) {
        if (door != null) {
            doorList.add(door);
        } else {
            System.out.println("Cannot add null door object");
        }
    }

    //add an npc as long as it is not null
    public void addNPC(NPC npc) {
        if (npc != null) {
            npcList.add(npc);
        } else {
            System.out.println("Cannot add null npc object");
        }
    }

    //getter for the list of doors attached to a room
    public ArrayList<Door> getDoorList() {
        System.out.println("You look around for doors.\nYou see:");
        return this.doorList;
    }

    //getter for the list of npcs attached to a room
    public ArrayList<NPC> getNpcList() {
        System.out.println("You look if there's someone here. \nYou see:");
        return this.npcList;
    }
}