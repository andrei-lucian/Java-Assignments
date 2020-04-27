package nl.rug.oop.rpg;

public class Coward extends Enemy implements Attackable{
    /**
     *@param exp experience that the class gives when it dies
     */
    private static final int exp = 10;

    public Coward(String description,int damage, int health, Room room){
        super(description, damage, health, room);
    }

    public void interact(Player player){
        printDialogue();
        int attack = scanner.nextInt();
        if(attack != -1){
            if(attack == 1) {
                System.out.println("The enemy ran away.");
                this.removeFromRoom(this.room);
                player.setExp(exp);
            }
            else{
                System.out.println("Not an option");
            }
        }
    }

    @Override
    protected void printDialogue() {
        System.out.println("Enemy: I hope he doesn't see me.'\n" +
                "Attack? (1) (-1 : don't interact).");
    }
}
