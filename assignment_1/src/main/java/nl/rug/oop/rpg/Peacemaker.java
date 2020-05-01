package nl.rug.oop.rpg;

import java.util.Scanner;

/** this type of enemy actually increases the health
 * of the player every time the player attacks it */

public class Peacemaker extends NPC {
    Scanner scanner = new Scanner(System.in);
    /**
     *@var exp experience that the class gives when it dies
     * @var recognized checks if the class has been seen before
     */
    private static final int exp = 5;

    public Peacemaker(String description, int damage, int health, Room room){
        super(description, damage, health, room);
    }

    @Override
    public void interact(Player player) {
        System.out.println("Do you want my heart? \n" +
                "(1) Take heart \n(-1) Don't interact");
        this.interact = scanner.nextInt();
        if (this.interact != -1) {
            while (this.interact != 1) {
                System.out.println("Not an option, please select again.\n" +
                        "(1) Take heart \n(-1) Don't interact");
                this.interact = scanner.nextInt();
                if(this.interact == -1){
                    return;
                }
            }
            player.setMaxHealth(25);
            removeFromRoom(player.getCurrentRoom());
            System.out.println("The peacemaker died. ");
        }
    }
}