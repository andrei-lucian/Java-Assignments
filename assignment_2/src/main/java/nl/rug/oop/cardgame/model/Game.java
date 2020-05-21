package nl.rug.oop.cardgame.model;
import nl.rug.oop.cardgame.model.elements.Card;
import nl.rug.oop.cardgame.model.elements.Deck;
import nl.rug.oop.cardgame.model.participants.Computer;
import nl.rug.oop.cardgame.model.participants.Participant;
import nl.rug.oop.cardgame.model.participants.Player;
import nl.rug.oop.cardgame.view.GameFrame;

import java.util.*;

public class Game extends Observable implements Observer {

    public GameFrame getGameFrame() {
        return gameFrame;
    }

    private GameFrame gameFrame;
    Deck faceDown = Dealer.newFaceDownDeck();
    Deck faceUp = new Deck();
    private final Player player = new Player();
    private final Computer computer = new Computer();
    private final Timer timer = new Timer();
    private static boolean exitGame = false;
    private Card participantCard;
    private Card clickedCard;
    private final CurrentCard currentCard = new CurrentCard();
    private Card topCard;
    private boolean playerTurn = true;
    private boolean computerTurn = false;
    private Card.Suit clickedSuit;

    public void setClickedSuit(Card.Suit clickedSuit) {
        this.clickedSuit = clickedSuit;
    }

    public Game() {
        gameFrame = new GameFrame(this);
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public void printComputerHand(){
        for (Card card : computer.getCardList()){
            System.out.println(card);
        }
    }

    /** Sets up the game (deals cards, reveals the top card,
     * sets the current face and suit) and calls the game loop */
    public void startGame(){
        Dealer.deal5Cards(player, computer, faceDown);
        Dealer.revealCard(faceDown, faceUp);
        currentCard.setFace(faceUp.peekTopCard().getFace());
        currentCard.setSuit(faceUp.peekTopCard().getSuit());
        printComputerHand();
        gameLoop(player, computer, faceUp, faceDown);
    }

    /** The main game loop where player and computer take turns */
    public void gameLoop(Player player, Computer computer, Deck faceUp, Deck faceDown){
        while(!exitGame){
            if(clickedCard != null) {
            //System.out.println(chosenCard);
                if(playerTurn) {
                    playerTurn(player, currentCard, faceDown, faceUp);
                    clickedCard = null;
                }
                if(computerTurn) {
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
        computerTurn = true;
        playerTurn = false;
    }

    private void computerTurn(Computer computer, CurrentCard currentCard, Deck faceDown, Deck faceUp){
        participantCard = computer.playCard(faceDown, faceUp, currentCard.getFace(), currentCard.getSuit()); //player either puts down or draws a card
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
                    System.out.println("The suit has been switched to: " + currentCard.getSuit());
                } else {
                    currentCard.setSuit(participantCard.getSuit());
                }
            }
        }
        //System.out.println("Current card is :" + currentCard.getFace() + "_" + currentCard.getSuit());
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
                            System.out.println("The suit has been switched to: " + currentCard.getSuit());
                        }
                        setChanged();
                        notifyObservers();
                    }
                }
                else {
                    currentCard.setSuit(participantCard.getSuit());
                }
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
}
