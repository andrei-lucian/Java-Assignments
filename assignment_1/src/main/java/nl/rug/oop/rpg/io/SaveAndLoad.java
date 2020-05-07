package nl.rug.oop.rpg.io;

import nl.rug.oop.rpg.Main;
import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.util.CatchNonInts;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that stores all methods for saving and loading.
 */
public class SaveAndLoad {
    public static boolean saved = false;
    public static boolean loaded = false;

    /**
     * Load a player from a saved file.
     * @param player Original player created in the main class.
     * @param fileName The name of the file to be loaded.
     * @return A player (either the original player if the game
     * could not be loaded, or a new player loaded from a saved game.
     */
    public static Player loadPlayer(Player player, String fileName){
        try {
            player = Serializer.loadPlayer(fileName);
            System.out.println("Game loaded.");
            return player;
        } catch (IOException e) {
            System.out.println("Could not load from the file");
        } catch (ClassNotFoundException e) {
            System.out.println("The savefile could not be used to load a player");
        }
        System.out.println("Could not load previous game, starting from scratch instead.");
        return player;
    }

    /**
     * Check if a string is alphanumeric (using regex).
     * @param s String to be checked.
     * @return if s is alphanumeric or not (boolean).
     */
    public static boolean isAlphaNumeric(String s) {
        return s != null && s.matches("^[a-zA-Z0-9]*$");
    }

    /**
     * Save a game with a file name that the user inputs.
     * @param player Save this player's state.
     */
    public static void customSave(Player player){
        saved = false;
        Main.scanner = new Scanner(System.in);
        System.out.println("Please enter a name for your file (! to abort):");
        while(!saved){
            String s = Main.scanner.nextLine();
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

    /**
     * @return an ArrayList of the names of all the
     * files in the savedGames directory.
     */
    public static ArrayList<String> listFiles(){
        ArrayList<String> results = new ArrayList<>();
        File[] files = new File("savedGames").listFiles();
        for (File file : files != null ? files : new File[0]) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }
        return results;
    }

    /**
     *
     * @param player Player object to be changed by loading.
     * @return Player that has either been changed by loading
     * the player from a file, or the original player passed in
     * to the function (if the user decides to abort the load
     * by entering -1).
     */
    public static Player customLoad(Player player){
        loaded = false;
        ArrayList<String> al = listFiles();
        for(String s : al){
            System.out.println("(" + al.indexOf(s) + ") " +  s);
        }
        while(!loaded) {
            int chosen = CatchNonInts.inputOption();
            if (chosen > al.size() - 1 || chosen < -1) { //check bounds
                System.out.println("Not an option, please choose a different file.");
            }
            else if (chosen != -1) {
                String fileName = al.get(chosen);
                fileName = fileName.substring(0, fileName.lastIndexOf("."));
                player = loadPlayer(player, fileName);
                loaded = true;
            }
            else {
                System.out.println("Load aborted");
                break;
            }
        }
        return player;
    }
}
