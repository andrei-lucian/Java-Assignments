package nl.rug.oop.rpg;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        //initialise a player object with a name
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a name for your player");
        String name = scanner.nextLine();
        Player player = new Player(name, 70);

        //create a game with a player and run the game loop
        initiateWorld(player);
        Game game = new Game();
        game.gameLoop(player);
    }

    private static void initiateWorld(Player player){

        //create the entry room and set it as they player's current room
        Room entry = new Room("a room lit by a candle with a door on one side and a tunnel on the other.");
        player.setCurrentRoom(entry);
        Room room1 = new Room("room 1.");
        Room room2 = new Room("room 2.");
        Room room3 = new Room("room 3.");
        Room room4 = new Room("room 4.");
        Room room5 = new Room("room 5.");
        Room room6 = new Room("room 6.");
        Room room7 = new Room("room 7.");
        Room room8 = new Room("room 8.");
        Room end = new Room("the end.");

        Door e1 = new Door("A heavy-looking metal door with a rusty handle.");
        Door e2 = new Door("A thick curtain of tangled green vines.");
        Door e3 = new PowerDoor("A stronk door.");
        Door one1 = new LockedDoor("A wooden door");
        Door two1 = new Door("A white opalescent door");
        Door two2 = new Door("A muddy underground tunnel");
        Door three1 = new Door("A sliding glass door with cracks in it");
        Door four1 = new Door("A blue door with graffiti on it - you hear music from the other side");
        Door four2 = new Door("A tall gate with spikes on the top");
        Door five1 = new Door("A majestic marble archway");
        Door six1 = new Door("A red mahogany door");
        Door seven1 = new Door("An extremely boring beige-painted door");
        Door eight1 = new Door("A bright yellow door with sunbeams coming out from under it");

        entry.addDoor(e1);
        entry.addDoor(e2);
        entry.addDoor(e3);
        room1.addDoor(one1);
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
        two1.setNextRoom(room5);
        two2.setNextRoom(room6);
        three1.setNextRoom(room7);
        four1.setNextRoom(end);
        four2.setNextRoom(room8);
        five1.setNextRoom(end);
        six1.setNextRoom(room5);
        seven1.setNextRoom(end);
        eight1.setNextRoom(end);

        NPC entry_enemy_1 = new AngryDude("A short ginger dude that looks a little crazy.", 40, 200, entry);
        NPC entry_healer = new Healer("A blonde girl wearing Nike air force sneakers and drinking Starbucks coffee.", 76, 24, entry);
        NPC entry_enemy_2 = new Coward("A man wearing a black and yellow shirt.", 100, 200, entry);
        entry.addNpc(entry_enemy_1);
        entry.addNpc(entry_healer);
        entry.addNpc(entry_enemy_2);

        NPC room1_coward = new Coward("A very large troll", 0, 0, room1);
        NPC room1_enemy1 = new AngryDude("An elf with sharp ears and long braided black hair", 50, 150, room1);
        room1.addNpc(room1_coward);
        room1.addNpc(room1_enemy1);

        NPC room2_healer = new Healer("An old lady wearing a rainbow tracksuit and giant sunglasses", 0, 0, room2);
        room2.addNpc(room2_healer);

        NPC room3_enemy1 = new AngryDude("A girl with short red hair and a leather bag", 20, 130, room3);
        NPC room3_enemy2 = new DementiaDude("A centaur", 40, 80, room3);
        NPC room3_enemy3 = new DementiaDude("John Cena", 80, 150, room3);
        room3.addNpc(room3_enemy1);
        room3.addNpc(room3_enemy2);
        room3.addNpc(room3_enemy3);

        NPC room4_coward1 = new Coward("A blue smurf", 0, 10, room4);
        NPC room4_coward2 = new Coward("Grumpy cat", 0, 10, room4);
        room4.addNpc(room4_coward1);
        room4.addNpc(room4_coward2);

        NPC room6_enemy = new DementiaDude("Perry the platypus", 30, 200, room6);
        NPC room6_healer = new Healer("A fluffy golden retriever", 0, 10,room6);
        NPC room6_coward = new Coward("A giant spider", 0, 10, room6);
        room6.addNpc(room6_enemy);
        room6.addNpc(room6_healer);
        room6.addNpc(room6_coward);

        NPC room7_healer = new Healer("A man with a man-bun wearing a flannel shirt and glasses", 10,10,room7);
        room7.addNpc(room7_healer);

        NPC room8_angrydude = new AngryDude("Godzilla, but he's old now", 50, 170, room8);
        NPC room8_dementiadude = new DementiaDude("Luigi", 30, 90, room8);
        room8.addNpc(room8_angrydude);
        room8.addNpc(room8_dementiadude);
    }
}