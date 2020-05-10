package nl.rug.oop.cardgame;

import java.util.List;

/** Superclass of Player and Computer */
public abstract class Participant {

    private List<Card> myCards;

    /** Draw a card from the FaceDown deck */
    public void drawCard(Deck deck){
        Card drawnCard = deck.draw();
        myCards.add(drawnCard);
    }

    /** Put a card onto the FaceUp deck */
    public void putDownCard(Deck deck, Card card){
        deck.addOnTop(card);
    }

    /** Choose a suit - via interaction in Player, via algorithm in Computer */
    public abstract Card.Suit chooseSuit();
}
