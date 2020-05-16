package nl.rug.oop.cardgame;

import nl.rug.oop.cardgame.elements.Card;

/** Class to store the state of the imaginary card
 * that a participant has to compare their cards to */

public class CurrentCard {
    private Card.Face face;
    private Card.Suit suit;

    public Card.Face getFace() {
        return face;
    }

    public void setFace(Card.Face face) {
        this.face = face;
    }


    public Card.Suit getSuit() {
        return suit;
    }

    public void setSuit(Card.Suit suit) {
        this.suit = suit;
    }

}
