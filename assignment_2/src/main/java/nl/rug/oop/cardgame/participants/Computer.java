package nl.rug.oop.cardgame.participants;

import nl.rug.oop.cardgame.elements.Card;
import nl.rug.oop.cardgame.elements.Deck;
import java.util.ArrayList;
import java.util.Random;

public class Computer extends Participant {

    /** NOT IMPLEMENTED YET
     * Choose the suit that appears the most in a computer's
     * stack of cards, in case it deals an 8 */
    public Card.Suit chooseSuit(){
        int[] freq = new int[4];
        for (Card card : this.cardList){
            Card.Suit suit = card.getSuit();
            System.out.println(suit);
            switch(suit){
                case HEARTS: freq[0]++; //System.out.println(freq[0] + " hearts");
                case SPADES: freq[1]++; //System.out.println(freq[1] + " spades");
                case CLUBS: freq[2]++; //System.out.println(freq[2] + " clubs");
                case DIAMONDS: freq[3]++; //System.out.println(freq[3] + " diamonds");
            }
        }
        for (int x = 0; x < freq.length; x++){
            System.out.println(freq[x]);
        }
        int max = findMaxElement(freq);
        //System.out.println(max);
        switch (max){
            case 0: return Card.Suit.HEARTS;
            case 1: return Card.Suit.SPADES;
            case 2: return Card.Suit.CLUBS;
            case 3: return Card.Suit.DIAMONDS;
        }
        return null;
    }

    /** Find the index of the element with the maximum value */
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

    /** NOT IMPLEMENTED YET
     * Compare a card's suit and face to all the cards that the computer has
     * and return a list of all the cards that can be used */
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

    /** Return the number of eights in a computer's pack of cards */
    public int countEights(ArrayList<Card> cards){
        int noOfEights = 0;
        for (Card card : cards){
            if (card.getFace() == Card.Face.EIGHT){
                noOfEights ++;
            }
        }
        return noOfEights;
    }

    /** Choose a card from the list of available cards
     * Draws a card if if there are no matching cards
     * Chooses the non-8 card if there is more than one option */
    public Card chooseCard(ArrayList<Card> cardOptions, Deck deck){
        Card card;
        int noOfEights = countEights(cardOptions); //count the number of 8s in their hand

        if (cardOptions.size() == 1){ //if there is only one card then play that card
            return cardOptions.get(0);
        }

        /* if there is more than one card then pick a non-8 one
        as long as there is a card that is not an 8 */
        else if (cardOptions.size() > 1) {
            Random r = new Random();
            int rand = r.nextInt(cardOptions.size() - 1);
            card = cardOptions.get(rand);

            while (card.getFace() == Card.Face.EIGHT && noOfEights != cardOptions.size()) {
                rand = r.nextInt(cardOptions.size() - 1);
                card = cardOptions.get(rand);
            }
            System.out.println("The computer played: " + card);
            return card;
        }
        //draw a card if there is no available card
        else {
            this.drawCard(deck);
            System.out.println("The computer drew a card");
            return null;
        }
    }

}
