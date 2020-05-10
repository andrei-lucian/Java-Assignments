package nl.rug.oop.cardgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {

    protected List<Card> cards;
    private Random random;

    private static int seed = 42;

    private static int nextSeed() {
        return seed++;
    }

    public Deck() {
        cards = new ArrayList<>();
        random = new Random(nextSeed());
        addCards();
    }

    /** Add all the cards to a deck */
    protected void addCards() {
        for (Card card : Card.values())
            cards.add(card);
    }

    /** Return all the cards in a deck */
    public List<Card> getCards() {
        return cards;
    }

    /** Shuffle a deck */
    public void shuffle() {
        Collections.shuffle(cards);
    }


}

