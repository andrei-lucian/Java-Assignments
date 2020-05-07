package nl.rug.oop.rpg.npcs;
import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.Room;
import nl.rug.oop.rpg.util.Attackable;
import nl.rug.oop.rpg.util.CatchNonInts;

public abstract class Enemy extends NPC implements Attackable {
    private static final long serialVersionUID = 41L;
    protected int baseDamage = 0;
    protected int baseHealth = 70;

    /**
     * Constructor:
     * @param description enemy description
     * @param room the room in which the enemy is
     */
    Enemy(String description, Room room) {
        super(description, room);
        this.isEnemy = true;
    }

    public void setBaseHealth(int baseHealth) {
        this.baseHealth += baseHealth;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage += baseDamage;
    }

    /**
     * Kill an enemy.
     * @return boolean (if the enemy is dead or not).
     */
    protected boolean isDead(){
        System.out.println("The enemy is dead.");
        removeFromRoom(this.room);
        return isDead = true;
    }

    /**
     * Enemy takes damage and dies if its health reaches 0.
     * @param damage Damage taken.
     */
    @Override
    public void takeDamage(int damage) {
        this.baseHealth = this.baseHealth - damage;
            System.out.println("Damage dealt: " + damage);
            if(this.baseHealth <= 0){
                isDead();
            }
            else {
                System.out.println("Enemy's health: " + this.baseHealth);
            }
    }

    /**
     * @return baseDamage - the amount of damage to be dealt.
     */
    @Override
    public int dealDamage() {
        System.out.println("Damage taken: " + baseDamage);
        return this.baseDamage;
    }

    /**
     * Action is unique to each type of enemy.
     * @param player Player being interacted with.
     */
    protected abstract void performAction(Player player);

    /**
     * Exit game if player dies.
     * @param player Check if this player is dead.
     */
    private void checkLoseCondition(Player player){
        if (player.isDead()){
            System.out.println("You died, game over :(");
            System.exit(0);
        }
    }

    /**
     * Start an interaction with a player.
     * @param player Player being interacted with.
     */
    public void interact(Player player) {
        System.out.println("Enemy: " + uniqueLine + "\n" +
                "(1) Attack \n(-1) Don't interact");
        this.interact = CatchNonInts.inputOption();
        if (this.interact != -1) {
            while (this.interact != 1) {
                System.out.println("Not an option, try again.");
                this.interact = CatchNonInts.inputOption();
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

    /**
     * Execute the attack loop (allows multiple iterations of interaction).
     * @param player Player being interacted with.
     */
    protected void attackLoop(Player player){
        while(this.currentlyInteracting && !this.isDead) {
            System.out.println("(1) Attack again \n(-1) Retreat");
            this.interact = CatchNonInts.inputOption();
            if(this.interact != -1){
                if(this.interact != 1 ){
                    System.out.println("Not an option, try again.");
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