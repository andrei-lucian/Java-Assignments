package nl.rug.oop.rpg;

public class Peacemaker extends Enemy{

    public Peacemaker(String description, int damage, int health){
        super(description, damage, health);
    }

    public void interact(Player player){
        System.out.println("Enemy: 'I'll hurt you!'\n" +
                "Attack? (1) (-1 : don't interact).");
        int attack = scanner.nextInt();
        if(attack != -1){
            this.takeDamage(player.dealDamage());
        }
    }
}
