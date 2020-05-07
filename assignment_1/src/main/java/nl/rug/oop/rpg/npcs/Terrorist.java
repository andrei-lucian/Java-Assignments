package nl.rug.oop.rpg.npcs;

import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.Room;

/** A terrorist explodes as soon as you interact with it */

public class Terrorist extends Enemy {
    public Terrorist(String description, Room room){
        super(description, room);
        this.baseDamage += 10;
        this.uniqueLine = "ALLAHU AKBAR!!111!1!1! *yeet*";
    }

    @Override
    protected void performAction(Player player){
        System.out.println("KABOOM! The suicide bomber exploded and dealt " + this.baseDamage + " damage.");
        player.takeDamage(this.baseDamage);
        this.isDead();
    }
}
