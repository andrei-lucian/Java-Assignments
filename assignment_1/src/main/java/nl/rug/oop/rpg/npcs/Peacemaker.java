package nl.rug.oop.rpg.npcs;

import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.Room;

import java.util.Scanner;

/** this type of enemy actually increases the health
 * of the player every time the player attacks it */

public class Peacemaker extends NPC {

    private static final long serialVersionUID = 43L;
    transient Scanner scanner = new Scanner(System.in);

    public Peacemaker(String description, int damage, int health, Room room){
        super(description, damage, health, room);
    }

    @Override
    public void interact(Player player) {
        System.out.println("Do you want my heart? \n" +
                "(1) Take heart \n(-1) Don't interact");
        this.interact = scanner.nextInt();
        if (this.interact != -1) {
            while (this.interact != 1) {
                System.out.println("Not an option, please select again.\n" +
                        "(1) Take heart \n(-1) Don't interact");
                this.interact = scanner.nextInt();
                if(this.interact == -1){
                    return;
                }
            }
            player.increaseMaxHealth(25);
            removeFromRoom(player.getCurrentRoom());
            System.out.println("The peacemaker died. ");
        }
    }
}