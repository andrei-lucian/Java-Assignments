package nl.rug.oop.rpg;
import nl.rug.oop.rpg.io.Serializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

public class Game {

    Scanner scanner = new Scanner(System.in);
    private boolean exitGame = false;

    /** Executes game loop */
    public void gameLoop(Player player) {
        while (!exitGame) {
            checkLoseCondition(player);
            checkWinCondition(player);
            printMenu();
            int option = scanner.nextInt();
            switch(option){
                case 0: player.getCurrentRoom().inspect(); gameLoop(player);
                case 1: player.selectDoor(); gameLoop(player);
                case 2: player.selectNPC(); gameLoop(player);
                case 3: Serializer.savePlayer(player, "quicksave"); gameLoop(player);
                case 4: player = loadPlayer(player, "quicksave"); gameLoop(player);
                case 5: customSave(player); gameLoop(player);
                case 6: player = customLoad(player); gameLoop(player);
                case 7: exitGame();
            }
        }
    }

    private void checkWinCondition(Player player){
        if (player.getCurrentRoom().getDescription().equals("the end!")){
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
                    "(3) Quicksave \n" +
                    "(4) Quickload \n" +
                    "(5) Save \n" +
                    "(6) Load \n" +
                    "(7) Exit the game");
    }

    private void exitGame(){
        System.out.println("You exited the game.");
        System.exit(0);
        exitGame = true;
    }

    public Player loadPlayer(Player player, String fileName){
        try {
            player = Serializer.loadPlayer(fileName);
            System.out.println("Game loaded.");
            return player;
        } catch (IOException e) {
            System.out.println("Could not load from the file");
        } catch (ClassNotFoundException e) {
            System.out.println("The savefile could not be used to load a playaaaa");
        }
        System.out.println("Could not load previous game, starting from scratch instead.");
        return player;
    }

    public static boolean isAlphaNumeric(String s) {
        return s != null && s.matches("^[a-zA-Z0-9]*$");
    }

    /** File name cannot have more than 20 characters,
     * a file extension, only alphanumeric characters**/
    private void customSave(Player player){
        scanner = new Scanner(System.in);
        boolean saved = false;
        System.out.println("Please enter a name for your file (! to abort):");
        while(!saved){
            String s = scanner.nextLine();
            System.out.println(s);
            if (s.equals("!")) {
                System.out.println("Save aborted.");
                break;
            }
            else if (s.length() > 0 && s.length() < 21 &&
                isAlphaNumeric(s)){
                Serializer.savePlayer(player, s);
                saved = true;
            }
            else {
                System.out.println("File name does not meet requirements, " +
                        "please try again (! to abort)");
            }
        }
    }


    private Player customLoad(Player player){
        //Scanner scanner = new Scanner(System.in);
        boolean loaded = false;
        ArrayList<String> al = Serializer.listFiles();
        for(String s : al){
            System.out.println("(" + al.indexOf(s) + ") " +  s);
        }
        while(!loaded) {
            int chosen = scanner.nextInt();
            if (chosen > al.size() - 1 || chosen < -1) { //check bounds
                System.out.println("Not an option, please choose a different file.");
            }
            else if (chosen != -1) {
                String fileName = al.get(chosen);
                fileName = fileName.substring(0, fileName.lastIndexOf("."));
                System.out.println(fileName);
                player = loadPlayer(player, fileName);
                loaded = true;
            }
            else {
                break;
            }
        }
        return player;
    }
}