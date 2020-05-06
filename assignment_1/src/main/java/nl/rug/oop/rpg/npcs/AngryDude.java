package nl.rug.oop.rpg.npcs;

import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.Room;

/** this type of enemy always attacks back
 * until either it or the player is dead */

public class AngryDude extends Enemy {
    /**
     *@param exp experience that the class gives when it dies
     */
    private static final long serialVersionUID = 411L;
    private static final int exp = 50;

    public AngryDude(String description, Room room){
        super(description, room);
        this.uniqueLine = "I'll hurt you!";
    }

    @Override
    protected void performAction(Player player) {
        System.out.println("Enemy: Grr... how dare you attack me?!");
        this.takeDamage(player.dealDamage());
            if(this.isDead){
                System.out.println("You defeated the angry dude.");
            }
            else {
                System.out.println("Ouch! The angry dude attacked you back.");
                player.takeDamage(this.dealDamage());
            }
    }
}