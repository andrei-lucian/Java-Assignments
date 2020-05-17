package nl.rug.oop.cardgame.model;
import nl.rug.oop.cardgame.model.elements.Card;
import nl.rug.oop.cardgame.model.elements.Deck;
import nl.rug.oop.cardgame.model.participants.Computer;
import nl.rug.oop.cardgame.model.participants.Participant;
import nl.rug.oop.cardgame.model.participants.Player;

import java.util.Observable;

public class Game extends Observable {

    private static boolean exitGame = false;
    Card participantCard;
    CurrentCard currentCard = new CurrentCard();
    Player player = new Player();
    Computer computer = new Computer();

    public Deck getFaceDown() {
        return faceDown;
    }

    public Deck getFaceUp() {
        return faceUp;
    }

    Deck faceDown = Dealer.newFaceDownDeck();
    Deck faceUp = new Deck();

    /** The main game loop where player and computer take turns */
    /*private static void gameLoop(Player player, Computer computer, Deck faceUp, Deck faceDown){
        while(!exitGame){
            System.out.println("Card to match: "+ currentCard.getFace() + "_" + currentCard.getSuit());
            turn(player, currentCard, faceDown, faceUp);
            turn(computer, currentCard, faceDown, faceUp);
        }
    }*/

    /** Sets up the game (deals cards, reveals the top card,
     * sets the current face and suit) and calls the game loop */
    public void startGame(){
        Dealer.deal5Cards(player, computer, faceDown);
        Dealer.revealCard(faceDown, faceUp);
        currentCard.setFace(faceUp.peekTopCard().getFace());
        currentCard.setSuit(faceUp.peekTopCard().getSuit());
    }

    /** A participant in the game takes a turn.
     * 1. The participant places down or draws a card
     * 2. The winning condition is checked
     * 3. The faceDown deck is reset if it is empty
     * 4. The new values for currentCard are set */
    private void turn(Participant participant, CurrentCard currentCard, Deck faceDown, Deck faceUp){
        participantCard = participant.playCard(faceDown, faceUp, currentCard.getFace(), currentCard.getSuit()); //player either puts down or draws a card
        checkWinCondition(participant); //check if this results in the player winning
        if (faceDown.isEmpty()){
            Dealer.transferDeck(faceUp, faceDown);
            System.out.println("Face down deck ran out, dealer switched it.");
        }
        setNewValues(currentCard, participant);
    }

    /** Sets the new values (face and suit) for the currentCard object,
     * depending on what the participant's action is */
    private void setNewValues(CurrentCard currentCard, Participant participant) {
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
    public void checkWinCondition(Participant participant) {
        if (participant.noOfCards() == 0) {
            System.out.println("Game over!");
            exitGame = true;
            System.exit(0);
        }
    }
}

