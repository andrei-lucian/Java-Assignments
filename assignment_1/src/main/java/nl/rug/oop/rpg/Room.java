package nl.rug.oop.rpg;

import java.util.ArrayList;

public class Room implements Inspectable {

    private String description;
    private ArrayList<Door> doorList; //create Door list
    private ArrayList<Npc> npcList; //create NPC list

    public Room(String description) {
        this.description = description;
        doorList = new ArrayList<>(); //initialise the ArrayList in the constructor
        npcList = new ArrayList<>();
    }

    public String getDescription() {
        return this.description;
    }

    public void inspect() {
        System.out.println(description);
    }

    //add a door as long as it is not null
    public void addDoor(Door door) {
        if (door != null) {
            doorList.add(door);
        } else {
            System.out.println("Cannot add null door object");
        }
    }

    public void addNPC(Npc npc) {
        if (npc != null) {
            npcList.add(npc);
        } else {
            System.out.println("Cannot add null npc object");
        }
    }

    public ArrayList<Door> getDoorList() {
        return this.doorList;
    }

    public ArrayList<Npc> getNpcList() {
        return this.npcList;
    }
}
