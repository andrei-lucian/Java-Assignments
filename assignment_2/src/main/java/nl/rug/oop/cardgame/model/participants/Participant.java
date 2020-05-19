package nl.rug.oop.cardgame.model.participants;
import nl.rug.oop.cardgame.model.elements.Card;
import nl.rug.oop.cardgame.model.elements.Deck;

import java.util.ArrayList;

/** Superclass of Player and Computer */
public abstract class Participant {

    public void setCardList(ArrayList<Card> cardList) {
        this.cardList = cardList;
    }

    protected ArrayList<Card> cardList;

    public ArrayList<Card> getCardList() {
        return cardList;
    }

    public Participant(){
        this.cardList = new ArrayList<>();
    }

    public int noOfCards(){
        return this.cardList.size();
    }

    /** Draw a card from the FaceDown deck */
    public void drawCard(Deck deck){
        Card drawnCard = deck.draw();
        cardList.add(drawnCard);
    }

    /** Put a card onto the FaceUp deck */
    public void putDownCard(Deck deck, Card card){
        deck.addOnTop(card);
        this.cardList.remove(card);
    }

    //public abstract Card playCard(Deck faceDown, Deck faceUp, Card.Face face, Card.Suit suit);

    /** Choose a suit - via interaction in Player, via algorithm in Computer */
    public abstract Card.Suit chooseSuit();

    /** View all a player's cards in terminal and their index
     * (put this in participant for now for debugging but will move
     * to player later) */
    public void inspectAllCards(){
        for (Card card : this.cardList) {
            System.out.println("(" + cardList.indexOf(card) + ") "+ card);
        }
    }
}
