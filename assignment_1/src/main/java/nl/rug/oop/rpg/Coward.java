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
        while(interact != -1){
            printDialogue("I hope he doesn't see me");
            this.interact = scanner.nextInt();
            if(interact == 1) {
                System.out.println("The enemy ran away.");
                this.removeFromRoom(this.room);
                player.setExp(exp);
                interact = -1;
            }
            else{
                System.out.println("Not an option");
            }
        }
    }
}
