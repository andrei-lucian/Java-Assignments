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
            printMenu();
            int option = scanner.nextInt();

            //changed this from if statements to a switch because it's cleaner
            switch(option){
                case 0: option0(player);
                case 1: option1(player);
                case 2: option2(player);
            }
        }
    }

    //display the interaction menu
    public void printMenu() {
        System.out.println("What do you want to do? \n" +
                            "(0) Look around \n" +
                            "(1) Look for a way out +" +
                            "(2) Go back to the previous room");
    }

    //the player chooses to inspect their surroundings
    public void option0(Player player){
        System.out.print("You see ");
        player.inspect();
        gameLoop();
    }

    //the player selects a door to go through
    public void option1(Player player){

        System.out.println("You look around for doors.\nYou see:");

        for (Door door : player.getCurrentRoom().getDoorList()){ //loop through doors and print their descriptions
            System.out.print("(" + player.getCurrentRoom().getDoorList().indexOf(door) + ") ");
            door.inspect();
        }

        System.out.println("Which door do you take? (-1 : stay here).");

        int chosenDoor = scanner.nextInt(); //user input (integer)

        if (chosenDoor > player.getCurrentRoom().getDoorList().size() -1 || chosenDoor < -1) { //check bounds
            System.out.println("Not a door, please choose a different option.");
            option1(player);
        }

        else if (chosenDoor == -1) { //stay here
            gameLoop(); //exit the option1 method and go back to the original game loop
        }

        else { //move to room behind chosen door
            player.getCurrentRoom().getDoorList().get(chosenDoor).interact(player);
            System.out.println("You go through the door.");
            System.out.print("You are now in ");
            player.getCurrentRoom().inspect();
            gameLoop();
        }
    }

    public void option2(Player player){

    }
}




