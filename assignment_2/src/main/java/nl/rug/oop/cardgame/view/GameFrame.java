package nl.rug.oop.cardgame.view;

import nl.rug.oop.cardgame.model.Game;
import nl.rug.oop.cardgame.model.elements.Card;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    public GameFrame(Game game) {
        super("Crazy Eights");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        GamePanel panel = new GamePanel(game);
        add(panel);
        setPreferredSize(new Dimension(1200, 800));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private Card chosenCard = null;

    public void setChosenCard(Card card){
        chosenCard = card;
    }
}
