package nl.rug.oop.rpg;

public class PowerDoor extends Door{


    private final int extraDamage = 50;
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
        player.setDamage(player.getDamage() + extraDamage);
        System.out.println("The magic of the door gave you " +
                extraDamage + " extra damage.");
    }
}