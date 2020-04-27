package nl.rug.oop.rpg;
import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);

    /**
     * Executes games loop
     */
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
                case 0: player.getCurrentRoom().inspect(); gameLoop(player);
                case 1: player.lookForDoors(); gameLoop(player);
                case 2: player.lookForNPC(); gameLoop(player);
                case 3: player.goBack(); gameLoop(player);
            }
        }
    }

    /**
     * prints interaction menu
     */
    private void printMenu() {
            System.out.println("What do you want to do? \n" +
                    "(0) Look around \n" +
                    "(1) Look for a way out \n" +
                    "(2) Look for company \n" +
                    "(3) Go Back"); //to fix: this should not be an available option for the entry room
    }
}