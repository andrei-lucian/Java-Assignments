package nl.rug.oop.rpg;

/** this type of enemy actually increases the health
 * of the player every time the player attacks it */

public class Peacemaker extends Enemy{

    private boolean recognized = false;

    public Peacemaker(String description, int damage, int health){
        super(description, damage, health);
    }

    public void interact(Player player){
        System.out.println("Enemy! \n" +
                "Attack? (1) (-1 : don't interact).");
        int attack = scanner.nextInt();
        if(attack != -1){
            if (attack ==1) {
                this.takeDamage(player.dealDamage());
                player.increaseHealth(this.damage);

                if (!recognized) {
                    System.out.println("...what? My health increased when I attacked? " +
                            "This must be a peacemaker type!");
                    this.recognized = true;
                }
            }
            else {
                System.out.println("Not an option, please select again");
            }
        }
    }
}
