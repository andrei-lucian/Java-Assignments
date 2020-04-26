package nl.rug.oop.rpg;

public class Enemy extends Npc implements Attackable{

    Enemy(String description, int damage, int health) {
        super(description, damage, health);
    }

    @Override
    public void takeDamage(int damage) {
        this.health = this.health - damage;
        System.out.println("Aww" + this.health);
    }

    @Override
    public int dealDamage() {
        System.out.println("Damage taken: " + this.damage);
        return this.damage;
    }

    @Override
    public void interact(Player player){
        System.out.println("I'll hurt you");
    }
}
