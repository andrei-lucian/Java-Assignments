package nl.rug.oop.rpg;

import nl.rug.oop.rpg.doors.Door;
import nl.rug.oop.rpg.npcs.NPC;
import nl.rug.oop.rpg.util.Inspectable;
import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Inspectable, Serializable {

    private static final long serialVersionUID = 3L;
    private final String description;
    private final ArrayList<Door> doorList; //create Door list
    private final ArrayList<NPC> npcList; //create NPC list

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
        System.out.println("You see " + this.description);
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
    public void addNpc(NPC npc) {
        if (npc != null) {
            npcList.add(npc);
        } else {
            System.out.println("Cannot add null npc object");
        }
    }

    /** remove an npc from a room */
    public void removeNPC(NPC npc){
        if (npc != null) {
            npcList.remove(npc);
        } else {
            System.out.println("Cannot remove null npc object");
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
            System.out.println("An empty room. (-1: Go back)");
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

    public boolean emptyRoom(){
        if(npcList.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

    public String getDescription() {
        return description;
    }

}