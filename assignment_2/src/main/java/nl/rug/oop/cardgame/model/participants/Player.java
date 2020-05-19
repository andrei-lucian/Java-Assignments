package nl.rug.oop.cardgame.model.participants;

import nl.rug.oop.cardgame.model.elements.Card;
import nl.rug.oop.cardgame.model.elements.Deck;
import nl.rug.oop.cardgame.model.utils.CatchNonInts;

public class Player extends Participant {

    /** Player chooses a suit in case they put down an 8 */
    public Card.Suit chooseSuit(){
        Card.Suit suit;
        System.out.println("Choose a suit: \n" +
                "(0) Hearts\n" +
                "(1) Clubs\n" +
                "(2) Diamonds\n" +
                "(3) Spades");
        int option = CatchNonInts.inputOption();
        switch (option){
            case 0: suit = Card.Suit.HEARTS; return suit;
            case 1: suit = Card.Suit.CLUBS; return suit;
            case 2: suit = Card.Suit.DIAMONDS; return suit;
            case 3: suit = Card.Suit.SPADES; return suit;
            default: System.out.println("Not an option, choose again"); chooseSuit();
        }
        return null;
    }

    /** Player chooses a card from the terminal (within bounds) */
    /*private int chosenOption() {
        boolean outOfBounds = true;
        int option = 0;
        while (outOfBounds) {
            System.out.println("Which card do you want to play? (-1) Draw a card.");
            this.inspectAllCards();
            option = CatchNonInts.inputOption();
            if (option < -1 || option >= this.noOfCards()) {
                System.out.println("Not an option, try again.");
            } else {
                outOfBounds = false;
            }
        }
        return option;
    }*/

    /** Makes sure the player selects a valid card */
    private boolean accordingToRules(Card card, Card.Face face, Card.Suit suit){
        if (card.getFace() == face || card.getSuit() == suit || card.getFace() == Card.Face.EIGHT) {
            return true;
        }
        else {
            System.out.println("This card does not match the requirements, try again.");
            return false;
        }
    }

    /**
     * Choose a card to play. Only allows a card that matches either the face or suit, or is an 8.
     * @param faceDown The face down deck.
     * @param faceUp The face up deck.
     * @param face The face to be matched.
     * @param suit The suit to be matched.
     */
    public Card playCard(Card card, Deck faceDown, Deck faceUp, Card.Face face, Card.Suit suit) {
        while (true) {
            /*(int option = chosenOption();
            if (option == -1) {
                System.out.println("You drew a card");
                this.drawCard(faceDown);
                return null;
            } else {*/
            if (card == faceDown.peekTopCard()) {
                drawCard(faceDown);
                return null;
            }
            else {
                if (accordingToRules(card, face, suit)) {
                    this.putDownCard(faceUp, card);
                    System.out.println("You played:  " + card);
                    return card;
                }
            }
        }
    }
}
