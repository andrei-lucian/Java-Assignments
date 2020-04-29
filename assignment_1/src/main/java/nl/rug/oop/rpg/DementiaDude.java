package nl.rug.oop.rpg;
import java.util.Random;

public class DementiaDude extends Enemy implements Attackable{
    /**
     * @param randInt random damage that the class deals
     * @param exp experience that the class gives when it dies
     */
    Random randInt = new Random();
    private static final int exp = 50;

    public DementiaDude(String description,int damage, int health, Room room){
        super(description, damage, health, room);
        this.uniqueLine = "Uhh... where am I? Who are you?";
    }

    @Override
    protected void performAction(Player player) {
        this.takeDamage(player.dealDamage());
        if(this.isDead){
            player.setExp(exp);
            this.interact  = -1;
        }
        else {
            this.damage = randInt.nextInt(50);
            player.takeDamage(this.dealDamage());
        }
    }
}
