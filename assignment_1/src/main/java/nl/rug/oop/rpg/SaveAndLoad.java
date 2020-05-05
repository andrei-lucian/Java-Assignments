package nl.rug.oop.rpg;

import nl.rug.oop.rpg.io.Serializer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveAndLoad {

    public static ArrayList<String> listFiles(){
        ArrayList<String> results = new ArrayList<>();
        File[] files = new File("savedGames").listFiles();
        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }
        return results;
    }

    public static Player loadPlayer(Player player, String fileName){
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

    public static void customSave(Player player){
        boolean saved = false;
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

    public static Player customLoad(Player player){
        boolean loaded = false;
        ArrayList<String> al = listFiles();
        for(String s : al){
            System.out.println("(" + al.indexOf(s) + ") " +  s);
        }
        while(!loaded) {
            int chosen = Main.scanner.nextInt();
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
