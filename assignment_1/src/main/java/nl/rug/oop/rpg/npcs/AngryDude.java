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

    public AngryDude(String description, Room room){
        super(description, room);
        this.baseDamage += 20;
        this.baseHealth += 30;
        this.uniqueLine = "I'll hurt you!";
    }

    @Override
    protected void performAction(Player player) {
        System.out.println("Enemy: Grr... how dare you attack me?!");
        this.takeDamage(player.dealDamage());
        if(!isDead){
            player.takeDamage(this.dealDamage());
            System.out.println("Ouch! The angry dude attacked you back.");
        }
    }
}