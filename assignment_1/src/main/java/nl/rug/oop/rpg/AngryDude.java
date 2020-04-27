package nl.rug.oop.rpg;

/** this type of enemy always attacks back
 * until either it or the player is dead */

public class AngryDude extends Enemy implements Attackable {
    /**
     *@param exp experience that the class gives when it dies
     */

    private static final int exp = 50;
    public AngryDude(String description, int damage, int health, Room room){
        super(description, damage, health, room);
    }

    @Override
    protected void performAction(Player player) {
        this.takeDamage(player.dealDamage());
        System.out.println("Enemy: ''Grr... how dare you attack me?!" +
                " I'll stop at nothing to kill you!'' \n " +
                "You are now stuck in a loop, either kill the enemy or die. ");
        while (this.health != 0 || player.getHealth() != 0) {
            if (this.health - player.getDamage() <= 0) {
                if(this.isDead()){
                    System.out.println("You defeated the angry dude.");
                    player.setExp(exp);
                    this.interact = -1;
                    break;
                }
            }
            player.takeDamage(this.dealDamage());
            System.out.println("You: ''Ow! Take this!''");
            this.takeDamage(player.dealDamage());
            System.out.println("Imbecile!");
        }
    }

    public void interact(Player player){
        printDialogue("I'll hurt you");
        this.interact = scanner.nextInt();
        if(this.interact != -1){
            if (this.interact == 1) {
                performAction(player);
            }
            else {
                System.out.println("Not an option, please select again");
                interact(player);
            }
        }
    }
}