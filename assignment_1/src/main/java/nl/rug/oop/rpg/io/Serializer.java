package nl.rug.oop.rpg.io;
import nl.rug.oop.rpg.Game;
import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.Room;
import nl.rug.oop.rpg.doors.Door;
import nl.rug.oop.rpg.npcs.NPC;

import java.io.*;

public class Serializer {

    public static void savePlayer(Player player, String fileName) {
        /* Sets up the save directory */
        File saveDirectory = new File("savedGames");
        saveDirectory.mkdir();

        /* write object to a file */
        try(FileOutputStream fileOutputStream = new FileOutputStream(saveDirectory + File.separator + fileName + ".ser");
            ObjectOutputStream playerOutputStream = new ObjectOutputStream(fileOutputStream);
            /*ObjectOutputStream roomOutputStream = new ObjectOutputStream(fileOutputStream);
            ObjectOutputStream doorOutputStream = new ObjectOutputStream(fileOutputStream);
            ObjectOutputStream npcOutputStream = new ObjectOutputStream(fileOutputStream)*/) {
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

        try(FileInputStream fileInputStream = new FileInputStream(saveDirectory + File.separator + fileName+ ".ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            Player player = (Player)objectInputStream.readObject();
            return player;
        }
    }
}
