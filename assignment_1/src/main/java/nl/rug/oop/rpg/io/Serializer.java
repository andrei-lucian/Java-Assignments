package nl.rug.oop.rpg.io;
import nl.rug.oop.rpg.Player;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Serializer {

    public static void savePlayer(Player player, String fileName) {
        /* Sets up the save directory */
        File saveDirectory = new File("savedGames");
        saveDirectory.mkdir();

        /* write object to a file */
        try(FileOutputStream fileOutputStream = new FileOutputStream(saveDirectory + File.separator + fileName + ".ser");
            ObjectOutputStream playerOutputStream = new ObjectOutputStream(fileOutputStream)) {
            playerOutputStream.writeObject(player);
            System.out.println("Save successful!");
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not write to file");
        }
    }

    public static Player loadPlayer(String fileName) throws IOException, ClassNotFoundException {
        File saveDirectory = new File("savedGames");

        try(FileInputStream fileInputStream = new FileInputStream(saveDirectory + File.separator + fileName + ".ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            Player player = (Player)objectInputStream.readObject();
            return player;
        }
    }

    public static ArrayList listFiles(){
        ArrayList<String> results = new ArrayList<>();
        File[] files = new File("savedGames").listFiles();
        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }
        return results;
    }
}
