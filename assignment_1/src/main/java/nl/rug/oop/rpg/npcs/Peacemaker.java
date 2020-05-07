package nl.rug.oop.rpg.npcs;

import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.Room;
import nl.rug.oop.rpg.util.CatchNonInts;

/** A peacemaker gives the player their heart to increase the player's
 * maximum health, then dies because it can only do this once. */

public class Peacemaker extends NPC {

    private static final long serialVersionUID = 43L;

    public Peacemaker(String description, Room room){
        super(description, room);
    }

    /** increase the player's maximum health and remove from room*/
    @Override
    public void interact(Player player) {
        System.out.println("Do you want my heart? \n" +
                "(1) Take heart \n(-1) Don't interact");
        this.interact = CatchNonInts.inputOption();
        if (this.interact != -1) {
            while (this.interact != 1) {
                System.out.println("Not an option, try again");
                this.interact = CatchNonInts.inputOption();
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