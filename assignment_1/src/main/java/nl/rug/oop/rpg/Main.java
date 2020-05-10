package nl.rug.oop.rpg;
import java.util.*;

public class Main {
    /** Main scanner used in all classes. */
    public static transient Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        Player player = new Player(70);
        Room[] rooms = CreateWorld.initiateWorld();
        player.setCurrentRoom(rooms[0]);

        CreateWorld.runConfigMenu(player, rooms);
        printGameRules();
        System.out.println("Hello " + player.getName());
        Game game = new Game();
        game.gameLoop(player);
    }

    private static void printGameRules(){
        System.out.println("\nWelcome to the game. \n" +
                "Your goal is to reach the end room.\n" +
                "There are multiple paths that you can take, and some might be easier than others. \n" +
                "Along the way you will find NPCs in the rooms, and it is up to you to figure out what they do.\n" +
                "Some doors work in your favour, and some are more difficult to get through.\n" +
                "Make sure to keep your health up! There are NPCs that can help with this.\n" +
                "If your health reaches below zero for any reason, you will die and the game will be over.\n" +
                "Good luck!\n");
    }
}