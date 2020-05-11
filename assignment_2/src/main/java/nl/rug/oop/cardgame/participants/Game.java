package nl.rug.oop.cardgame.participants;

import nl.rug.oop.cardgame.Dealer;
import nl.rug.oop.cardgame.elements.Card;
import nl.rug.oop.cardgame.elements.Deck;

public class Game {

    /** If one of the players has no cards left then they win and the game is over */

    public static void setUpGame(){
        Player player = new Player();
        Computer computer = new Computer();
        Deck FaceDownDeck = Dealer.newFaceDownDeck();
        Deck FaceUpDeck = new Deck();
        Dealer.deal5Cards(player, computer, FaceDownDeck);
        Dealer.revealCard(FaceDownDeck, FaceUpDeck);
    }

    public boolean checkWinCondition(Player player, Computer computer){
        if (player.noOfCards() == 0 || computer.noOfCards() == 0){
            return true;
        }
        else {
            return false;
        }
    }
}

