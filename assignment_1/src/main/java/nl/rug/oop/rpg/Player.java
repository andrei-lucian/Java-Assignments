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
}
