package nl.rug.oop.cardgame;
import nl.rug.oop.cardgame.elements.Card;
import nl.rug.oop.cardgame.elements.Deck;
import nl.rug.oop.cardgame.participants.Computer;
import nl.rug.oop.cardgame.participants.Player;

public class Game {

    private static boolean exitGame = false;
    static Card playerCard;
    static Card computerCard;
    static Card.Suit previousSuit;
    static Card.Face previousFace;

    public static void gameLoop(Player player, Computer computer, Deck faceUp, Deck faceDown,
                         Card.Face currentFace, Card.Suit currentSuit){
        while(!exitGame){
            System.out.println("Card to match: "+ currentFace + "_" + currentSuit);
            playerCard = player.playCard(faceDown, faceUp, currentFace, currentSuit); //player either puts down or draws a card
            checkWinCondition(player, computer); //check if this results in the player winning
            previousSuit = currentSuit;
            previousFace = currentFace;
            if (faceDown.isEmpty()){ //reset the deck if it's empty
                Dealer.transferDeck(faceUp, faceDown);
                System.out.println("Face down deck ran out, dealer switched it.");
            }
            if (playerCard!= null && playerCard.getFace() == Card.Face.EIGHT){ //choose a suit if an 8 is played
                currentSuit = player.chooseSuit();
                currentFace = null;
                System.out.println("The suit has been switched to: " + currentSuit);
            }
            else {
                if (playerCard != null){
                    currentSuit = playerCard.getSuit();
                    currentFace = playerCard.getFace();
                }
                else {
                    currentSuit = previousSuit;
                    currentFace = previousFace;
                }
            }
            computerCard = computer.playCard(faceUp, faceDown, currentSuit, currentFace); //same process as above but for computer
            checkWinCondition(player, computer);
            previousSuit = currentSuit;
            previousFace = currentFace;
            if (faceDown.isEmpty()){
                Dealer.transferDeck(faceUp, faceDown);
                System.out.println("Face down deck ran out, dealer reset it.");
            }
            if (computerCard!= null && computerCard.getFace() == Card.Face.EIGHT){
                currentSuit = computer.chooseSuit();
                currentFace = null;
                System.out.println("The suit has been switched to: " + currentSuit);
            }
            else {
                if (computerCard != null){
                    currentSuit = computerCard.getSuit();
                    currentFace = computerCard.getFace();
                }
                else {
                    currentSuit = previousSuit;
                    currentFace = previousFace;
                }
            }
        }
    }

    /** If one of the players has no cards left then they win and the game is over */
    public static void checkWinCondition(Player player, Computer computer){
        if (player.noOfCards() == 0 || computer.noOfCards() == 0){
            exitGame = true;
            if (player.noOfCards() == 0) {
                System.out.println("Congratulations, you won!");
            }
            else {
                System.out.println("Unlucky - you lost this time.");
            }
            System.exit(0);
        }
    }
}

