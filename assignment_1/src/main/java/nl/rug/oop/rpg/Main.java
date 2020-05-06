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
    public static transient Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){

        Player player = new Player(70);
        Initialiser.createProperties("gameProp");

        //create a game with a player and run the game loop
        printConfigMenu();
        int option = Main.scanner.nextInt();
        switch(option){
            case 0: initiateWorld(player);
            case 1: initiateWorld(player); loadFromConfig(player);
            case 2: ;
        }

        Game game = new Game();
        game.gameLoop(player);

    }

    private static void loadFromConfig(Player player){
        try {
            Initialiser.initGameFromProps("gameProp", player);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printConfigMenu(){
        System.out.println("You are about to start the game, what do you want to do? \n" +
                "(0) Play normally \n" +
                "(1) Initialise from config \n" +
                "(2) Set default config ");
    }

    private static void initiateWorld(Player player){

        //create the entry room and set it as they player's current room
        Room entry = new Room("a room dimly lit by a candle.");
        player.setCurrentRoom(entry);
        Room room1 = new Room("a concrete bunker.");
        Room room2 = new Room("a dark stone cave with water streams running down the walls.");
        Room room3 = new Room("a library filled with old books and scrolls.");
        Room room4 = new Room("an abandoned factory.");
        Room room5 = new Room("a cave with strange markings on the walls.");
        Room room6 = new Room("a room with shattered glass on the floor.");
        Room room7 = new Room("a room filled with computer equipment.");
        Room room8 = new Room("a fancy kitchen.");
        Room end = new Room("the end!");

        Door e1 = new PowerDoor("A heavy-looking metal door with a rusty handle.");
        Door e2 = new Door("A thick curtain of tangled green vines.");
        Door e3 = new PowerDoor("A stronk door.");
        Door one1 = new Door("A wooden door");
        Door one2 = new HealingDoor("A door");
        Door two1 = new HealingDoor("A white opalescent door");
        Door two2 = new PowerDoor("A muddy underground tunnel");
        Door three1 = new PowerDoor("A sliding glass door with cracks in it");
        Door four1 = new Door("A blue door with graffiti on it - you hear music from the other side");
        Door four2 = new Door("A tall gate with spikes on the top");
        Door five1 = new LockedDoor("A majestic marble archway");
        Door six1 = new Door("A red mahogany door");
        Door seven1 = new LockedDoor("An extremely boring beige-painted door");
        Door eight1 = new LockedDoor("A bright yellow door with sunbeams coming out from under it");

        entry.addDoor(e1);
        entry.addDoor(e2);
        entry.addDoor(e3);
        room1.addDoor(one1);
        room1.addDoor(one2);
        room2.addDoor(two1);
        room2.addDoor(two2);
        room3.addDoor(three1);
        room4.addDoor(four1);
        room4.addDoor(four2);
        room5.addDoor(five1);
        room6.addDoor(six1);
        room7.addDoor(seven1);
        room8.addDoor(eight1);

        e1.setNextRoom(room1);
        e2.setNextRoom(room2);
        e3.setNextRoom(room3);
        one1.setNextRoom(room4);
        one2.setNextRoom(room3);
        two1.setNextRoom(room5);
        two2.setNextRoom(room6);
        three1.setNextRoom(room7);
        four1.setNextRoom(room2);
        four2.setNextRoom(room8);
        five1.setNextRoom(end);
        six1.setNextRoom(room5);
        seven1.setNextRoom(end);
        eight1.setNextRoom(end);

        NPC entry_ad = new AngryDude("A short ginger dude that looks a little crazy.", 40, 200, entry);
        NPC entry_healer = new Healer("A blonde girl wearing Nike air force sneakers and drinking Starbucks coffee.", 0, 0, entry);
        NPC entry_coward = new Coward("A man wearing a black and yellow shirt.", 100, 200, entry);
        entry.addNpc(entry_ad);
        entry.addNpc(entry_healer);
        entry.addNpc(entry_coward);

        NPC room1_pm = new Peacemaker("A surfer dude with dreadlocks and a shell necklace", 0, 50, room1);
        NPC room1_dd = new DementiaDude("An elf with sharp ears and long braided black hair", 50, 120, room1);
        room1.addNpc(room1_pm);
        room1.addNpc(room1_dd);

        NPC room2_pm = new Peacemaker("An old lady wearing a rainbow tracksuit and giant sunglasses", 0, 0, room2);
        room2.addNpc(room2_pm);

        NPC room3_pm = new Peacemaker("A girl with short red hair and a leather bag", 0, 10, room3);
        NPC room3_dd = new DementiaDude("A centaur", 45, 95, room3);
        NPC room3_healer = new Healer("John Cena", 0, 10, room3);
        room3.addNpc(room3_pm);
        room3.addNpc(room3_dd);
        room3.addNpc(room3_healer);

        NPC room4_coward = new Coward("A blue smurf", 0, 10, room4);
        NPC room4_healer = new Healer("Grumpy cat", 0, 10, room4);
        NPC room4_ad = new AngryDude("A giant squid", 32, 150, room4);
        room4.addNpc(room4_coward);
        room4.addNpc(room4_healer);
        room4.addNpc(room4_ad);

        NPC room5_ad1 = new AngryDude("A very large troll", 30, 210, room5);
        NPC room5_ad2 = new AngryDude("A horse-sized duck", 40, 160, room5);
        NPC room5_dd = new DementiaDude("Mark Zuckerberg", 10, 123, room5);
        NPC room5_coward = new Coward("A duck-sized horse", 0, 10, room5);
        room5.addNpc(room5_ad1);
        room5.addNpc(room5_ad2);
        room5.addNpc(room5_dd);
        room5.addNpc(room5_coward);

        NPC room6_dd = new DementiaDude("Perry the platypus", 40, 200, room6);
        NPC room6_healer = new Healer("A fluffy golden retriever", 0, 10,room6);
        NPC room6_coward = new Coward("A giant spider", 0, 10, room6);
        room6.addNpc(room6_dd);
        room6.addNpc(room6_healer);
        room6.addNpc(room6_coward);

        NPC room7_ad1 = new AngryDude("A man with a man-bun wearing a flannel shirt and glasses", 20,100,room7);
        NPC room7_dd1 = new DementiaDude("Santa", 20, 100, room7);
        NPC room7_dd2 = new DementiaDude("Kanye West", 40, 100, room7);
        room7.addNpc(room7_ad1);
        room7.addNpc(room7_dd1);
        room7.addNpc(room7_dd2);

        NPC room8_ad1 = new AngryDude("Godzilla, but he's old now", 12, 120, room8);
        NPC room8_ad2 = new AngryDude("A purple goblin", 35, 80, room8);
        NPC room8_dd = new DementiaDude("Luigi", 25, 100, room8);
        NPC room8_coward = new Coward("Mario", 0, 90, room8);
        room8.addNpc(room8_ad1);
        room8.addNpc(room8_ad2);
        room8.addNpc(room8_dd);
        room8.addNpc(room8_coward);
    }
}