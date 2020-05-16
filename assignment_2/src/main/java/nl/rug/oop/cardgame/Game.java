package nl.rug.oop.cardgame;
import nl.rug.oop.cardgame.elements.Card;
import nl.rug.oop.cardgame.elements.Deck;
import nl.rug.oop.cardgame.participants.Computer;
import nl.rug.oop.cardgame.participants.Participant;
import nl.rug.oop.cardgame.participants.Player;

public class Game {

    private static boolean exitGame = false;
    static Card participantCard;
    static CurrentCard currentCard = new CurrentCard();

    private static void gameLoop(Player player, Computer computer, Deck faceUp, Deck faceDown){
        while(!exitGame){
            System.out.println("Card to match: "+ currentCard.getFace() + "_" + currentCard.getSuit());
            turn(player, currentCard, faceDown, faceUp);
            turn(computer, currentCard, faceDown, faceUp);
        }
    }

    public static void startGame(Player player, Computer computer, Deck faceUp, Deck faceDown){
        Dealer.deal5Cards(player, computer, faceDown);
        Dealer.revealCard(faceDown, faceUp);
        currentCard.setFace(faceUp.peekTopCard().getFace());
        currentCard.setSuit(faceUp.peekTopCard().getSuit());
        gameLoop(player, computer, faceUp, faceDown);
    }

    private static void turn(Participant participant, CurrentCard currentCard, Deck faceDown, Deck faceUp){
        participantCard = participant.playCard(faceDown, faceUp, currentCard.getFace(), currentCard.getSuit()); //player either puts down or draws a card
        checkWinCondition(participant); //check if this results in the player winning
        if (faceDown.isEmpty()){
            Dealer.transferDeck(faceUp, faceDown);
            System.out.println("Face down deck ran out, dealer switched it.");
        }
        setNewValues(currentCard, participant);
    }

    private static void setNewValues(CurrentCard currentCard, Participant participant) {
        if (participantCard != null) {
            currentCard.setFace(participantCard.getFace());
            if (participantCard.getFace() == Card.Face.EIGHT) {
                currentCard.setSuit(participant.chooseSuit());
                System.out.println("The suit has been switched to: " + currentCard.getSuit());
            } else {
                currentCard.setSuit(participantCard.getSuit());
            }
        }
    }

    /** If one of the players has no cards left then they win and the game is over */
    public static void checkWinCondition(Participant participant) {
        if (participant.noOfCards() == 0) {
            System.out.println("Game over!");
            exitGame = true;
            System.exit(0);
        }
    }
}

