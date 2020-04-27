package nl.rug.oop.rpg;

import java.util.Scanner;

public abstract class Enemy extends NPC implements Attackable{
    Scanner scanner = new Scanner(System.in);
    protected boolean isDead = false;
    protected int interact = 0;

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
            System.out.println("I died");
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

    @Override
    public abstract void interact(Player player);

    protected void printDialogue(String uniqueLine){
        System.out.println( "Enemy: " + uniqueLine + "\n"+
                "Attack? (1) (-1 : don't interact).");
    }

    protected abstract void performAction(Player player);
}
