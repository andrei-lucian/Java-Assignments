package nl.rug.oop.rpg;
import java.util.Scanner;

public class Healer extends NPC{
    Scanner scanner = new Scanner(System.in);

    Healer(String description, int damage, int health, Room room) {
        super(description, damage, health, room);
    }

    @Override
    public void interact(Player player) {
        System.out.println("Hello ma dude, I'm a health wizard" +
                ", want some health? \n" +
                "(1) Heal \n(-1) Don't interact");
        this.interact = scanner.nextInt();
        if (this.interact != -1) {
            while (this.interact != 1) {
                System.out.println("Not an option, please select again.\n" +
                        "(1) Heal \n(-1) Don't interact");
                this.interact = scanner.nextInt();
            }
            if (this.interact==1){
                player.setHealth(player.getHealth() + 50);
                System.out.println("You've been healed by 50, your health is: " +
                        player.getHealth());
            }
        }
    }
}