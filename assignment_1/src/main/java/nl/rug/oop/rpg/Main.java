package nl.rug.oop.rpg;
import nl.rug.oop.rpg.doors.Door;
import nl.rug.oop.rpg.doors.HealingDoor;
import nl.rug.oop.rpg.doors.LockedDoor;
import nl.rug.oop.rpg.doors.PowerDoor;
import nl.rug.oop.rpg.npcs.*;
import nl.rug.oop.rpg.io.Initialiser;

import java.io.IOException;
import java.util.*;

public class Main {
    /** Main scanner used in all classes. */
    public static transient Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        Player player = new Player(70);
        Room[] rooms = CreateWorld.initiateWorld();
        player.setCurrentRoom(rooms[0]);

        CreateWorld.runConfigMenu(player, rooms);

        System.out.println("Hello ma dude, your name is: " + player.getName());
        Game game = new Game();
        game.gameLoop(player);
    }
}