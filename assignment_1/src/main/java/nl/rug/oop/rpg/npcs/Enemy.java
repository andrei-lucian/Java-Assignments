package nl.rug.oop.rpg.npcs;
import nl.rug.oop.rpg.Main;
import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.Room;
import nl.rug.oop.rpg.util.Attackable;

public abstract class Enemy extends NPC implements Attackable {
    private static final long serialVersionUID = 41L;

    public int getBaseDamage() {
        return baseDamage;
    }

    protected int baseDamage = 20;
    protected int baseHealth = 100;

    /**
     * Constructor:
     * @param description enemy description
     * @param room the room in which the char is
     */
    Enemy(String description, Room room) {
        super(description, room);
        this.isEnemy = true;
    }

    /** enemy takes damage when it is
     * attacked and health is reduced */
    @Override
    public void takeDamage(int damage) {
        this.baseHealth = this.baseHealth - damage;
            System.out.println("Damage dealt: " + damage);
            if(this.baseHealth <= 0){
                this.isDead = true;
                removeFromRoom(this.room);
            }
            else {
                System.out.println("Enemy's health:  " + this.baseHealth);
            }
    }

    /** @return damage: the amount of damage to be dealt */
    @Override
    public int dealDamage() {
        System.out.println("Damage taken: " + baseDamage);
        return this.baseDamage;
    }

    protected abstract void performAction(Player player);

    public void interact(Player player) {
        System.out.println("Enemy: " + uniqueLine + "\n" +
                "(1) Attack \n(-1) Don't interact");
        this.interact = Main.scanner.nextInt();
        if (this.interact != -1) {
            while (this.interact != 1) {
                System.out.println("Not an option, please select again.\n" +
                        "(1) Attack \n(-1) Don't interact");
                this.interact = Main.scanner.nextInt();
                if(this.interact == -1){
                    return;
                }
            }
            this.currentlyInteracting = true;
            performAction(player);
            checkLoseCondition(player);
            if (!this.isDead){
                attackLoop(player);
            }
        }
    }

    private void checkLoseCondition(Player player){
        if (player.isDead()){
            System.out.println("You died, game over :(");
            System.exit(0);
        }
    }

    protected void attackLoop(Player player){
        while(this.currentlyInteracting && !this.isDead) {
            System.out.println("(1) Attack again \n(-1) Retreat");
            this.interact = Main.scanner.nextInt();
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

    public void setBaseHealth(int baseHealth) {
        this.baseHealth = baseHealth;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }
}