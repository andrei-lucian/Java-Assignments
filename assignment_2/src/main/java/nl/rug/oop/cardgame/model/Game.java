package nl.rug.oop.cardgame.model;
import nl.rug.oop.cardgame.Main;
import nl.rug.oop.cardgame.model.elements.Card;
import nl.rug.oop.cardgame.model.elements.Deck;
import nl.rug.oop.cardgame.model.participants.Computer;
import nl.rug.oop.cardgame.model.participants.Participant;
import nl.rug.oop.cardgame.model.participants.Player;
import nl.rug.oop.cardgame.view.GameFrame;

import javax.swing.*;
import java.util.*;
import java.util.Timer;

public class Game extends Observable {

    private final GameFrame gameFrame;
    private Player player = new Player();
    private Computer computer = new Computer();
    private final Timer timer = new Timer();
    private static boolean exitGame = false;
    private Card participantCard;
    private Card clickedCard;
    private CurrentCard currentCard = new CurrentCard();
    private Card topCard;
    private boolean playerTurn = true;
    private boolean computerTurn = false;
    private Card.Suit clickedSuit;
    Deck faceDown = Dealer.newFaceDownDeck();
    Deck faceUp = new Deck();
    private String suitString = "";

    public String getSuitString() {
        return suitString;
    }

    public Game() {
        gameFrame = new GameFrame(this);
    }


    /** Sets up the game (deals cards, reveals the top card,
     * sets the current face and suit) and calls the game loop */
    public void startGame(){
        Dealer.deal5Cards(player, computer, faceDown);
        Dealer.revealCard(faceDown, faceUp);
        currentCard.setFace(faceUp.peekTopCard().getFace());
        currentCard.setSuit(faceUp.peekTopCard().getSuit());
        gameLoop(player, computer, faceUp, faceDown);
    }

    public void reset(){
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
        startGame();
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
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            System.out.println("COMPUTER TURN");
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
        computerTurn = true;
        playerTurn = false;
    }

    private void computerTurn(Computer computer, CurrentCard currentCard, Deck faceDown, Deck faceUp){
        participantCard = computer.playCard(faceDown, faceUp, currentCard.getFace(), currentCard.getSuit());
        updateConditions(computer, faceDown, faceUp);
        setNewValuesComputer(currentCard, computer);
        computerTurn = false;
        playerTurn = true;
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
        if (participantCard != null) {
            if (participantCard!=topCard) {
                currentCard.setFace(participantCard.getFace());
                if (participantCard.getFace() == Card.Face.EIGHT) {
                    currentCard.setSuit(computer.chooseSuit());
                    suitString = "The computer switched the suit to: " + currentCard.getSuit();
                    System.out.println("The suit has been switched to: " + currentCard.getSuit());
                } else {
                    currentCard.setSuit(participantCard.getSuit());
                    suitString = "";
                }
            }
        }
    }

    private void setNewValuesPlayer(CurrentCard currentCard){
        boolean suitChosen = false;
        if (participantCard != null) {
            if (participantCard != topCard) {
                currentCard.setFace(participantCard.getFace());
                if (participantCard.getFace() == Card.Face.EIGHT) {
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
                else {
                    currentCard.setSuit(participantCard.getSuit());
                    suitString = "";
                }
            }
        }
    }

    /** If one of the players has no cards left then they win and the game is over */
    public void checkWinCondition(Participant participant) {
        if (participant.noOfCards() == 0) {
            System.out.println("Game over!");
            int n = JOptionPane.showConfirmDialog(
                    null,
                    "Would you like to play again?",
                    "GAME OVER",
                    JOptionPane.YES_NO_OPTION);
            if (n == 1){
                System.exit(0);
            }
            else if (n == 0){
                reset();
            }
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

    public ArrayList<Card> getPlayerHand(){
        return player.getCardList();
    }

    public ArrayList<Card> getComputerHand(){
        return computer.getCardList();
    }

    public void setClickedSuit(Card.Suit clickedSuit) {
        this.clickedSuit = clickedSuit;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }
}
