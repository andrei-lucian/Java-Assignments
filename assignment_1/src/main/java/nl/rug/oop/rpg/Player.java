package nl.rug.oop.rpg;
import java.util.Scanner;
import java.util.ArrayList;

public class Player implements Attackable{

    private final String name;
    private int damage;
    private int health = 100;
    private int maxHealth = 100;
    private Room currentRoom;
    private Room previousRoom;
    private int exp = 0;
    private boolean isDead = false;
    Scanner scanner = new Scanner(System.in);

    /**
     * Constructor:
     * @param name player name
     * @param damage player damage
     */

    public Player(String name, int damage){
        this.name = name;
        this.damage = damage;
    }

    public void setCurrentRoom(Room room){
        this.currentRoom = room;
    }

    /** @return currentRoom: room that the player is in */
    public Room getCurrentRoom(){
        return this.currentRoom;
    }

    /** set the previous room in case we want to go back */
    public void setPreviousRoom(Room room){
        this.previousRoom = room;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setExp(int exp) {
        this.exp += exp;
        System.out.println("Your exp is: " + this.exp);
    }

    /** Player takes damage from an enemy
     * if (health-damage) > 0. If this is not the case
     * then the player is dead, so set isDead to true.
     * Otherwise, decrement player's health. Also print a
     * warning if health is too low. */
    @Override
    public void takeDamage(int damage) {
        if (this.health-damage > 0){
            this.health -= damage;
            System.out.println("Your health is: " + this.health);
            if (this.health < 50){
                System.out.println("Your health is critically low, look for a health wizard!");
            }
        }
        else {
            this.isDead = true;
        }
    }

    public void increaseHealth(){
        if(this.health == this.maxHealth){
            System.out.println("Maximum health reached - you can't heal anymore." +
                    " Your health is: " + maxHealth);
        }
        else {
            this.health = this.maxHealth;
            System.out.println("Nice! Your health is back at " + this.health + "!");
        }
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth += maxHealth;
        System.out.println("Your max health increased to: " + this.maxHealth);
    }

    /** @return if the player is dead */
    public boolean isDead(){
        return this.isDead;
    }

    @Override
    public int dealDamage() {
        return this.damage;
    }

    /** Moves player to next room */
    private void moveToNextRoom(Door newDoor) {
        newDoor.interact(this);
    }

    /** select door and go to next room */
    public void selectDoor(){
        ArrayList<Door> doorList = this.currentRoom.findDoors();
        int chosenDoor = scanner.nextInt(); //user input (integer)
        if (chosenDoor > doorList.size() -1 || chosenDoor < -1) { //check bounds
            System.out.println("Not a door, please choose a different option.");
            selectDoor();
        }
        else if (chosenDoor != -1){ //move to room behind chosen door
            this.setPreviousRoom(currentRoom); //store the current room as the previous room in case we want to go back
            Door currentDoor = doorList.get(chosenDoor); //move to next room
            moveToNextRoom(currentDoor);
        }
    }

    /** select NPC and interact with NPC */
    public void selectNPC(){
        ArrayList<NPC> npcList = this.getCurrentRoom().findNPCs();
        int chosenNPC = scanner.nextInt();
        if (chosenNPC > npcList.size() -1 || chosenNPC < -1) { //check bounds
            System.out.println("Not an available option, please choose again.");
            selectNPC();
        }
        else if (chosenNPC != -1){
            NPC currentNpc = npcList.get(chosenNPC); //interact with npc
            currentNpc.interact(this);
        }
    }

    /** Method to go to previous room */
    public void goBack(){
        System.out.println("You went back to the previous room.");
        this.setCurrentRoom(previousRoom);
        this.getCurrentRoom().inspect();
    }

    public int getExp() {
        return exp;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
}