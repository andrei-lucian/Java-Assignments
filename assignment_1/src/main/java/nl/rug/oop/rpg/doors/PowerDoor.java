package nl.rug.oop.rpg.doors;

import nl.rug.oop.rpg.Player;

/**
 * PowerDoor increases the amount of damage that a player
 * can deal once the player passes through this door.
 */
public class PowerDoor extends Door{

    private final long serialVersionUID = 23L;
    private int power = 20;

    public PowerDoor(String description) {
        super(description);
        this.isPowerDoor = true;
    }

    public void setPower(int power) {
        this.power = power;
    }

    /**
     * Increase the player's damage.
     * @param player Player that goes through this door.
     */
    @Override
    public void interact(Player player){
        player.setCurrentRoom(nextRoom);
        player.setDamage(player.getDamage() + power);
        System.out.println("The magic of the door gave you " +
                power + " extra damage.");
    }
}