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

    public DementiaDude(String description, Room room){
        super(description, room);
        this.baseDamage += 30;
        this.baseHealth += 15;
        this.uniqueLine = "Uhh... where am I? Who are you?";
    }

    @Override
    protected void performAction(Player player) {
        this.takeDamage(player.dealDamage());
        if(!isDead){
            player.takeDamage(this.dealDamage());
            System.out.println("Enemy: I have no idea what I'm doing");
        }
    }
}
