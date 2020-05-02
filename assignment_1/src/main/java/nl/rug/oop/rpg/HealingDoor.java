package nl.rug.oop.rpg;

public class HealingDoor extends Door implements Heal{
    /**
     * set the description of door
     *
     * @param description
     */
    public HealingDoor(String description) {
        super(description);
    }

    @Override
    public void interact(Player player){
        player.setCurrentRoom(nextRoom);
        System.out.println("It's a healing door.");
        heal(player);
        System.out.println("You go through the door.");
    }

    public void heal(Player player) {
        player.increaseHealth();
    }
}
