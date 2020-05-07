package nl.rug.oop.rpg.npcs;
import nl.rug.oop.rpg.Main;
import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.Room;
import nl.rug.oop.rpg.util.Heal;

import java.util.Scanner;

public class Healer extends NPC implements Heal {

    private static final long serialVersionUID = 42L;
    transient Scanner scanner = Main.scanner;

    public Healer(String description, Room room) {
        super(description, room);
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
                if(this.interact == -1){
                    return;
                }
            }
                heal(player);
        }
    }

    public void heal(Player player) {
        player.increaseHealth();
    }
}
