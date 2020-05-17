package nl.rug.oop.cardgame.view;

import nl.rug.oop.cardgame.model.elements.Deck;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class GamePanel extends JPanel implements Observer {

    private Deck faceDown;
    private Deck faceUp;
    private static final Color BACKGROUND_COLOR = new Color(18, 30, 75, 255);

    public GamePanel() {
        setBackground(BACKGROUND_COLOR);
        setVisible(true);
        setOpaque(true);
    }

    public void update(Observable observed, Object message) {
        repaint();
    }

    private void paintAreas(Graphics g) {
        g.setColor(Color.YELLOW);
        g.drawRect(0, 0, getWidth() / 2, getHeight() - 1);
        g.drawString("Deck Area", getWidth() / 4, 10);
        g.drawRect(getWidth() / 2, 0, getWidth() / 2 - 1, getHeight() - 1);
        g.drawString("Discard Area", 3 * (getWidth() / 4), 10);
        g.setColor(Color.BLACK);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        paintAreas(g);
    }
}
