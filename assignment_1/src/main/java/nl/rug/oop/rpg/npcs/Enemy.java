package nl.rug.oop.rpg.npcs;

import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.Room;
import nl.rug.oop.rpg.util.Attackable;

import java.util.Scanner;

public abstract class Enemy extends NPC implements Attackable {
    private static final long serialVersionUID = 41L;
    transient Scanner scanner = new Scanner(System.in);
    protected boolean isDead = false;
    protected String uniqueLine;

    /**
     * Constructor:
     * @param description enemy description
     * @param damage enemy damage
     * @param health enemy health
     * @param room the room in which the char is
     */
    Enemy(String description, int damage, int health, Room room) {
        super(description, damage, health, room);
    }

    /** enemy takes damage when it is
     * attacked and health is reduced */
    @Override
    public void takeDamage(int damage) {
        this.health = this.health - damage;
            System.out.println("Damage dealt: " + damage);
            if(this.health <= 0){
                this.isDead = true;
                removeFromRoom(this.room);
            }
            else {
                System.out.println("Enemy's health:  " + this.health);
            }
    }

    /** @return damage: the amount of damage to be dealt */
    @Override
    public int dealDamage() {
        System.out.println("Damage taken: " + this.damage);
        return this.damage;
    }

    protected abstract void performAction(Player player);

    public void interact(Player player) {
        System.out.println("Enemy: " + uniqueLine + "\n" +
                "(1) Attack \n(-1) Don't interact");
        this.interact = scanner.nextInt();
        if (this.interact != -1) {
            while (this.interact != 1) {
                System.out.println("Not an option, please select again.\n" +
                        "(1) Attack \n(-1) Don't interact");
                this.interact = scanner.nextInt();
            }
            if (this.interact==1){
                this.currentlyInteracting = true;
                performAction(player);
                if (!this.isDead){
                    attackLoop(player);
                }
            }
        }
    }

    protected void attackLoop(Player player){
        while(this.currentlyInteracting && !this.isDead) {
            System.out.println("(1) Attack again \n(-1) Retreat");
            this.interact = scanner.nextInt();
            if(this.interact != -1){
                if(this.interact != 1 ){
                    System.out.println("Not an option, please select again.");
                    attackLoop(player);
                }
                else {
                    performAction(player);
                }
            }
            else {
                this.currentlyInteracting = false;
                System.out.println("You retreated away from the enemy.");
            }
        }
    }
}
