package nl.rug.oop.rpg;

import java.util.Scanner;

public abstract class Enemy extends NPC implements Attackable{
    Scanner scanner = new Scanner(System.in);
    protected boolean isDead = false;
    protected int interact = 0;
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
        if(this.health <= 0){
            System.out.println("You killed the enemy!");
        }
        else {
            System.out.println("Enemy: Aww damn it, my health is now " + this.health);
        }
    }

    /** @return damage: the amount of damage to be dealt */
    @Override
    public int dealDamage() {
        System.out.println("Damage taken: " + this.damage);
        return this.damage;
    }

    /** Remove npc from room*/
    protected void removeFromRoom(Room room){
        room.removeNPC(this);
    }

    /** @return the status of enemy and remove from room if health smaller than 0*/
    public boolean isDead(){
        if(this.health <= 0){
            this.isDead = true;
            removeFromRoom(this.room);
        }
        return this.isDead;
    }

    protected abstract void performAction(Player player);

    public void interact(Player player){
        System.out.println( "Enemy: " + uniqueLine + "\n"+
                "Attack? (1) (-1 : don't interact).");
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
