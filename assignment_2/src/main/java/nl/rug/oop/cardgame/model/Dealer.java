package nl.rug.oop.cardgame.model;
import nl.rug.oop.cardgame.model.elements.Card;
import nl.rug.oop.cardgame.model.elements.Deck;
import nl.rug.oop.cardgame.model.participants.Computer;
import nl.rug.oop.cardgame.model.participants.Player;

/** Dealer class - holds all the methods to set up and maintain the game */
public class Dealer {

    /** Create a new, shuffled deck */
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
        if (newCard.getFace() == Card.Face.EIGHT){
            faceDown.addAnywhere(newCard);
            revealCard(faceDown, faceUp);
        }
        else {
            faceUp.addOnTop(newCard);
        }
    }

    /** Moves all the cards (except the top one) in
     * FaceUpDeck to FaceDownDeck in case FaceDownDeck runs out*/
    public static void transferDeck(Deck faceUp, Deck faceDown){
        Card savedCard = faceUp.draw();
        int size = faceUp.getCards().size();
        for (int i = 0; i < size; i++) {
            faceDown.addOnTop(faceUp.draw());
        }
        faceDown.shuffle();
        faceUp.addOnTop(savedCard);
    }

}
