package nl.rug.oop.cardgame;
import nl.rug.oop.cardgame.model.Game;
import nl.rug.oop.cardgame.view.GameFrame;

import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}