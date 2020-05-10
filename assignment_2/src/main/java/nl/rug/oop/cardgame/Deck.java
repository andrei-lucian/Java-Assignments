package nl.rug.oop.cardgame;

import java.util.*;

public class Deck {

    protected Stack<Card> cards;
    private Random random;

    private static int seed = 42;

    private static int nextSeed() {
        return seed++;
    }

    public Deck() {
        cards = new Stack<>();
        random = new Random(nextSeed());
        addCards();
    }

    /** Add all the cards to a deck */
    protected void addCards() {
        cards.addAll(Arrays.asList(Card.values()));
    }

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
    /** Get the top card */
    public Card getTopCard(){
        return cards.pop();
    }

    /** Return if deck is empty */
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    /** Draw a card from a deck */
    public Card draw() {
        if (!isEmpty())
            return cards.remove(cards.size() - 1);
        return null;
    }

}

