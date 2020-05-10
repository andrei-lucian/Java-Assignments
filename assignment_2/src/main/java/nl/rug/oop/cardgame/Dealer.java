package nl.rug.oop.cardgame;


/** Dealer class - holds all the methods to set up and maintain the game */
public class Dealer {

    public static Deck FaceDownDeck = createFaceDownDeck();
    public static Deck FaceUpDeck = createFaceUpDeck();

    public static Deck createFaceDownDeck(){
        Deck FaceDownDeck = new Deck();
        FaceDownDeck.addCards();
        FaceDownDeck.shuffle();
        return FaceDownDeck;
    }

    public static Deck createFaceUpDeck(){
        Deck FaceUpDeck = new Deck();
        return FaceUpDeck;
    }

    public static void deal5Cards(Player player, Computer computer, Deck deck){
    }

    /** Moves the top card of FaceDownDeck to the top of FaceUpDeck */
    public static Card revealCard(){
    }

    /** Moves all the cards (except the top one) in
     * FaceUpDeck to FaceDownDeck in case FaceDownDeck runs out*/
    public static void resetDeck(){

    }

}
