package nl.rug.oop.cardgame.participants;

import nl.rug.oop.cardgame.elements.Card;
import nl.rug.oop.cardgame.elements.Deck;
import java.util.ArrayList;
import java.util.Random;

public class Computer extends Participant {

    /** Choose the suit that appears the most in a computer's
     * stack of cards (used in case it deals an 8) */
    public Card.Suit chooseSuit(){
        int[] freq = new int[4];
        for (Card card : this.cardList){
            Card.Suit suit = card.getSuit();
            switch(suit){
                case HEARTS: freq[0]++; break;
                case SPADES: freq[1]++; break;
                case CLUBS: freq[2]++; break;
                case DIAMONDS: freq[3]++; break;
            }
        }
        int max = findMaxElement(freq);
        switch (max){
            case 0: return Card.Suit.HEARTS;
            case 1: return Card.Suit.SPADES;
            case 2: return Card.Suit.CLUBS;
            case 3: return Card.Suit.DIAMONDS;
        }
        return null;
    }

    /**
     * Find the index of the element with the maximum value.
     * @param arr Array of frequencies with which suits appear.
     * @return The index of the most frequent suit.
     */
    public int findMaxElement(int[] arr){
        int max = arr[0];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            if (max < arr[i]) {
                max = arr[i];
                index = i;
            }
        }
        return index;
    }

    /**
     * Finds all cards that the computer can put down.
     * @param suit The suit to be matched.
     * @param face The face to be matched.
     * @return An ArrayList of all the computer's possible options.
     */
    public ArrayList<Card> findCardOptions(Card.Suit suit, Card.Face face) {
        ArrayList<Card> cardOptions = new ArrayList<>();
        for (Card myCard : this.cardList) {
            if (myCard.getSuit() == suit || myCard.getFace() == face
                    || myCard.getFace() == Card.Face.EIGHT) {
                cardOptions.add(myCard);
            }
        }
        for (Card card : cardOptions){
            System.out.println(card);
        }
        return cardOptions;
    }

    /**
     * Returns the number of 8 cards the computer has.
     * @param cards An ArrayList of the computer's cards.
     * @return The number of 8 cards in the list.
     */
    public int countEights(ArrayList<Card> cards){
        int noOfEights = 0;
        for (Card card : cards){
            if (card.getFace() == Card.Face.EIGHT){
                noOfEights ++;
            }
        }
        return noOfEights;
    }

    /**
     * Plays or draws a card, depending on what the computer can do.
     * @param faceUp Face up deck.
     * @param faceDown Face down deck.
     * @param suit Suit to be matched.
     * @param face Face to be matched.
     */
    public void playCard(Deck faceUp, Deck faceDown, Card.Suit suit, Card.Face face) {
        ArrayList<Card> cardOptions = this.findCardOptions(suit, face);
        Card card;
        int noOfEights = countEights(cardOptions); //count the number of 8s in their hand

        if (cardOptions.size() == 1) { //if there is only one card then play that card
            card = cardOptions.get(0);
            this.putDownCard(faceUp, card);
            System.out.println("The computer played: " + card);
        }

        else if (cardOptions.size() > 1) {
            card = chooseCard(cardOptions, noOfEights);
            this.putDownCard(faceUp, card);
            System.out.println("The computer played: " + card);
        }
        else {
            this.drawCard(faceDown);
            System.out.println("The computer drew a card");
        }
    }

    /**
     * Randomly choose a non-8 card if there is more than one option
     * and if a non-8 card exists, else just choose an 8 card.
     * @param cardOptions ArrayList of all the cards a computer can put down.
     * @param eights Number of 8s in this ArrayList.
     * @return Chosen card to play.
     */
    public Card chooseCard(ArrayList<Card> cardOptions, int eights){
        Random r = new Random();
        int rand = r.nextInt(cardOptions.size() - 1);
        Card card = cardOptions.get(rand);

        while (card.getFace() == Card.Face.EIGHT && eights != cardOptions.size()) {
            rand = r.nextInt(cardOptions.size() - 1);
            card = cardOptions.get(rand);
        }
        return card;
    }
}
