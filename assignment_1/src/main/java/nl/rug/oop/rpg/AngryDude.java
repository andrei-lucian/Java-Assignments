package nl.rug.oop.rpg;

/** this type of enemy always attacks back
 * until either it or the player is dead */

public class AngryDude extends Enemy {
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
        System.out.println("Enemy: ''Grr... how dare you attack me?!");
            if (this.health - player.getDamage() <= 0) {
                if(this.isDead()){
                    System.out.println("You defeated the angry dude.");
                    player.setExp(exp);
                    this.interact = -1;
                }
            }
            player.takeDamage(this.dealDamage());
            System.out.println("You: ''Ow! Take this!''");
            this.takeDamage(player.dealDamage());
            System.out.println("Imbecile!");
    }
}