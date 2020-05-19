package nl.rug.oop.cardgame.view;

import nl.rug.oop.cardgame.model.elements.Card;

import java.util.Observable;

public class ClickableCard extends Observable {

    private Card card;
    private int relativeY;

    public ClickableCard(Card card){
        this.card = card;
        relativeY = 0;
    }

    public Card getCard() {
        return card;
    }

    public int getRelativeY() {
        return relativeY;
    }

    public void setRelativeY(int move){
        this.relativeY += move;
        setChanged();
        notifyObservers();
    }
}
