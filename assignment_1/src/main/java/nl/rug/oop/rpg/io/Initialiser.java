package nl.rug.oop.rpg.io;

import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.doors.Door;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class Initialiser {

    public static void createProperties(String fileName){
        File configDirectory = new File("config");
        configDirectory.mkdir();

        Properties gameProperties = new Properties();
        gameProperties.setProperty("playerName", "jake");
        int maxHealth = 50;
        gameProperties.setProperty("maxHealth", String.valueOf(maxHealth));

        try (FileWriter fileWriter = new FileWriter(configDirectory + File.separator
                + fileName + ".properties")){
            gameProperties.store(fileWriter, "Game properties");
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("Could not write to file");
        }
    }

    public static void initGameFromProps(String fileName, Player player, ArrayList<Door> powerDoorList) throws IOException {
        File configDirectory = new File("config");
        try (FileReader fileReader = new FileReader(configDirectory + File.separator +
                fileName + ".properties")){
                Properties gameProperties = new Properties();
                gameProperties.load(fileReader);
                String playerName = gameProperties.getProperty("playerName");
                int maxHealth = Integer.parseInt(gameProperties.getProperty("maxHealth"));
                player.setName(playerName);
                player.setMaxHealth(maxHealth);

        }
    }
}