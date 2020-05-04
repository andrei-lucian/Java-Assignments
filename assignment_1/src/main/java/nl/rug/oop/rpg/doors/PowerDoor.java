package nl.rug.oop.rpg.doors;

import nl.rug.oop.rpg.Player;

public class PowerDoor extends Door{


    /**
     * set the description of door
     *
     * @param description description of door
     */
    private static final long serialVersionUID = 23L;
    public PowerDoor(String description) {
        super(description);
    }

    @Override
    public void interact(Player player){
        player.setCurrentRoom(nextRoom);
        player.setDamage(player.getDamage() + 20);
        System.out.println("The magic of the door gave you " +
                20 + " extra damage.");
    }
}