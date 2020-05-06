package nl.rug.oop.rpg.npcs;

import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.Room;
import nl.rug.oop.rpg.util.Attackable;

public class Coward extends NPC {
    /**
     *@param exp experience that the class gives when it dies
     */
    private static final long serialVersionUID = 413L;

    public Coward(String description,Room room){
        super(description, room);
        this.uniqueLine = "Please don't hurt me!";
    }

    @Override
    public void interact(Player player) {
        System.out.println("The coward ran away. What a loser.");
        this.removeFromRoom(this.room);
        this.isDead = true;
    }
}