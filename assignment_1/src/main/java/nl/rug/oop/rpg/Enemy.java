package nl.rug.oop.rpg;

import java.util.Scanner;

public abstract class Enemy extends NPC implements Attackable{
    Scanner scanner = new Scanner(System.in);
    protected boolean isDead = false;

    /**
     * Constructor:
     * @param description enemy description
     * @param damage enemy damage
     * @param health enemy health
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
            System.out.println("Aww damn it, my health is now " + this.health);
        }
    }

    /** @return damage: the amount of damage to be dealt */
    @Override
    public int dealDamage() {
        System.out.println("Damage taken: " + this.damage);
        return this.damage;
    }

    /** @return if the enemy is dead */
    public boolean isDead(Room room){
        if(this.health <= 0){
            this.isDead = true;
            room.removeNPC(this);
        }
        return this.isDead;
    }

    @Override
    public abstract void interact(Player player);
}
