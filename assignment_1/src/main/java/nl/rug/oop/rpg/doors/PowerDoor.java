package nl.rug.oop.rpg.doors;

import nl.rug.oop.rpg.Player;

public class PowerDoor extends Door{
    /**
     * set the description of door
     *
     * @param description description of door
     */

    private final long serialVersionUID = 23L;

    private int power = 20;

    public PowerDoor(String description) {
        super(description);
        this.isPowerDoor = true;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public void interact(Player player){
        player.setCurrentRoom(nextRoom);
        player.setDamage(player.getDamage() + power);
        System.out.println("The magic of the door gave you " +
                power + " extra damage.");
    }
}