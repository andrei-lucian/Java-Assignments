package nl.rug.oop.cardgame.model;
import nl.rug.oop.cardgame.view.GameFrame;
import nl.rug.oop.cardgame.model.elements.Deck;
import nl.rug.oop.cardgame.model.participants.Computer;
import nl.rug.oop.cardgame.model.participants.Player;

import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        new GameFrame();
        Player player = new Player();
        Computer computer = new Computer();
        Deck FaceDownDeck = Dealer.newFaceDownDeck();
        Deck FaceUpDeck = new Deck();
        Game.startGame(player, computer, FaceUpDeck, FaceDownDeck);
    }
}