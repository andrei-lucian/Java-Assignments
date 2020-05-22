package nl.rug.oop.cardgame.model.participants;
import nl.rug.oop.cardgame.model.elements.Card;
import nl.rug.oop.cardgame.model.elements.Deck;

import java.util.concurrent.CopyOnWriteArrayList;

/** Superclass of Player and Computer */
public abstract class Participant {

    protected CopyOnWriteArrayList<Card> cardList;

    public CopyOnWriteArrayList<Card> getCardList() {
        return cardList;
    }

    public Participant(){
        this.cardList = new CopyOnWriteArrayList<>();
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

}
