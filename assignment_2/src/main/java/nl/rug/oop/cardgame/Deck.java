package nl.rug.oop.cardgame;

import java.util.*;

/** A deck of cards with all the types of cards in it */
public class Deck {

    protected Stack<Card> cards;

    public Deck() {
        cards = new Stack<>();
    }

    /** Add all the cards to a deck */
    protected void addCards() {
        cards.addAll(Arrays.asList(Card.values()));
    }

    /** Add a card to the top of the deck */
    public void addOnTop(Card card) {
        cards.add(card);
    }

    /** Return all the cards in a deck */
    public Stack<Card> getCards() {
        return cards;
    }

    /** Shuffle a deck */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /** Return if deck is empty */
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    /** Draw a card from a deck */
    public Card draw() {
        if (!isEmpty())
            return cards.pop();
        return null;
    }

}

