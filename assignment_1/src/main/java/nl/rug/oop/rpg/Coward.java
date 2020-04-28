package nl.rug.oop.rpg;

public class Coward extends Enemy implements Attackable{
    /**
     *@param exp experience that the class gives when it dies
     */
    private static final int exp = 10;

    public Coward(String description,int damage, int health, Room room){
        super(description, damage, health, room);
    }

    @Override
    protected void performAction(Player player) {
        System.out.println("The enemy ran away.");
        this.removeFromRoom(this.room);
        player.setExp(exp);
    }
}