package nl.rug.oop.cardgame.model;
import nl.rug.oop.cardgame.model.elements.Card;
import nl.rug.oop.cardgame.model.elements.Deck;
import nl.rug.oop.cardgame.model.participants.Computer;
import nl.rug.oop.cardgame.model.participants.Participant;
import nl.rug.oop.cardgame.model.participants.Player;
import nl.rug.oop.cardgame.view.ClickableCard;
import nl.rug.oop.cardgame.view.GameFrame;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Game extends Observable implements Observer {

    private static boolean exitGame = false;
    Card participantCard;
    CurrentCard currentCard = new CurrentCard();
    Player player = new Player();
    Computer computer = new Computer();
    private GameFrame gameFrame;
    private Card chosenCard;
    Deck faceDown = Dealer.newFaceDownDeck();
    Deck faceUp = new Deck();
    private int x = 2;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Game() {
        gameFrame = new GameFrame(this);
    }

    public Card getChosenCard() {
        return chosenCard;
    }

    public void setChosenCard(Card chosenCard) {
        this.chosenCard = chosenCard;
    }

    public Deck getFaceDown() {
        return faceDown;
    }

    public Deck getFaceUp() {
        return faceUp;
    }

    public ArrayList<Card> getPlayerHand(){
        return player.getCardList();
    }

    public ArrayList<ClickableCard> getClickableCards(){
        ArrayList<ClickableCard> cc = new ArrayList<>();
        for (Card card : player.getCardList()){
            ClickableCard click = new ClickableCard(card);
            cc.add(click);
        }
        return cc;
    }

    public ArrayList<Card> getComputerHand() {
        return computer.getCardList();
    }

    /** The main game loop where player and computer take turns */
    public void gameLoop(Player player, Computer computer, Deck faceUp, Deck faceDown){
        while(!exitGame){
            if(chosenCard!=null) {
                //System.out.println(getChosenCard());
                playerTurn(chosenCard, player, currentCard, faceDown, faceUp);
                computerTurn(computer, currentCard, faceDown, faceUp);
            }
       }
    }

    /** Sets up the game (deals cards, reveals the top card,
     * sets the current face and suit) and calls the game loop */
    public void startGame(){
        Dealer.deal5Cards(player, computer, faceDown);
        Dealer.revealCard(faceDown, faceUp);
        currentCard.setFace(faceUp.peekTopCard().getFace());
        currentCard.setSuit(faceUp.peekTopCard().getSuit());
        System.out.println(x);
        while(!exitGame){
            if(x==10){
                System.out.println(x);
            }
        }
        //gameLoop(player, computer, faceUp, faceDown);
    }

    private void playerTurn(Card card, Player player, CurrentCard currentCard, Deck faceDown, Deck faceUp){
        participantCard = player.playCard(card, faceDown, faceUp, currentCard.getFace(), currentCard.getSuit());
        checkWinCondition(player);
        if (faceDown.isEmpty()){
            Dealer.transferDeck(faceUp, faceDown);
            System.out.println("Face down deck ran out, dealer switched it.");
        }
        setNewValues(currentCard, player);
        setChanged();
        notifyObservers();
    }

    /** A participant in the game takes a turn.
     * 1. The participant places down or draws a card
     * 2. The winning condition is checked
     * 3. The faceDown deck is reset if it is empty
     * 4. The new values for currentCard are set */
    private void computerTurn(Computer computer, CurrentCard currentCard, Deck faceDown, Deck faceUp){
        participantCard = computer.playCard(faceDown, faceUp, currentCard.getFace(), currentCard.getSuit()); //player either puts down or draws a card
        checkWinCondition(computer); //check if this results in the player winning
        if (faceDown.isEmpty()){
            Dealer.transferDeck(faceUp, faceDown);
            System.out.println("Face down deck ran out, dealer switched it.");
        }
        setNewValues(currentCard, computer);
        setChanged();
        notifyObservers();
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

    @Override
    public void update(Observable observable, Object o) {
        setChanged();
        notifyObservers();
    }
}
