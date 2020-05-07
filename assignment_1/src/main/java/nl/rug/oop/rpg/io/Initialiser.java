package nl.rug.oop.rpg.io;

import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.Room;
import nl.rug.oop.rpg.doors.Door;
import nl.rug.oop.rpg.doors.PowerDoor;
import nl.rug.oop.rpg.npcs.Enemy;
import nl.rug.oop.rpg.npcs.NPC;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class Initialiser {
    public static void createProperties(String fileName){
        int maxHealth = 50;
        int enemyDamage = 200;
        int enemyHealth = 500;
        int doorPower = 30;

        File configDirectory = new File("config");
        configDirectory.mkdir();

        Properties gameProperties = new Properties();
        gameProperties.setProperty("playerName", "jake");
        gameProperties.setProperty("maxHealth", String.valueOf(maxHealth));
        gameProperties.setProperty("enemyDamage", String.valueOf(enemyDamage));
        gameProperties.setProperty("enemyHealth", String.valueOf(enemyHealth));
        gameProperties.setProperty("doorPower", String.valueOf(doorPower));

        try (FileWriter fileWriter = new FileWriter(configDirectory + File.separator
                + fileName + ".properties")){
            gameProperties.store(fileWriter, "Game properties");
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("Could not write to file");
        }
    }

    public static void initGameFromProps(String fileName, Player player, Room[] rooms) throws IOException {
        File configDirectory = new File("config");
        try (FileReader fileReader = new FileReader(configDirectory + File.separator +
                fileName + ".properties")){
                Properties gameProperties = new Properties();
                gameProperties.load(fileReader);
                String playerName = gameProperties.getProperty("playerName");
                int maxHealth = Integer.parseInt(gameProperties.getProperty("maxHealth"));
                int enemyDamage = Integer.parseInt(gameProperties.getProperty("enemyDamage"));
                int enemyHealth = Integer.parseInt(gameProperties.getProperty("enemyHealth"));
                int doorPower = Integer.parseInt(gameProperties.getProperty("doorPower"));
                player.setName(playerName);
                player.setMaxHealth(maxHealth);
                for (Room room : rooms){
                    ArrayList<NPC> npcs = room.getNpcList();
                    for (NPC npc : npcs){
                        if (npc.isEnemy()){
                            Enemy newEnemy = (Enemy)npc;
                            newEnemy.setBaseDamage(enemyDamage);
                            newEnemy.setBaseHealth(enemyHealth);
                        }
                    }
                    ArrayList<Door> doors = room.getDoorList();
                    for (Door door : doors){
                        if (door.isPowerDoor()) ((PowerDoor) door).setPower(doorPower);
                    }
                }
        }
    }
}