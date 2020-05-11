package nl.rug.oop.cardgame;
import nl.rug.oop.cardgame.elements.Card;
import nl.rug.oop.cardgame.elements.Deck;
import nl.rug.oop.cardgame.participants.Computer;
import nl.rug.oop.cardgame.participants.Player;

public class Game {

    private static boolean exitGame = false;
    static Card playerCard;
    static Card computerCard;

    public static void gameLoop(Player player, Computer computer, Deck faceUp, Deck faceDown,
                         Card.Face currentFace, Card.Suit currentSuit){
        while(!exitGame){
            playerCard = player.playCard(false, faceDown, faceUp, currentFace, currentSuit);
            checkWinCondition(player, computer);
            if (faceDown.isEmpty()){
                Dealer.transferDeck(faceUp, faceDown);
                System.out.println("Face down deck ran out, dealer switched it.");
            }
            if (playerCard!= null && playerCard.getFace() == Card.Face.EIGHT){
                currentSuit = player.chooseSuit();
                currentFace = null;
                System.out.println("The suit has been switched to: " + currentSuit);
            }
            else {
                currentSuit = faceUp.peekTopCard().getSuit();
                currentFace = faceUp.peekTopCard().getFace();
            }

            computerCard = computer.playCard(faceUp, faceDown, currentSuit, currentFace);
            checkWinCondition(player, computer);
            if (faceDown.isEmpty()){
                Dealer.transferDeck(faceUp, faceDown);
                System.out.println("Face down deck ran out, dealer reset it.");
            }
            if (computerCard!= null && computerCard.getFace() == Card.Face.EIGHT){
                currentSuit = computer.chooseSuit();
                currentFace = null;
                System.out.println("The computer played an 8 - the new suit is: " + currentSuit);
            }
            else {
                currentSuit = faceUp.peekTopCard().getSuit();
                currentFace = faceUp.peekTopCard().getFace();
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

