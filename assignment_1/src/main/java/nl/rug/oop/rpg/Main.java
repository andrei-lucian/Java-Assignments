package nl.rug.oop.rpg;
import java.util.*;

public class Main {

    private static Room[] initRooms(){

        Room entry = new Room("A room lit by a candle with a door on one side and a tunnel on the other");

        Room[] rooms = {entry};
        return rooms;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a name for your player");
        String name = scanner.nextLine();
        Player player = new Player(name);
        Room entry = new Room("a room lit by a candle with a door on one side and a tunnel on the other");
        player.setCurrentRoom(entry);

        while(true) {
            System.out.println("What do you want to do? \n (0) Look around");
            int option = scanner.nextInt();
            if (option == 0) {
                player.inspect();
            }
        }
    }
}