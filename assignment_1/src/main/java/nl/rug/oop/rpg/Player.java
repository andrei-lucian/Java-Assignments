package nl.rug.oop.rpg;
import java.util.Scanner;

import java.util.ArrayList;

public class Player implements Attackable{

    private final String name;
    private int damage;
    private int health = 100;
    private Room currentRoom;
    private Room previousRoom;
    private boolean isDead = false;

    public Player(String name, int damage){
        this.name = name;
        this.damage = damage;
    }

    //set a player's current room
    public void setCurrentRoom(Room room){
        this.currentRoom = room;
    }

    //return a player's current room
    public Room getCurrentRoom(){
        return this.currentRoom;
    }

    //setter for the previous room
    public void setPreviousRoom(Room room){
        this.previousRoom = room;
    }

    public int getHealth(){
        return this.health;
    }

    //take damage
    @Override
    public void takeDamage(int damage) {

        //take damage if it doesn't result in the player dying
        if (this.health-damage > 0){
            this.health -= damage;

            System.out.println("You: 'Rude...' \nYour health is: " + this.health);

            //low health warning
            if (this.health < 50){
                System.out.println("Your health is critically low, look for a health wizard!");
            }
        }
        //player dies because health <= 0
        else {
            this.isDead = true;
        }
    }

    //returns if the player is dead or not
    public boolean isDead(){
        return this.isDead;
    }

    //deal damage
    @Override
    public int dealDamage() {
        return this.damage;
    }

    //the player chooses to inspect their surroundings
    public void lookAround(){
        System.out.print("You see ");
        this.getCurrentRoom().inspect();
    }

    //move to the next room
    private void moveToNextRoom(Door newDoor) {
        newDoor.interact(this);
        System.out.println("You go through the door.");
        System.out.print("You are now in ");
        this.getCurrentRoom().inspect();
    }

    //the player selects a door to go through
    public void lookForDoors(){
        System.out.println("You look around for doors.\nYou see:");

        Room currentRoom = this.getCurrentRoom();
        ArrayList<Door> doorList = currentRoom.getDoorList();

        for (Door door : doorList){ //loop through doors and print their descriptions
            System.out.print("(" + doorList.indexOf(door) + ") ");
            door.inspect();
        }

        System.out.println("Which door do you take? (-1 : stay here).");
        Scanner scanner = new Scanner(System.in);
        int chosenDoor = scanner.nextInt(); //user input (integer)

        if (chosenDoor > doorList.size() -1 || chosenDoor < -1) { //check bounds
            System.out.println("Not a door, please choose a different option.");
            lookForDoors();
        }

        else if (chosenDoor == -1) { //stay here
            return; //exit the option1 method and go back to the original game loop
        }

        else { //move to room behind chosen door
            //store the current room as the previous room in case we want to go back
            this.setPreviousRoom(currentRoom);
            //move to next room
            Door newDoor = doorList.get(chosenDoor);
            moveToNextRoom(newDoor);
        }
    }

    //method to go back to the previous room
    public void goBack(){
        System.out.print("You went back to the previous room \n" +
                "You are now in ");
        this.setCurrentRoom(previousRoom);
        this.getCurrentRoom().inspect();
    }

    //lookForNpc is almost identical to lookForDoor, we need to put these in new class
    public void lookForNPC(){
        System.out.println("You look if there's someone here. \nYou see:");
        Room currentRoom = this.getCurrentRoom();

        ArrayList<NPC> npcList = currentRoom.getNpcList();

        //there are no NPCs in this room
        if(npcList.isEmpty()){
            System.out.println("An empty room");
        }

        else {
            for (NPC npc : npcList) { //loop through NPCs and print their descriptions
                System.out.print("(" + npcList.indexOf(npc) + ") ");
                npc.inspect();
            }
        }

        System.out.println("Interact? (-1 : don't interact).");
        Scanner scanner = new Scanner(System.in);
        int chosenNPC = scanner.nextInt(); //user input (integer)

        if (chosenNPC > npcList.size() -1 || chosenNPC < -1) { //check bounds
            System.out.println("Not an available option, please choose again.");
            lookForNPC();
        }
        else if (chosenNPC == -1) { //do not interact
            return; //exit the method and go back to the original game loop
        }

        else {
            //interact with npc
            NPC newNpc = npcList.get(chosenNPC);
            newNpc.interact(this);
            //take damage if npc is Enemy
            if(!newNpc.isFriendly()){
                this.takeDamage(((Enemy)newNpc).dealDamage());
            }
        }
    }
}
