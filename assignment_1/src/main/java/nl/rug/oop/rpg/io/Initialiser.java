package nl.rug.oop.rpg.io;

import nl.rug.oop.rpg.*;
import nl.rug.oop.rpg.doors.*;
import nl.rug.oop.rpg.npcs.*;
import nl.rug.oop.rpg.util.CatchNonInts;

import java.io.*;
import java.util.*;

/**
 * Class to store methods for creating a properties file,
 * as well as initializing a game from it.
 */
public class Initialiser {

    /**
     * Name a player.
     * @param printed Used to flag if the first print has
     *                been printed or not (it should not be
     *                printed after the first iteration)
     * @return The name of the player that the user sets.
     */
    private static String namePlayer(boolean printed){
        if(!printed) {
            System.out.println("Configure player's name (! to skip):");
        }
        String name = Main.scanner.nextLine();
        if (name.equals("!")){
            name = "Bob";
            return name;
        }
        if (SaveAndLoad.isAlphaNumeric(name) &&
                name.length() > 0 && name.length() < 21) {
            return name;
        }
        else {
            System.out.println("Name does not meet requirements, try again (! to skip):");
            namePlayer(true);
        }
        return name;
    }

    /**
     * Set the max health of a player according to 3 possible levels.
     * @param printed same as namePlayer().
     * @return Chosen max health.
     */
    private static int setMaxHealth(boolean printed){
        if(!printed){
            System.out.println("Set player's max health:\n" +
                    "(0) Hard: 50\n" +
                    "(1) Medium: 100\n" +
                    "(2) Easy: 150");
        }
        int maxHealth = 100;
        int chosen = CatchNonInts.inputOption();
        switch(chosen){
            case 0: maxHealth = 50; break;
            case 1: maxHealth = 100; break;
            case 2: maxHealth = 150; break;
            default: System.out.println("Not an option, please choose again"); setMaxHealth(true);
        }
        return maxHealth;
    }

    /**
     * Set the difficulty level of a game according to 3 possible levels.
     * @param printed same as namePlayer().
     * @return Chosen difficulty level.
     */
    private static int setNpcDifficulty(boolean printed) {
        if(!printed){
            System.out.println("Set NPC difficulty level:\n" +
                    "(0) Hard\n" +
                    "(1) Medium\n" +
                    "(2) Easy");
        }
        int chosen = CatchNonInts.inputOption();
        if (chosen<0 || chosen > 2){
            System.out.println("Not an option, please choose again");
            setNpcDifficulty(true);
        }
        else{
            return chosen;
        }
        return chosen;
    }

    /**
     * Set the extra health/damage of an enemy according to difficulty level.
     * @param diff Chosen difficulty from setNpcDifficulty()
     * @return Extra health/damage of the enemy.
     */
    private static int setExtraEnemyX(int diff){
        int extraEnemyX = 0;
        switch(diff){
            case 0: extraEnemyX = 0; break;
            case 1: extraEnemyX = 20; break;
            case 2: extraEnemyX = 40; break;
        }
        return extraEnemyX;
    }

    /**
     * Set the power of a powerDoor according to 3 possible levels.
     * @param printed same as namePlayer().
     * @return Chosen door power.
     */
    private static int setDoorPower(boolean printed){
        if(!printed){
            System.out.println("Set the power door's power level:\n" +
                    "(0) 10\n" +
                    "(1) 20\n" +
                    "(2) 30");
        }
        int doorPower = 20;
        int chosen =  CatchNonInts.inputOption();
        switch(chosen){
            case 0: doorPower = 10; break;
            case 1: doorPower = 20; break;
            case 2: doorPower = 30; break;
            default: System.out.println("Not an option, please choose again"); setDoorPower(true);
        }
        return doorPower;
    }

    /**
     * Create a properties file with the chosen
     * values of properties input by the user.
     * @param fileName same as namePlayer().
     */
    public static void createProperties(String fileName){
        Main.scanner = new Scanner(System.in);
        String playerName = namePlayer(false);
        int maxHealth = setMaxHealth(false);
        int diff = setNpcDifficulty(false);

        int extraEnemyDamage = setExtraEnemyX(diff);
        int extraEnemyHealth = setExtraEnemyX(diff) + 20;

        int doorPower = setDoorPower(false);

        File configDirectory = new File("config");
        configDirectory.mkdir();

        Properties gameProperties = new Properties();
        gameProperties.setProperty("playerName", playerName);
        gameProperties.setProperty("maxHealth", String.valueOf(maxHealth));
        gameProperties.setProperty("extraEnemyDamage", String.valueOf(extraEnemyDamage));
        gameProperties.setProperty("extraEnemyHealth", String.valueOf(extraEnemyHealth));
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

    /**
     * Initiate a game from a properties file.
     * @param fileName Properties file to be read from.
     * @param player Player to be modified based on properties.
     * @param rooms All the rooms in a game (to access doors and NPCs).
     * @throws IOException to CreateWorld class.
     */
    public static void initGameFromProps(String fileName, Player player, Room[] rooms) throws IOException {
        File configDirectory = new File("config");
        try (FileReader fileReader = new FileReader(configDirectory + File.separator +
                fileName + ".properties")){
            Properties gameProperties = new Properties();
            gameProperties.load(fileReader);
            String playerName = gameProperties.getProperty("playerName");
            int maxHealth = Integer.parseInt(gameProperties.getProperty("maxHealth"));
            int extraEnemyDamage = Integer.parseInt(gameProperties.getProperty("extraEnemyDamage"));
            int extraEnemyHealth = Integer.parseInt(gameProperties.getProperty("extraEnemyHealth"));
            int doorPower = Integer.parseInt(gameProperties.getProperty("doorPower"));
            player.setName(playerName);
            player.setMaxHealth(maxHealth);
            setDamageHealthPower(rooms, extraEnemyDamage, extraEnemyHealth, doorPower);
        }
    }

    private static void setDamageHealthPower(Room[] rooms, int ed, int eh, int dp){
        for (Room room : rooms){
            ArrayList<NPC> npcs = room.getNpcList();
            for (NPC npc : npcs){
                if (npc.isEnemy()){
                    Enemy enemy = (Enemy)npc;
                    enemy.setBaseDamage(ed);
                    enemy.setBaseHealth(eh);
                }
            }
            ArrayList<Door> doors = room.getDoorList();
            for (Door door : doors){
                if (door.isPowerDoor()) ((PowerDoor) door).setPower(dp);
            }
        }
    }
}