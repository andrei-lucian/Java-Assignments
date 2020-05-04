package nl.rug.oop.rpg.npcs;

import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.Room;
import nl.rug.oop.rpg.util.Attackable;

public class Coward extends Enemy implements Attackable {
    /**
     *@param exp experience that the class gives when it dies
     */
    private static final int exp = 10;
    private static final long serialVersionUID = 413L;

    public Coward(String description,int damage, int health, Room room){
        super(description, damage, health, room);
        this.uniqueLine = "Please don't hurt me!";
    }

    @Override
    protected void performAction(Player player) {
        System.out.println("The coward ran away. What a loser.");
        this.removeFromRoom(this.room);
        this.isDead = true;
        player.setExp(exp);
    }
}