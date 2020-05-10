package nl.rug.oop.cardgame;

import java.util.List;

public abstract class Participant {

    private List<Card> myCards;

    protected void takeCard(Deck deck){
        Card drawnCard = deck.draw();
        myCards.add(drawnCard);
    }

    protected void putDownCard(Deck deck, Card card){
        deck.addOnTop(card);
    }

    protected abstract Card.Suit chooseSuit();
}
