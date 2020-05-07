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
     * Constructor: Create a player.
     * @param damage Player damage.
     */
    public Player(int damage){
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room getCurrentRoom(){
        return this.currentRoom;
    }

    public void setCurrentRoom(Room room){
        this.currentRoom = room;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
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

    private void moveToNextRoom(Door newDoor) {
        newDoor.interact(this);
    }

    public boolean isDead(){
        return this.isDead;
    }

    /**
     * Take damage from an enemy. This either decrements the player's health
     * or kills the player. Also prints a warning if health is too low.
     * @param damage The amount of damage that the player takes.
     */
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

}