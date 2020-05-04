package nl.rug.oop.rpg;
import nl.rug.oop.rpg.io.Serializer;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Game {
    Scanner scanner = new Scanner(System.in);

    /** Executes game loop */
    public void gameLoop(Player player) {
        while (true) {
            checkLoseCondition(player);
            checkWinCondition(player);
            printMenu();
            int option = scanner.nextInt();
            switch(option){
                case 0: player.getCurrentRoom().inspect(); gameLoop(player);
                case 1: player.selectDoor(); gameLoop(player);
                case 2: player.selectNPC(); gameLoop(player);
                case 3: Serializer.savePlayer(player, "quicksave"); gameLoop(player);
                case 4: loadPlayer(player); gameLoop(player);
                case 5: exitGame();
            }
        }
    }

    private void checkWinCondition(Player player){
        if (player.getExp() >= 500 || player.getCurrentRoom().getDescription().equals("the end!")){
            System.out.println("You win the game.");
            System.exit(0);
        }
    }

    private void checkLoseCondition(Player player){
        if (player.isDead()){
            System.out.println("You died, game over :(");
            System.exit(0);
        }
    }

    /** prints interaction menu */
    private void printMenu() {
            System.out.println("What do you want to do? \n" +
                    "(0) Look around \n" +
                    "(1) Look for a way out \n" +
                    "(2) Look for company \n" +
                    //"(3) Go back to the previous room\n" + //to fix: this should not be an available option for the entry room
                    "(3) Quicksave \n" +
                    "(4) Quickload \n" +
                    "(5) Exit the game\n");
    }

    private void exitGame(){
        System.out.println("You exited the game.");
        System.exit(0);
    }

    public void loadPlayer(Player player){
        try {
            player = Serializer.loadPlayer("quicksave");
            System.out.println("Game loaded.");

        } catch (IOException e) {
            System.out.println("Could not load from the file");
        } catch (ClassNotFoundException e) {
            System.out.println("The savefile could not be used to load a playaaaa");
        }
    }
}