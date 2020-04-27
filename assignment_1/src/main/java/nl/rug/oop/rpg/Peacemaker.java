package nl.rug.oop.rpg;

/** this type of enemy actually increases the health
 * of the player every time the player attacks it */

public class Peacemaker extends Enemy{
    /**
     *@var exp experience that the class gives when it dies
     * @var recognized checks if the class has been seen before
     */

    private boolean recognized = false;
    private static final int exp = 5;

    public Peacemaker(String description, int damage, int health, Room room){
        super(description, damage, health, room);
    }

    /** Check if you've seen this class before */
    private void checkIfRecognized(){
        if (!recognized) {
            System.out.println("...what? My health increased when I attacked? ");
            this.recognized = true;
        }
    }

    public void interact(Player player){
        printDialogue("I won't hurt you");
        int attack = scanner.nextInt();
        if(attack != -1){
            if (attack ==1) {
                this.takeDamage(player.dealDamage());
                player.increaseHealth(this.damage);
                checkIfRecognized();
                if(this.isDead()){
                    player.setExp(exp);
                }
            }
            else {
                System.out.println("Not an option, please select again");
            }
        }
    }
}