package nl.rug.oop.cardgame;


/** Dealer class - holds all the methods to set up and maintain the game */
public class Dealer {

    public static Deck FaceDownDeck = createFaceDownDeck();
    public static Deck FaceUpDeck = new Deck();

    public static Deck createFaceDownDeck(){
        Deck FaceDownDeck = new Deck();
        FaceDownDeck.addCards();
        FaceDownDeck.shuffle();
        return FaceDownDeck;
    }

    /** Deals 5 cards to each player at the beginning of the game */
    public static void deal5Cards(Player player, Computer computer){
        for (int i = 0; i < 5; i++) {
            player.drawCard(FaceDownDeck);
            computer.drawCard(FaceDownDeck);
        }
    }

    /** Moves the top card of FaceDownDeck to the top of FaceUpDeck and returns the card*/
    public static Card revealCard(){
        Card newCard = FaceDownDeck.draw();
        FaceUpDeck.addOnTop(newCard);
        return newCard;
    }

    /** Moves all the cards (except the top one) in
     * FaceUpDeck to FaceDownDeck in case FaceDownDeck runs out*/
    public static void transferDeck(){
        Card savedCard = FaceUpDeck.draw();
        for (Card card: FaceUpDeck.getCards()){
            FaceDownDeck.addOnTop(FaceUpDeck.draw());
            FaceDownDeck.shuffle();
            FaceUpDeck.addOnTop(savedCard);
        }
    }

}
