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
        Room[] rooms = initiateWorld();
        player.setCurrentRoom(rooms[0]);

        //create a game with a player and run the game loop
        printConfigMenu();
        int option = Main.scanner.nextInt();
        switch(option){
            case 0: initiateWorld(); break;
            case 1: loadFromConfig(player, rooms); break;
            case 2: setDefaultConfig(); System.out.println("Default configuration set."); System.exit(0);
        }

        System.out.println("Hello ma dude, your name is: " + player.getName() +
                            "Your max health is " + player.getMaxHealth());

        Game game = new Game();
        game.gameLoop(player);
    }

    public static void printConfigMenu(){
        System.out.println("You are about to start the game, what do you want to do? \n" +
                "(0) Play normally \n" +
                "(1) Initialise from config \n" +
                "(2) Set default config ");
    }

    private static Room[] initiateWorld(){

        //create the entry room and set it as they player's current room
        Room room0 = new Room("a room dimly lit by a candle.");
        Room room1 = new Room("a concrete bunker.");
        Room room2 = new Room("a dark stone cave with water streams running down the walls.");
        Room room3 = new Room("a library filled with old books and scrolls.");
        Room room4 = new Room("an abandoned factory.");
        Room room5 = new Room("a cave with strange markings on the walls.");
        Room room6 = new Room("a room with shattered glass on the floor.");
        Room room7 = new Room("a room filled with computer equipment.");
        Room room8 = new Room("a fancy kitchen.");
        Room end = new Room("the end!");

        Door zero1 = new PowerDoor("A heavy-looking metal door with a rusty handle.");
        Door zero2 = new Door("A thick curtain of tangled green vines.");
        Door zero3 = new PowerDoor("A stronk door.");
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

        room0.addDoor(zero1);
        room0.addDoor(zero2);
        room0.addDoor(zero3);
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

        zero1.setNextRoom(room1);
        zero2.setNextRoom(room2);
        zero3.setNextRoom(room3);
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

        NPC entry_ad = new AngryDude("A short ginger dude that looks a little crazy.", room0);
        NPC entry_healer = new Healer("A blonde girl wearing Nike air force sneakers and drinking Starbucks coffee.", room0);
        NPC entry_coward = new Coward("A man wearing a black and yellow shirt.", room0);
        room0.addNpc(entry_ad);
        room0.addNpc(entry_healer);
        room0.addNpc(entry_coward);

        NPC room1_pm = new Peacemaker("A surfer dude with dreadlocks and a shell necklace", room1);
        NPC room1_dd = new DementiaDude("An elf with sharp ears and long braided black hair", room1);
        room1.addNpc(room1_pm);
        room1.addNpc(room1_dd);

        NPC room2_pm = new Peacemaker("An old lady wearing a rainbow tracksuit and giant sunglasses", room2);
        room2.addNpc(room2_pm);

        NPC room3_pm = new Peacemaker("A girl with short red hair and a leather bag", room3);
        NPC room3_dd = new DementiaDude("A centaur", room3);
        NPC room3_healer = new Healer("John Cena", room3);
        room3.addNpc(room3_pm);
        room3.addNpc(room3_dd);
        room3.addNpc(room3_healer);

        NPC room4_coward = new Coward("A blue smurf", room4);
        NPC room4_healer = new Healer("Grumpy cat",  room4);
        NPC room4_ad = new AngryDude("A giant squid", room4);
        room4.addNpc(room4_coward);
        room4.addNpc(room4_healer);
        room4.addNpc(room4_ad);

        NPC room5_ad1 = new AngryDude("A very large troll", room5);
        NPC room5_ad2 = new AngryDude("A horse-sized duck", room5);
        NPC room5_dd = new DementiaDude("Mark Zuckerberg", room5);
        NPC room5_coward = new Coward("A duck-sized horse", room5);
        room5.addNpc(room5_ad1);
        room5.addNpc(room5_ad2);
        room5.addNpc(room5_dd);
        room5.addNpc(room5_coward);

        NPC room6_dd = new DementiaDude("Perry the platypus", room6);
        NPC room6_healer = new Healer("A fluffy golden retriever", room6);
        NPC room6_coward = new Coward("A giant spider", room6);
        room6.addNpc(room6_dd);
        room6.addNpc(room6_healer);
        room6.addNpc(room6_coward);

        NPC room7_ad1 = new AngryDude("A man with a man-bun wearing a flannel shirt and glasses", room7);
        NPC room7_dd1 = new DementiaDude("Santa", room7);
        NPC room7_dd2 = new DementiaDude("Kanye West", room7);
        room7.addNpc(room7_ad1);
        room7.addNpc(room7_dd1);
        room7.addNpc(room7_dd2);

        NPC room8_ad1 = new AngryDude("Godzilla, but he's old now", room8);
        NPC room8_ad2 = new AngryDude("A purple goblin", room8);
        NPC room8_dd = new DementiaDude("Luigi", room8);
        NPC room8_coward = new Coward("Mario",  room8);
        room8.addNpc(room8_ad1);
        room8.addNpc(room8_ad2);
        room8.addNpc(room8_dd);
        room8.addNpc(room8_coward);
        return new Room[]{room0, room1, room2, room3, room4, room5, room6, room7, room8};
    }

    private static void loadFromConfig(Player player, Room[] rooms){
        try {
            Initialiser.initGameFromProps("gameProp", player, rooms);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setDefaultConfig(){
        Initialiser.createProperties("gameProp");
    }
}