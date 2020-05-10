package nl.rug.oop.rpg.npcs;

import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.Room;
import nl.rug.oop.rpg.util.CatchNonInts;
import nl.rug.oop.rpg.util.Heal;

/** Healer heals a player to their maximum health */
public class Healer extends NPC implements Heal {

    private static final long serialVersionUID = 42L;

    public Healer(String description, Room room) {
        super(description, room);
    }

    /** Interact with a player */
    @Override
    public void interact(Player player) {
        System.out.println("Hello ma dude, I'm a health wizard" +
                ", want some health? \n" +
                "(1) Heal \n(-1) Don't interact");
        this.interact = CatchNonInts.inputOption();
        if (this.interact != -1) {
            while (this.interact != 1) {
                System.out.println("Not an option, try again");
                this.interact = CatchNonInts.inputOption();
                if(this.interact == -1){
                    return;
                }
            }
                heal(player);
        }
    }

    /** Increase a player's health */
    public void heal(Player player) {
        player.increaseHealth();
    }
}
