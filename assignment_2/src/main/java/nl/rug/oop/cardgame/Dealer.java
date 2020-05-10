package nl.rug.oop.cardgame;
import nl.rug.oop.cardgame.elements.Card;
import nl.rug.oop.cardgame.elements.Deck;
import nl.rug.oop.cardgame.participants.Computer;
import nl.rug.oop.cardgame.participants.Player;

/** Dealer class - holds all the methods to set up and maintain the game */
public class Dealer {

    public static Deck newFaceDownDeck(){
        Deck FaceDownDeck = new Deck();
        FaceDownDeck.addCards();
        FaceDownDeck.shuffle();
        return FaceDownDeck;
    }

    /** Deals 5 cards to each player at the beginning of the game */
    public static void deal5Cards(Player player, Computer computer, Deck faceDown){
        for (int i = 0; i < 5; i++) {
            player.drawCard(faceDown);
            computer.drawCard(faceDown);
        }
    }

    /** Moves the top card of FaceDownDeck to the top of FaceUpDeck */
    public static void revealCard(Deck faceDown, Deck faceUp){
        Card newCard = faceDown.draw();
        faceUp.addOnTop(newCard);
    }

    /** Moves all the cards (except the top one) in
     * FaceUpDeck to FaceDownDeck in case FaceDownDeck runs out*/
    public static void transferDeck(Deck faceUp, Deck faceDown){
        Card savedCard = faceUp.draw();
        System.out.println("\n \n CHECK SIZE!!!" + faceUp.getCards().size() + "\n \n");
        int size = faceUp.getCards().size();
        for (int i = 0; i < size; i++) {
            faceDown.addOnTop(faceUp.draw());
        }
        faceDown.shuffle();
        faceUp.addOnTop(savedCard);
    }

}
