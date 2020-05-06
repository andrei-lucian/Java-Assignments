package nl.rug.oop.rpg;
import nl.rug.oop.rpg.io.SaveAndLoad;
import nl.rug.oop.rpg.io.Serializer;

public class Game {

    private boolean exitGame = false;

    /** Executes game loop */
    public void gameLoop(Player player) {
        while (!exitGame) {
            checkWinCondition(player);
            printMenu();
            int option = Main.scanner.nextInt();
            switch(option){
                case 0: player.getCurrentRoom().inspect(); gameLoop(player);
                case 1: player.selectDoor(); gameLoop(player);
                case 2: player.selectNPC(); gameLoop(player);
                case 3: Serializer.savePlayer(player, "quicksave"); gameLoop(player);
                case 4: player = SaveAndLoad.loadPlayer(player, "quicksave"); gameLoop(player);
                case 5: SaveAndLoad.customSave(player); gameLoop(player);
                case 6: player = SaveAndLoad.customLoad(player); gameLoop(player);
                case 7: exitGame();
            }
        }
    }

    private void checkWinCondition(Player player){
        if (player.getCurrentRoom().getDescription().equals("the end!")){
            System.out.println("You win the game.");
            System.exit(0);
        }
    }

    /** prints interaction menu */
    private void printMenu() {
            System.out.println("What do you want to do? \n" +
                    "(0) Look around \n" +
                    "(1) Look for a way out \n" +
                    "(2) Look for company \n" +
                    "(3) Quicksave \n" +
                    "(4) Quickload \n" +
                    "(5) Save \n" +
                    "(6) Load \n" +
                    "(7) Exit the game");
    }

    private void exitGame(){
        System.out.println("You exited the game.");
        System.exit(0);
        exitGame = true;
    }
}