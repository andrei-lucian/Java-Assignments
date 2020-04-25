package nl.rug.oop.rpg;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        //initialise a player object with a name
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a name for your player");
        String name = scanner.nextLine();
        Player player = new Player(name);

        //create the entry room and set it as they player's current room
        Room entry = new Room("a room lit by a candle with a door on one side and a tunnel on the other");
        player.setCurrentRoom(entry);
        Room room_0 = new Room("room zero");
        Room room_1 = new Room("room one");
        Room room_2 = new Room("room two");
        Door entry_0 = new Door("A dark, shiny mahogany door");
        Door entry_1 = new Door("A heavy-looking metal door with a rusty handle");
        Door entry_2 = new Door("A thick curtain of tangled green vines");
        Npc npc_1 = new Npc("Ginger dude");
        Npc npc_2 = new Npc("Blonde bitch with air force's on");
        Npc npc_3 = new Npc("Dude with a black and yellow shirt");
        entry.addDoor(entry_0);
        entry.addDoor(entry_1);
        entry.addDoor(entry_2);
        entry.addNPC(npc_1);
        entry.addNPC(npc_2);
        entry.addNPC(npc_3);
        entry_0.setNextRoom(room_0);
        entry_1.setNextRoom(room_1);
        entry_2.setNextRoom(room_2);

        //create a game with a player and run the game loop
        Game game = new Game(player);
        game.gameLoop();
    }
}