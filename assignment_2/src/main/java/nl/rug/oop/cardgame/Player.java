package nl.rug.oop.cardgame;

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
}
