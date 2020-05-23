package nl.rug.oop.cardgame.model.participants;

import nl.rug.oop.cardgame.model.elements.Card;
import nl.rug.oop.cardgame.model.elements.Deck;

public class Player extends Participant {

    /** Makes sure the player selects a valid card */
    private boolean accordingToRules(Card card, Card.Face face, Card.Suit suit){
        return card.getFace() == face || card.getSuit() == suit || card.getFace() == Card.Face.EIGHT;
    }

    /**
     * Choose a card to play. Only allows a card that matches either the face or suit, or is an 8.
     * @param faceDown The face down deck.
     * @param faceUp The face up deck.
     * @param face The face to be matched.
     * @param suit The suit to be matched.
     */
    public Card playCard(Card card, Deck faceDown, Deck faceUp, Card.Face face, Card.Suit suit) {
        if (card == faceDown.peekTopCard()) {
            drawCard(faceDown);
            return card;
        }
        else if (accordingToRules(card, face, suit)) {
            this.putDownCard(faceUp, card);
            return card;
            }
        else {
            return null;
        }
    }
}
