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
    public void gameLoop(Player player) {

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
                case 0: player.lookAround(); gameLoop(player);
                case 1: player.lookForDoors(); gameLoop(player);
                case 2: lookForNPC(player);
                case 3: player.goBack(); gameLoop(player);
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

    //lookForNpc is almost identical to lookForDoor, we need to put these in new class
    private void lookForNPC(Player player){
        System.out.println("You look if there's someone here. \nYou see:");
        Room currentRoom = player.getCurrentRoom();

        ArrayList<NPC> npcList = currentRoom.getNpcList();

        //there are no NPCs in this room
        if(npcList.isEmpty()){
            System.out.println("An empty room");
            gameLoop(player);
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
            gameLoop(player); //exit the method and go back to the original game loop
        }

        else {
            //interact with npc
            NPC newNpc = npcList.get(chosenNPC);
            newNpc.interact(player);
            //take damage if npc is Enemy
            if(newNpc.isFriendly()){
                player.takeDamage(((Enemy)newNpc).dealDamage());
            }
            gameLoop(player);
        }
    }
}
