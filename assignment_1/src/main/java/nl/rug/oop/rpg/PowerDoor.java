package nl.rug.oop.rpg;

public class PowerDoor extends Door{


    /**
     * set the description of door
     *
     * @param description description of door
     */
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