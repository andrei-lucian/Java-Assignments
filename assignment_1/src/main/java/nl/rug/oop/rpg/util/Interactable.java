package nl.rug.oop.rpg.util;

import nl.rug.oop.rpg.Player;

/**
 * Makes a class interactable.
 * Implemented by Door and NPC.
 */
public interface Interactable {
    void interact(Player player);
}
