package nl.rug.oop.rpg;

import java.util.ArrayList;

public class Room implements Inspectable {

    private String description;
    private ArrayList<Door> doorList; //create Door list
    private ArrayList<NPC> npcList; //create NPC list

    /**
     * Constructor:
     * @param description room description
     */
    public Room(String description) {
        this.description = description;
        doorList = new ArrayList<>(); //initialise the ArrayList in the constructor
        npcList = new ArrayList<>();
    }

    /** print a room's description */
    public void inspect() {
        System.out.println("You see:\n" + this.description);
    }

    /** add a door to a room (as long as it is not null) */
    public void addDoor(Door door) {
        if (door != null) {
            doorList.add(door);
        } else {
            System.out.println("Cannot add null door object");
        }
    }

    /** add an NPC to a room (as long as it is not null) */
    public void addNPC(NPC npc) {
        if (npc != null) {
            npcList.add(npc);
        } else {
            System.out.println("Cannot add null npc object");
        }
    }

    /** print all the doors connected to a room
     * in an interactable way for the player */
    public ArrayList<Door> findDoors() {
        System.out.println("You look around for doors.\nYou see:");
        for (Door door : doorList){ //loop through doors and print their descriptions
            System.out.print("(" + doorList.indexOf(door) + ") ");
            door.inspect();
        }
        System.out.println("Which door do you take? (-1 : stay here).");
        return this.doorList;
    }

    /** print all the doors connected to a room
     * in an interactable way for the player */
    public ArrayList<NPC> findNPCs() {
        System.out.println("You look if there's someone here. \nYou see:");
        if(npcList.isEmpty()){
            System.out.println("An empty room");
        }
        else{
            for (NPC npc : npcList) { //loop through NPCs and print their descriptions
                System.out.print("(" + npcList.indexOf(npc) + ") ");
                npc.inspect();
            }
            System.out.println("Interact? (-1 : don't interact).");
        }
        return this.npcList;
    }
}