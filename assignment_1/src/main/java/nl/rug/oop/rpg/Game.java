package nl.rug.oop.rpg;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private Player player;

    //constructor giving the game a player
    public Game(Player player){
        this.player = player;
    }

    Scanner scanner = new Scanner(System.in);

    //execute the main game loop
    public void gameLoop() {

        while (true) {

            //stop the loop if the player dies
            if (player.isDead()){
                System.out.println("You died, game over :(");

                System.exit(0);
            }

            printMenu();
            int option = scanner.nextInt();

            //changed this from if statements to a switch because it's cleaner
            switch(option){
                case 0: lookAround(player);
                case 1: lookForDoors(player);
                case 2: lookForNPC(player);
                case 3: goBack(player);
            }
        }
    }

    //display the interaction menu
    private void printMenu() {
            System.out.println("What do you want to do? \n" +
                    "(0) Look around \n" +
                    "(1) Look for a way out \n" +
                    "(2) Look for company \n" +
                    "(3) Go Back"); //to fix: this should not be an available option for the entry room
    }

    private void moveToNextRoom(Player player, Door newDoor) {
        newDoor.interact(player);
        System.out.println("You go through the door.");
        System.out.print("You are now in ");
        player.getCurrentRoom().inspect();
        gameLoop();
    }

    //the player chooses to inspect their surroundings
    private void lookAround(Player player){
        System.out.print("You see ");
        player.getCurrentRoom().inspect();
        gameLoop();
    }

    //the player selects a door to go through
    private void lookForDoors(Player player){

        System.out.println("You look around for doors.\nYou see:");

        Room currentRoom = player.getCurrentRoom();
        ArrayList<Door> doorList = currentRoom.getDoorList();

        for (Door door : doorList){ //loop through doors and print their descriptions
            System.out.print("(" + doorList.indexOf(door) + ") ");
            door.inspect();
        }

        System.out.println("Which door do you take? (-1 : stay here).");

        int chosenDoor = scanner.nextInt(); //user input (integer)

        if (chosenDoor > doorList.size() -1 || chosenDoor < -1) { //check bounds
            System.out.println("Not a door, please choose a different option.");
            lookForDoors(player);
        }

        else if (chosenDoor == -1) { //stay here
            gameLoop(); //exit the option1 method and go back to the original game loop
        }

        else { //move to room behind chosen door

            //store the current room as the previous room in case we want to go back
            player.setPreviousRoom(currentRoom);

            //move to next room
            Door newDoor = doorList.get(chosenDoor);
            moveToNextRoom(player, newDoor);
        }
    }

    //lookForNpc is almost identical to lookForDoor, we need to put these in new class
    private void lookForNPC(Player player){
        System.out.println("You look if there's someone here. \nYou see:");
        Room currentRoom = player.getCurrentRoom();

        ArrayList<NPC> npcList = currentRoom.getNpcList();

        //there are no NPCs in this room
        if(npcList.isEmpty()){
            System.out.println("An empty room");
            gameLoop();
        }

        else {
            for (NPC npc : npcList) { //loop through NPCs and print their descriptions
                System.out.print("(" + npcList.indexOf(npc) + ") ");
                npc.inspect();
            }
        }

        System.out.println("Interact? (-1 : don't interact).");

        int chosenNPC = scanner.nextInt(); //user input (integer)

        if (chosenNPC > npcList.size() -1 || chosenNPC < -1) { //check bounds
            System.out.println("Not an available option, please choose again.");
            lookForNPC(player);
        }
        else if (chosenNPC == -1) { //do not interact
            gameLoop(); //exit the method and go back to the original game loop
        }

        else {
            //interact with npc
            NPC newNpc = npcList.get(chosenNPC);
            newNpc.interact(player);
            //take damage if npc is Enemy
            if(newNpc instanceof Enemy){
                player.takeDamage(((Enemy)newNpc).dealDamage());
            }
            gameLoop();
        }
    }

    //method to go back to the previous room
    private void goBack(Player player){
        System.out.print("You went back to the previous room \n" +
                "You are now in ");
        player.goBack();
        player.getCurrentRoom().inspect();
        gameLoop();
    }

}
