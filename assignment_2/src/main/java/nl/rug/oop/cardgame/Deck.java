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

    /** Return all the cards in a deck */
    public Stack<Card> getCards() {
        return cards;
    }

    /** Shuffle a deck */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card getTopCard(){
        return cards.pop();
    }

}

