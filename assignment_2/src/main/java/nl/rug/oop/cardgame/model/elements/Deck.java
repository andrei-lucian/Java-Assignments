package nl.rug.oop.cardgame.model.elements;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/** A deck of cards with all the types of cards in it */
public class Deck{

    protected final CopyOnWriteArrayList<Card> cards;
    private final Random random = new Random();

    /** Constructor */
    public Deck() {
        cards = new CopyOnWriteArrayList<>();
    }

    public Card peekTopCard(){
        return cards.get(cards.size() - 1);
    }

    /** Add all the cards to a deck */
    public void addCards() {
        cards.addAll(Arrays.asList(Card.values()));
    }

    /** Add a card to the top of the deck */
    public void addOnTop(Card card) {
        cards.add(card);
    }

    public void addAnywhere(Card card) {
        cards.add(random.nextInt(cards.size()), card);
    }

    /** Return all the cards in a deck */
    public CopyOnWriteArrayList<Card> getCards() {
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
            return cards.remove(cards.size() - 1);
        }
        return null;
    }

}
