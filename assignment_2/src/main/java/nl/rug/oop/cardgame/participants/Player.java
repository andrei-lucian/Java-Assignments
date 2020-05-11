package nl.rug.oop.cardgame.participants;

import nl.rug.oop.cardgame.elements.Card;
import nl.rug.oop.cardgame.elements.Deck;
import nl.rug.oop.cardgame.utils.CatchNonInts;

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

    /** Choose a card to play. Only allows a card that matches either the face or suit, or is an 8. */
    public void playCard(boolean printed, Deck faceDown, Deck faceUp, Card.Face face, Card.Suit suit){
        if(!printed) {
            System.out.println("Which card do you want to play? (-1) Draw a card.");
            this.inspectAllCards();
        }
        int option = CatchNonInts.inputOption();
        if (option < -1 && option >= this.noOfCards()){
            System.out.println("Not an option, try again.");
            playCard(true, faceDown, faceUp, face, suit);
        }
        else if (option == -1){
            this.drawCard(faceDown);
        }
        else {
            Card card = this.cardList.get(option);
            if (card.getFace() == face || card.getSuit() == suit || card.getFace() == Card.Face.EIGHT) {
                this.putDownCard(faceUp, card);
            }
            else {
                System.out.println("This card does not match the requirements, try again.");
                playCard(true, faceDown, faceUp, face, suit);
            }
        }
    }
}
