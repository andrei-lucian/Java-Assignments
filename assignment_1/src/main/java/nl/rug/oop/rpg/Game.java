package nl.rug.oop.rpg;
import nl.rug.oop.rpg.io.SaveAndLoad;
import nl.rug.oop.rpg.io.Serializer;
import nl.rug.oop.rpg.util.CatchNonInts;

public class Game implements CatchNonInts {

    private boolean exitGame = false;

    /**
     * Runs the main game loop.
     * @param player Player to call methods on.
     */
    public void gameLoop(Player player) {
        while (!exitGame) {
            checkWinCondition(player);
            printMenu();
            int option = CatchNonInts.inputOption();
            switch(option){
                case 0: player.getCurrentRoom().inspect(); gameLoop(player);
                case 1: player.selectDoor(false); gameLoop(player);
                case 2: player.selectNPC(false); gameLoop(player);
                case 3: Serializer.savePlayer(player, "quicksave"); gameLoop(player);
                case 4: player = SaveAndLoad.loadPlayer(player, "quicksave"); gameLoop(player);
                case 5: SaveAndLoad.customSave(player); gameLoop(player);
                case 6: player = SaveAndLoad.customLoad(player); gameLoop(player);
                case 7: exitGame();
            }
        }
    }

    /**
     * Checks if the player has reached the last room (winning condition).
     * @param player Check this player's current room.
     */
    private void checkWinCondition(Player player){
        if (player.getCurrentRoom().getDescription().equals("the end!")){
            System.out.println("You win the game.");
            System.exit(0);
        }
    }

    public static void printGameRules(){
        System.out.println("Welcome to the game. \n" +
                "Your goal is to reach the end room.\n" +
                "There are multiple paths that you can take, and some might be easier than others. \n" +
                "Along the way you will find NPCs in the rooms, and it is up to you to figure out what they do.\n" +
                "Some doors work in your favour, and some are more difficult to get through.\n" +
                "Make sure to keep your health up! There are NPCs that can help with this.\n" +
                "If your health reaches below zero for any reason, you will die and the game will be over.\n" +
                "Good luck!");
    }

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
