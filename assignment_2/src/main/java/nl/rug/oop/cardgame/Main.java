package nl.rug.oop.cardgame;
import nl.rug.oop.cardgame.elements.Card;
import nl.rug.oop.cardgame.elements.Deck;
import nl.rug.oop.cardgame.participants.Computer;
import nl.rug.oop.cardgame.participants.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        Deck FaceDownDeck = Dealer.newFaceDownDeck();
        Deck FaceUpDeck = new Deck();
        Computer computer = new Computer();
        Player player = new Player();
        Dealer.deal5Cards(player, computer, FaceDownDeck);
        computer.inspectAllCards();
        ArrayList<Card> options = computer.findCardOptions(Card.Suit.SPADES, Card.Face.TEN);
        computer.playCard(FaceUpDeck, FaceDownDeck, Card.Suit.SPADES, Card.Face.TEN);
        //Card.Suit suit = computer.chooseSuit();
        //System.out.println(suit);

    }
}