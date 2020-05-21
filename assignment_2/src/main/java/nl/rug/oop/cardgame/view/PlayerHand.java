package nl.rug.oop.cardgame.view;

import nl.rug.oop.cardgame.model.elements.Card;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class PlayerHand extends Observable {

    private ArrayList<Card> playerCards;
    private Card pickedCard;

    public PlayerHand(ArrayList<Card> playerCards){
        this.playerCards = playerCards;
    }

    public ArrayList<Card> getCards() {
        return this.playerCards;
    }

    public int getSize(){
        return this.playerCards.size();
    }

    public void resetPickedCard(){
        this.pickedCard = null;
    }

    public void setPickedCard(Card pickedCard){
        for (Card playerCard : this.playerCards){
            if (playerCard.equals(pickedCard)){
                System.out.println("hello");
                this.pickedCard = playerCard;
                setChanged();
                notifyObservers();
                break;
            }
        }
    }

    public Card getPickedCard(){
        return pickedCard;
    }

    public void update(ArrayList<Card> currentPlayerCards){
        if (currentPlayerCards.size() > this.playerCards.size()){
            Card cardToAdd = currentPlayerCards.get(currentPlayerCards.size()-1);
            this.playerCards.add(cardToAdd);
        }
        else if (currentPlayerCards.size() < this.playerCards.size()){
            List<Card> c = new ArrayList<>(this.playerCards);
            c.removeAll(currentPlayerCards);
            Card cardToRemove = c.get(0);
            this.playerCards.remove(cardToRemove);
        }
        setChanged();
        notifyObservers();
    }

}
