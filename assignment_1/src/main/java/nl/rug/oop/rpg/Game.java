package nl.rug.oop.rpg;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    protected Player player;

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
        /*if (currentRoom == entry) {
            System.out.println("What do you want to do? \n" +
                    "(0) Look around \n" +
                    "(1) Look for a way out \n");
        }
        else {*/
            System.out.println("What do you want to do? \n" +
                    "(0) Look around \n" +
                    "(1) Look for a way out \n" +
                    "(2) Go back ");
        //}
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
            option1(player);
        }

        else if (chosenDoor == -1) { //stay here
            gameLoop(); //exit the option1 method and go back to the original game loop
        }

        else { //move to room behind chosen door

            //store the current room as the previous room in case we want to go back
            player.setPreviousRoom(currentRoom);

            //move to next room
            Door newDoor = doorList.get(chosenDoor);
            newDoor.interact(player);
            System.out.println("You go through the door.");
            System.out.print("You are now in ");
            player.getCurrentRoom().inspect();
            gameLoop();
        }
    }

    //method to go back to the previous room
    public void option2(Player player){
        System.out.print("You went back to the previous room \n" +
                "You are now in ");
        player.goBack();
        player.getCurrentRoom().inspect();
    }
}




