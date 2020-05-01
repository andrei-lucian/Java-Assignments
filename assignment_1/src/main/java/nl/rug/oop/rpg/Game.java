package nl.rug.oop.rpg;
import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);

    /** Executes game loop */
    public void gameLoop(Player player) {
        while (true) {
            if (player.isDead()){
                System.out.println("You died, game over :(");
                System.exit(0);
            }
            printMenu();
            int option = scanner.nextInt();
            switch(option){
                case 0: player.getCurrentRoom().inspect(); gameLoop(player);
                case 1: player.selectDoor(); gameLoop(player);
                case 2: player.selectNPC(); gameLoop(player);
                case 3: player.goBack(); gameLoop(player);
                case 4: exitGame();
            }
        }
    }

    /** prints interaction menu */
    private void printMenu() {
            System.out.println("What do you want to do? \n" +
                    "(0) Look around \n" +
                    "(1) Look for a way out \n" +
                    "(2) Look for company \n" +
                    "(3) Go back to the previous room\n" + //to fix: this should not be an available option for the entry room
                    "(4) Exit the game");
    }

    private void exitGame(){
        System.out.println("You exited the game.");
        System.exit(0);
    }
}