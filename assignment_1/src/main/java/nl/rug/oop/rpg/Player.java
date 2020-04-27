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

    public Room getCurrentRoom(){
        return this.currentRoom;
    }

    public void setPreviousRoom(Room room){
        this.previousRoom = room;
    }

    public int getHealth(){
        return this.health;
    }

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
        System.out.println("You go through the door.");
        this.getCurrentRoom().inspect();
    }

    /** select door and go to next room */
    public void lookForDoors(){
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

        else if (chosenDoor != -1){ //move to room behind chosen door
            //store the current room as the previous room in case we want to go back
            this.setPreviousRoom(currentRoom);
            //move to next room
            Door newDoor = doorList.get(chosenDoor);
            moveToNextRoom(newDoor);
        }
    }

    /** select NPC and interact with NPC */
    public void lookForNPC(){
        ArrayList<NPC> npcList = this.getCurrentRoom().getNpcList();

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

        else if (chosenNPC != -1){
            //interact with npc
            NPC newNpc = npcList.get(chosenNPC);
            newNpc.interact(this);
            //take damage if npc is Enemy
            if(!newNpc.isFriendly()){
                this.takeDamage(((Enemy)newNpc).dealDamage());
            }
        }
    }

    /** Method to go to previous room */
    public void goBack(){
        System.out.print("You went back to the previous room \n" +
                "You are now in ");
        this.setCurrentRoom(previousRoom);
        this.getCurrentRoom().inspect();
    }
}