package nl.rug.oop.cardgame.model;
import nl.rug.oop.cardgame.model.elements.Card;
import nl.rug.oop.cardgame.model.elements.Deck;
import nl.rug.oop.cardgame.model.participants.Computer;
import nl.rug.oop.cardgame.model.participants.Participant;
import nl.rug.oop.cardgame.model.participants.Player;
import nl.rug.oop.cardgame.view.GameFrame;

import javax.swing.*;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game extends Observable {

    private Timer timer;
    private Player player;
    private Computer computer;
    private static boolean exitGame;
    private Card participantCard;
    private Card clickedCard;
    private CurrentCard currentCard;
    private Card topCard;
    private boolean playerTurn;
    private boolean computerTurn;
    private Card.Suit clickedSuit;
    private Deck faceDown;
    private Deck faceUp;
    private String suitString = "";

    public String getSuitString() {
        return suitString;
    }

    public Game() {
        new GameFrame(this);
    }

    /** Sets up the game (deals cards, reveals the top card,
     * sets the current face and suit) and calls the game loop */
    public void startGame(){
        timer = new Timer();
        player = new Player();
        computer = new Computer();
        exitGame = false;
        participantCard = null;
        clickedCard = null;
        currentCard = new CurrentCard();
        topCard = null;
        playerTurn = true;
        computerTurn = false;
        clickedSuit = null;
        faceDown = Dealer.newFaceDownDeck();
        faceUp = new Deck();
        suitString = "";
        Dealer.deal5Cards(player, computer, faceDown);
        Dealer.revealCard(faceDown, faceUp);
        currentCard.setFace(faceUp.peekTopCard().getFace());
        currentCard.setSuit(faceUp.peekTopCard().getSuit());
        setChanged();
        notifyObservers();
        gameLoop(player, computer, faceUp, faceDown);
    }

    /** The main game loop where player and computer take turns */
    public void gameLoop(Player player, Computer computer, Deck faceUp, Deck faceDown){
        while(!exitGame){
            if(clickedCard != null) {
                if(playerTurn) {
                    System.out.println("PLAYER TURN");
                    playerTurn(player, currentCard, faceDown, faceUp);
                    clickedCard = null;
                }
                if(computerTurn) {
                    System.out.println("COMPUTER TURN");
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            computerTurn(computer, currentCard, faceDown, faceUp);
                        }
                    }, 1000);
                }
            }
            setChanged();
            notifyObservers();
        }
    }

    private void playerTurn(Player player, CurrentCard currentCard, Deck faceDown, Deck faceUp){
        boolean played = false;
        while (!played) {
            topCard = faceDown.peekTopCard();
            participantCard = player.playCard(this.clickedCard, faceDown, faceUp, currentCard.getFace(), currentCard.getSuit());
            if (participantCard != null){
                played = true;
            }
        }
        updateConditions(player, faceDown, faceUp);
        setNewValuesPlayer(currentCard);
        playerTurn = false;
        computerTurn = true;
        System.out.println("PLAYER TURN OVER");
    }

    private void computerTurn(Computer computer, CurrentCard currentCard, Deck faceDown, Deck faceUp){
        participantCard = computer.playCard(faceDown, faceUp, currentCard.getFace(), currentCard.getSuit());
        updateConditions(computer, faceDown, faceUp);
        setNewValuesComputer(currentCard, computer);
        computerTurn = false;
        playerTurn = true;
        System.out.println("COMPUTER TURN OVER");
    }

    /**Checks the current game status and updates it accordingly */
    private void updateConditions(Participant participant, Deck faceDown, Deck faceUp){
        checkWinCondition(participant);
        if (faceDown.isEmpty()){
            Dealer.transferDeck(faceUp, faceDown);
            System.out.println("Face down deck ran out, dealer switched it.");
        }
        setChanged();
        notifyObservers();
    }

    /** Sets the new values (face and suit) for the currentCard object,
     * depending on what the participant's action is */
    private void setNewValuesComputer(CurrentCard currentCard, Computer computer) {
        if(checkForEight(participantCard)){
            currentCard.setSuit(computer.chooseSuit());
            suitString = "The computer switched the suit to: " + currentCard.getSuit();
            System.out.println("The suit has been switched to: " + currentCard.getSuit());
        }
    }

    private boolean checkForEight(Card participantCard){
        if (participantCard != null) {
            if (participantCard != topCard) {
                currentCard.setFace(participantCard.getFace());
                if (participantCard.getFace() == Card.Face.EIGHT) {
                    return true;
                } else {
                    currentCard.setSuit(participantCard.getSuit());
                    suitString = "";
                    return false;
                }
            }
        }
        return false;
    }

    private void setNewValuesPlayer(CurrentCard currentCard){
        boolean suitChosen = false;
        if(checkForEight(participantCard)){
            while (!suitChosen) {
                if (clickedSuit != null) {
                    currentCard.setSuit(clickedSuit);
                    suitChosen = true;
                    suitString = "You switched the suit to: " + currentCard.getSuit();
                    System.out.println("The suit has been switched to: " + currentCard.getSuit());
                }
                setChanged();
                notifyObservers();
            }
        }
    }

    /** If one of the players has no cards left then they win and the game is over */
    public void checkWinCondition(Participant participant) {
        if (participant.noOfCards() == 0) {
            System.out.println("Game over!");
            JOptionPane.showMessageDialog(null, "Game over.");
            System.exit(0);
        }
    }

    public void setClickedCard(Card clickedCard) {
        this.clickedCard = clickedCard;
    }

    public Deck getFaceDown() {
        return faceDown;
    }

    public Deck getFaceUp() {
        return faceUp;
    }

    public CopyOnWriteArrayList<Card> getPlayerHand(){
        return player.getCardList();
    }

    public CopyOnWriteArrayList<Card> getComputerHand(){
        return computer.getCardList();
    }

    public void setClickedSuit(Card.Suit clickedSuit) {
        this.clickedSuit = clickedSuit;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }
}
