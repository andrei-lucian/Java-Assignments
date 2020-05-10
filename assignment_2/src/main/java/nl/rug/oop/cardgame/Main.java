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
        player.inspectAllCards();
        ArrayList<Card> options = computer.findCardOptions(Card.Suit.SPADES, Card.Face.TEN);
        System.out.println(computer.chooseCard(options, FaceDownDeck));
        System.out.println(computer.chooseSuit());

    }
}