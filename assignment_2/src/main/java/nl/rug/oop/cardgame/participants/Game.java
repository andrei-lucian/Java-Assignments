package nl.rug.oop.cardgame.participants;

import nl.rug.oop.cardgame.Dealer;
import nl.rug.oop.cardgame.elements.Card;
import nl.rug.oop.cardgame.elements.Deck;

public class Game {

    /** Set up the game: create a player, computer, FaceUp and FaceDown deck,
     * deal 5 cards to each player, and place the first card face up. */
    public static void setUpGame(){
        Player player = new Player();
        Computer computer = new Computer();
        Deck FaceDownDeck = Dealer.newFaceDownDeck();
        Deck FaceUpDeck = new Deck();
        Dealer.deal5Cards(player, computer, FaceDownDeck);
        Dealer.revealCard(FaceDownDeck, FaceUpDeck);
    }

    /** Returns the suit of the top card on the faceUp deck. */
    public Card.Suit getCurrentSuit(Deck deck){
        return deck.getCards().peek().getSuit();
    }

    /** Returns the face of the top card on the faceUp deck. */
    public Card.Face getCurrentFace(Deck deck){
        return deck.getCards().peek().getFace();
    }

    /** If one of the players has no cards left then they win and the game is over */
    public boolean checkWinCondition(Player player, Computer computer){
        return player.noOfCards() == 0 || computer.noOfCards() == 0;
    }
}

