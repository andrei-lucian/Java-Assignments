package nl.rug.oop.rpg.npcs;

import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.Room;

/** This NPC just runs away if the player attacks it */

public class Coward extends NPC {
    private static final long serialVersionUID = 413L;

    public Coward(String description,Room room){
        super(description, room);
        this.uniqueLine = "Please don't hurt me!";
    }

    /** Run away if attacked */
    @Override
    public void interact(Player player) {
        System.out.println("The coward ran away. What a loser.");
        this.removeFromRoom(this.room);
        this.isDead = true;
    }
}