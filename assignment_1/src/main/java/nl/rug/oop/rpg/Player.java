package nl.rug.oop.rpg;
import nl.rug.oop.rpg.doors.Door;
import nl.rug.oop.rpg.npcs.NPC;
import nl.rug.oop.rpg.util.Attackable;
import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Attackable, Serializable {
    private static final long serialVersionUID = 1L;
    private String name = "Bob";
    private int damage;
    private int maxHealth = 100;
    private int health = maxHealth;
    private Room currentRoom;
    private boolean isDead = false;

    /**
     * Constructor:
     * @param damage player damage
     */

    public Player(int damage){
        this.damage = damage;
    }

    public void setCurrentRoom(Room room){
        this.currentRoom = room;
    }

    /** @return currentRoom: room that the player is in */
    public Room getCurrentRoom(){
        return this.currentRoom;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
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

    @Override
    public int dealDamage() {
        return this.damage;
    }

    /** @return if the player is dead */
    public boolean isDead(){
        return this.isDead;
    }

    /** Moves player to next room */
    private void moveToNextRoom(Door newDoor) {
        newDoor.interact(this);
    }

    /** select door and go to next room */
    public void selectDoor(){
        ArrayList<Door> doorList = this.currentRoom.findDoors();
        int chosenDoor = Main.scanner.nextInt(); //user input (integer)
        if (chosenDoor > doorList.size() -1 || chosenDoor < -1) { //check bounds
            System.out.println("Not a door, please choose a different option.");
            selectDoor();
        }
        else if (chosenDoor != -1){ //move to room behind chosen door
            //this.setPreviousRoom(currentRoom); //store the current room as the previous room in case we want to go back
            Door currentDoor = doorList.get(chosenDoor); //move to next room
            moveToNextRoom(currentDoor);
        }
    }

    /** select NPC and interact with NPC */
    public void selectNPC(){
        ArrayList<NPC> npcList = this.getCurrentRoom().findNPCs();
        int chosenNPC = Main.scanner.nextInt();
        if (chosenNPC > npcList.size() -1 || chosenNPC < -1) { //check bounds
            System.out.println("Not an available option, please choose again.");
            selectNPC();
        }
        else if (chosenNPC != -1){
            NPC currentNpc = npcList.get(chosenNPC); //interact with npc
            currentNpc.interact(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void increaseHealth(){
        if(this.health == this.maxHealth){
            System.out.println("Maximum health reached - you can't heal anymore." +
                    " Your health is: " + maxHealth);
        }
        else {
            this.health = this.maxHealth;
            System.out.println("Nice! Your health is: " + this.health + "!");
        }
    }

    public void increaseMaxHealth(int maxHealth) {
        this.maxHealth += maxHealth;
        System.out.println("Your max health increased to: " + this.maxHealth);
    }
}