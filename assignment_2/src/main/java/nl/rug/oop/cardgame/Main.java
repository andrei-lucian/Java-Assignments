package nl.rug.oop.cardgame;
import nl.rug.oop.cardgame.elements.Card;
import nl.rug.oop.cardgame.elements.Deck;
import nl.rug.oop.cardgame.participants.Computer;
import nl.rug.oop.cardgame.participants.Player;

import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        Player player = new Player();
        Computer computer = new Computer();
        Deck FaceDownDeck = Dealer.newFaceDownDeck();
        Deck FaceUpDeck = new Deck();
        Dealer.deal5Cards(player, computer, FaceDownDeck);
        Dealer.revealCard(FaceDownDeck, FaceUpDeck);
        Card.Suit currentSuit = FaceUpDeck.peekTopCard().getSuit();
        Card.Face currentFace = FaceUpDeck.peekTopCard().getFace();

        Game.gameLoop(player, computer, FaceUpDeck, FaceDownDeck, currentFace, currentSuit);
    }
}