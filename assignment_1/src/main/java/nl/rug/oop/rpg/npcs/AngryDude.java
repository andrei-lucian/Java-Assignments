package nl.rug.oop.rpg.npcs;

import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.Room;

/** An AngryDude attacks a player with a set amount of damage */

public class AngryDude extends Enemy {

    private static final long serialVersionUID = 411L;

    public AngryDude(String description, Room room){
        super(description, room);
        this.baseDamage += 20;
        this.baseHealth += 30;
        this.uniqueLine = "I'll hurt you!";
    }

    /** Attack a player back with set amount of damage if the player attacks */
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