package nl.rug.oop.rpg.npcs;
import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.Room;
import nl.rug.oop.rpg.util.Attackable;

import java.util.Random;

public class DementiaDude extends Enemy implements Attackable {
    /**
     * @param randInt random damage that the class deals
     * @param exp experience that the class gives when it dies
     */
    private static final long serialVersionUID = 412L;
    Random randInt = new Random();

    public DementiaDude(String description,int damage, int health, Room room){
        super(description, damage, health, room);
        this.uniqueLine = "Uhh... where am I? Who are you?";
    }

    @Override
    protected void performAction(Player player) {
        this.takeDamage(player.dealDamage());
        if(this.isDead){
            System.out.println("You defeated the dementia dude");
        }
        else {
            this.damage = randInt.nextInt(damage);
            player.takeDamage(this.dealDamage());
        }
    }
}
