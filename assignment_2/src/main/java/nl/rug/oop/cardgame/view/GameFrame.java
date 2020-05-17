package nl.rug.oop.cardgame.view;

import nl.rug.oop.cardgame.model.Game;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    public GameFrame(Game game) {
        super("Crazy Eights");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePanel panel = new GamePanel(game);
        add(panel);
        setPreferredSize(new Dimension(800, 600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
