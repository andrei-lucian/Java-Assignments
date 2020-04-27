package nl.rug.oop.rpg;

public class Enemy extends NPC implements Attackable{

    /**
     * Constructor:
     * @param description enemy description
     * @param damage enemy damage
     * @param health enemy health
     */
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

    /** @return damage: the amount of damage to be dealt */
    @Override
    public void interact(Player player){
        System.out.println("Enemy: 'I'll hurt you!'\n" +
                "Attack? (1) (-1 : don't interact).");
    }

    @Override
    public boolean isFriendly() {
        return false;
    }
}
