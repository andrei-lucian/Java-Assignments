package nl.rug.oop.rpg.io;

import nl.rug.oop.rpg.Main;
import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.Room;
import nl.rug.oop.rpg.doors.Door;
import nl.rug.oop.rpg.doors.PowerDoor;
import nl.rug.oop.rpg.npcs.Enemy;
import nl.rug.oop.rpg.npcs.NPC;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Initialiser {

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

    private static int setMaxHealth(boolean printed){
        if(!printed){
            System.out.println("Set player's max health:\n" +
                    "(0) Hard: 50\n" +
                    "(1) Medium: 100\n" +
                    "(2) Easy: 150");
        }
        int maxHealth = 100;
        int chosen = Main.scanner.nextInt();
        switch(chosen){
            case 0: maxHealth = 50; break;
            case 1: maxHealth = 100; break;
            case 2: maxHealth = 150; break;
            default: System.out.println("Not an option, please choose again"); setMaxHealth(true);
        }
        return maxHealth;
    }

    private static int setNpcDifficulty(boolean printed) {
        if(!printed){
            System.out.println("Set NPC difficulty level:\n" +
                    "(0) Hard\n" +
                    "(1) Medium\n" +
                    "(2) Easy");
        }
        int chosen = Main.scanner.nextInt();
        if (chosen<0 || chosen > 2){
            System.out.println("Not an option, please choose again");
            setNpcDifficulty(true);
        }
        else{
            return chosen;
        }
        return chosen;
    }

    private static int setExtraEnemyDamage(int diff){
        int extraEnemyDamage = 0;
        switch(diff){
            case 0: extraEnemyDamage = 0; break;
            case 1: extraEnemyDamage = 10; break;
            case 2: extraEnemyDamage = 20; break;
        }
        return extraEnemyDamage;
    }

    private static int setExtraEnemyHealth(int diff){
        int extraEnemyHealth = 0;
        switch(diff){
            case 0: extraEnemyHealth = 0; break;
            case 1: extraEnemyHealth = 10; break;
            case 2: extraEnemyHealth = 20; break;
        }
        return extraEnemyHealth;
    }

    private static int setDoorPower(boolean printed){
        if(!printed){
            System.out.println("Set the power door's power level:\n" +
                    "(0) 10\n" +
                    "(1) 20\n" +
                    "(2) 30");
        }
        int doorPower = 20;
        int chosen = Main.scanner.nextInt();
        switch(chosen){
            case 0: doorPower = 10; break;
            case 1: doorPower = 20; break;
            case 2: doorPower = 30; break;
            default: System.out.println("Not an option, please choose again"); setDoorPower(true);
        }
        return doorPower;
    }

    public static void createProperties(String fileName){
        Main.scanner = new Scanner(System.in);
        String playerName = namePlayer(false);
        int maxHealth = setMaxHealth(false);
        int diff = setNpcDifficulty(false);
        int enemyDamage = setExtraEnemyDamage(diff);
        int enemyHealth = setExtraEnemyHealth(diff);
        int doorPower = setDoorPower(false);

        File configDirectory = new File("config");
        configDirectory.mkdir();

        Properties gameProperties = new Properties();
        gameProperties.setProperty("playerName", playerName);
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