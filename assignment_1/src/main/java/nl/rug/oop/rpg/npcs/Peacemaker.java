package nl.rug.oop.rpg.npcs;

import nl.rug.oop.rpg.Main;
import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.Room;

import java.util.Scanner;

/** A peacemaker gives the player their heart to increase the player's
 * maximum health, then dies because it can only do this once. */

public class Peacemaker extends NPC {

    private static final long serialVersionUID = 43L;
    transient Scanner scanner = Main.scanner;

    public Peacemaker(String description, Room room){
        super(description, room);
    }

    /** increase the player's maximum health and remove from room*/
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