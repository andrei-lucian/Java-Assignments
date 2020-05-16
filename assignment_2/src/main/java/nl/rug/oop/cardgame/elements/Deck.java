package nl.rug.oop.cardgame.elements;

import java.util.*;

/** A deck of cards with all the types of cards in it - implemented as a stack */
public class Deck {

    protected Stack<Card> cards;
    Random random = new Random();

    /** Constructor */
    public Deck() {
        cards = new Stack<>();
    }

    public Card peekTopCard(){
        return cards.peek();
    }

    /** Add all the cards to a deck */
    public void addCards() {
        cards.addAll(Arrays.asList(Card.values()));
    }

    /** Add a card to the top of the deck */
    public void addOnTop(Card card) {
        cards.push(card);
    }

    public void addAnywhere(Card card) {
        cards.add(random.nextInt(cards.size()), card);
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
        if (!isEmpty()) {
            Card card = cards.pop();
            return card;
        }
        return null;
    }

}
