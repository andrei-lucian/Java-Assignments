package nl.rug.oop.rpg;

/**
 * class for various types of attack actions
 * that can be linked to an enemy
 */

public class AttackAction {
    private String description;
    private int amountOfDamage;

    public AttackAction(String description, int amountOfDamage) {
        this.description = description;
        this.amountOfDamage = amountOfDamage;
    }

    public int getAmountOfDamage(){
        return amountOfDamage;
    }
}
