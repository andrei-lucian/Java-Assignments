package nl.rug.oop.rpg;

import java.util.ArrayList;

public class Enemy extends NPC implements Attackable{

    //list of types of attacks this enemy can carry out
    private ArrayList<AttackAction> possibleAttacks;
    protected int health;

    Enemy(String description, int damage, int health) {
        super(description);
        this.health = health;
        possibleAttacks = new ArrayList<>();
    }

    @Override
    public void takeDamage(int damage) {
        this.health = this.health - damage;
        System.out.println("Aww damn it, my health is now" + this.health);
    }

    @Override
    public int dealDamage() {
        System.out.println("Damage taken: " + this.damage);
        return this.damage;
    }

    @Override
    public void interact(Player player){
        System.out.println("Enemy: 'I'll hurt you!'");
    }
}
