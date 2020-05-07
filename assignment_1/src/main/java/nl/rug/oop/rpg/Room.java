package nl.rug.oop.rpg;

import nl.rug.oop.rpg.doors.Door;
import nl.rug.oop.rpg.npcs.NPC;
import nl.rug.oop.rpg.util.Inspectable;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class for making a room in the game.
 */
public class Room implements Inspectable, Serializable {

    private static final long serialVersionUID = 3L;
    private final String description;
    private final ArrayList<Door> doorList;
    private final ArrayList<NPC> npcList;

    /**
     * Constructor: Create a room with a description,
     * list of doors connected to it, and list of NPCs in it.
     * @param description room description
     */
    public Room(String description) {
        this.description = description;
        doorList = new ArrayList<>();
        npcList = new ArrayList<>();
    }

    public ArrayList<NPC> getNpcList() {
        return npcList;
    }

    public ArrayList<Door> getDoorList() {
        return doorList;
    }

    public String getDescription() {
        return description;
    }

    public void inspect() {
        System.out.println("You see " + this.description);
    }

    public void addDoor(Door door) {
        if (door != null) {
            doorList.add(door);
        } else {
            System.out.println("Cannot add null door object");
        }
    }

    public void addNpc(NPC npc) {
        if (npc != null) {
            npcList.add(npc);
        } else {
            System.out.println("Cannot add null npc object");
        }
    }

    public void removeNPC(NPC npc){
        if (npc != null) {
            npcList.remove(npc);
        } else {
            System.out.println("Cannot remove null npc object");
        }
    }

    public boolean emptyRoom(){
        return npcList.isEmpty();
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

    /** print all the NPCs connected to a room
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
}