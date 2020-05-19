package nl.rug.oop.cardgame.model;

import nl.rug.oop.cardgame.model.elements.Card;

import java.util.Observable;

public class ImaginaryCard extends Observable {

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
        setChanged();
        notifyObservers();
    }

    private Card card;
}
