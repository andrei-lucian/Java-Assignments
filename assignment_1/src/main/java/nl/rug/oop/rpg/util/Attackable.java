package nl.rug.oop.rpg.util;

/**
 * Makes a class attackable.
 * Implemented by Player and Enemy.
 */
public interface Attackable {
    void takeDamage(int damage);
    int dealDamage();
}
