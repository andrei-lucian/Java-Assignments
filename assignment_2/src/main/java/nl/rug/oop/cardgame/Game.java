package nl.rug.oop.cardgame;
import nl.rug.oop.cardgame.elements.Card;
import nl.rug.oop.cardgame.elements.Deck;
import nl.rug.oop.cardgame.participants.Computer;
import nl.rug.oop.cardgame.participants.Participant;
import nl.rug.oop.cardgame.participants.Player;

public class Game {

    private static boolean exitGame = false;
    static Card participantCard;

    public static void gameLoop(Player player, Computer computer, Deck faceUp, Deck faceDown){

        Dealer.deal5Cards(player, computer, faceDown);
        Dealer.revealCard(faceDown, faceUp);

        CurrentCard currentCard = new CurrentCard();

        currentCard.setFace(faceUp.peekTopCard().getFace());
        currentCard.setSuit(faceUp.peekTopCard().getSuit());

        while(!exitGame){
            System.out.println("Card to match: "+ currentCard.getFace() + "_" + currentCard.getSuit());
            currentCard = turn(player, currentCard, faceDown, faceUp);
            currentCard = turn(computer, currentCard, faceDown, faceUp);
        }
    }

    private static CurrentCard turn(Participant participant, CurrentCard currentCard, Deck faceDown, Deck faceUp){
        participantCard = participant.playCard(faceDown, faceUp, currentCard.getFace(), currentCard.getSuit()); //player either puts down or draws a card

        checkWinCondition(participant); //check if this results in the player winning
        if (faceDown.isEmpty()){
            Dealer.transferDeck(faceUp, faceDown);
            System.out.println("Face down deck ran out, dealer switched it.");
        }

        if (participantCard!= null && participantCard.getFace() == Card.Face.EIGHT){ //choose a suit if an 8 is played
            currentCard.setSuit(participant.chooseSuit());
            currentCard.setFace(participantCard.getFace());
            System.out.println("The suit has been switched to: " + currentCard.getSuit());
        }

        else {
            if (participantCard != null){
                currentCard.setFace(participantCard.getFace());
                currentCard.setSuit(participantCard.getSuit());
            }
        }
        return currentCard;
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

