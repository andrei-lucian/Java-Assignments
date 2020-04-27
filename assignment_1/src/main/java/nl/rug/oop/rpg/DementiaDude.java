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
    }

    /** This class deals a random amount of damage each time he attacks */
    public void interact(Player player){
        this.interact = 0;
        while(this.interact != -1){
            printDialogue("Where am I?");
            this.interact = scanner.nextInt();
            if(interact == 1) {
                this.takeDamage(player.dealDamage());
                if(this.isDead()){
                    player.setExp(exp);
                    interact = -1;
                }
                else {
                    this.damage = randInt.nextInt(50);
                    player.takeDamage(this.dealDamage());
                }
            }
            else{
                System.out.println("Not an option");
            }
        }
    }
}
