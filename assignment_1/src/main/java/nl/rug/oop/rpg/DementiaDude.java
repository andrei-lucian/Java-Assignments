package nl.rug.oop.rpg;
import java.util.Random;

public class DementiaDude extends Enemy implements Attackable{
    Random randInt = new Random();

    public DementiaDude(String description,int damage, int health){
        super(description, damage, health);
    }

    public void interact(Player player){
        System.out.println("Enemy: Where am I?'\n" +
                "Attack? (1) (-1 : don't interact).");
        int attack = scanner.nextInt();
        if(attack != -1){
            if(attack == 1) {
                this.takeDamage(player.dealDamage());
                this.damage = randInt.nextInt(50);
                player.takeDamage(this.dealDamage());
            }
            else{
                System.out.println("Not an option");
            }
        }
    }
}
