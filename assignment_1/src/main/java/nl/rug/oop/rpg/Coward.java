package nl.rug.oop.rpg;

public class Coward extends Enemy implements Attackable{

    public Coward(String description,int damage, int health){
        super(description, damage, health);
    }

    public void interact(Player player){
        System.out.println("Enemy: I hope he doesn't see me.'\n" +
                "Attack? (1) (-1 : don't interact).");
        int attack = scanner.nextInt();
        if(attack != -1){
            if(attack == 1) {
                System.out.println("The enemy ran away.");
            }
            else{
                System.out.println("Not an option");
            }
        }
    }
}
