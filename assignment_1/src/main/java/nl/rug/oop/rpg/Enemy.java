package nl.rug.oop.rpg;

import java.util.Scanner;

public abstract class Enemy extends NPC implements Attackable{

    Scanner scanner = new Scanner(System.in);
    /**
     * Constructor:
     * @param description enemy description
     * @param damage enemy damage
     * @param health enemy health
     */
    protected boolean isDead = false;

    Enemy(String description, int damage, int health) {
        super(description, damage, health);
    }

    /** enemy takes damage when it is
     * attacked and health is reduced */
    @Override
    public void takeDamage(int damage) {
        this.health = this.health - damage;
        System.out.println("Aww damn it, my health is now " + this.health);
    }

    /** @return damage: the amount of damage to be dealt */
    @Override
    public int dealDamage() {
        System.out.println("Damage taken: " + this.damage);
        return this.damage;
    }

    /** @return if the enemy is dead */
    public boolean isDead(){
        return this.isDead;
    }

    @Override
    public abstract void interact(Player player);
}
