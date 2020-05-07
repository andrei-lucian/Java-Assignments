package nl.rug.oop.rpg.doors;

import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.util.Heal;

/**
 * The healing door heals the player to max health.
 */
public class HealingDoor extends Door implements Heal {

    private static final long serialVersionUID = 21L;
    public HealingDoor(String description) {
        super(description);
    }

    @Override
    public void interact(Player player){
        player.setCurrentRoom(nextRoom);
        System.out.println("It's a healing door.");
        heal(player);
        System.out.println("You go through the door.");
    }

    public void heal(Player player) {
        player.increaseHealth();
    }
}
