package nl.rug.oop.cardgame.model;
import nl.rug.oop.cardgame.model.Game;
import nl.rug.oop.cardgame.view.GameFrame;
import nl.rug.oop.cardgame.model.elements.Deck;
import nl.rug.oop.cardgame.model.participants.Computer;
import nl.rug.oop.cardgame.model.participants.Player;

import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
        //System.out.println(game.faceUp.peekTopCard());
        //new GameFrame(game);
    }
}