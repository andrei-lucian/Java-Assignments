package nl.rug.oop.cardgame.view;

import nl.rug.oop.cardgame.model.Game;
import nl.rug.oop.cardgame.model.elements.Card;
import nl.rug.oop.cardgame.view.textures.CardBack;
import nl.rug.oop.cardgame.view.textures.CardBackTextures;
import nl.rug.oop.cardgame.view.textures.CardTextures;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

public class GamePanel extends JPanel implements Observer {

    private static final Color BACKGROUND_COLOR = new Color(10, 120, 81, 255);
    private Game game;
    private static final int CARD_SPACING = 1;
    private static final int Y_OFFSET = Card.values().length * CARD_SPACING;
    private static final double CARD_WIDTH = 43.6;
    private static final double CARD_HEIGHT = 60.0;

    public GamePanel(Game game) {
        this.game = game;
        setBackground(BACKGROUND_COLOR);
        setVisible(true);
        setOpaque(true);
        game.addObserver(this);
    }

    public void update(Observable observed, Object message) {
        repaint();
    }

    private void paintAreas(Graphics g) {
        g.setColor(Color.YELLOW);
//        g.drawRect(0, 0, getWidth() / 2, getHeight() - 1);
//        g.drawString("Deck Area", getWidth() / 4, 10);
//        g.drawRect(getWidth() / 2, 0, getWidth() / 2 - 1, getHeight() - 1);
//        g.drawString("Discard Area", 3 * (getWidth() / 4), 10);
        g.setColor(Color.BLACK);
    }


    private void paintFaceUpDeck(Graphics g) {
        int depth = 0;

        for (Card card : game.getFaceUp().getCards()) {

            int posX = getWidth() - getSpacing() - cardWidth()
                    + depth - Card.values().length;

            int posY = getSpacing() + Y_OFFSET - CARD_SPACING * depth;

            g.drawImage(CardTextures.getTexture(card)
                    , posX, posY, cardWidth(), cardHeight(), this);

            g.drawRect(posX, posY, cardWidth(), cardHeight());
            ++depth;
        }
    }

    private void paintPlayerHand(Graphics g) {

        for (Card card : game.getPlayerHand()) {

            int posX = (int) ((getWidth() / 2) - (cardWidth() * (game.getPlayerHand().size() / 4.0)));
            int posY = (getHeight() - 20) - cardHeight();

            g.drawImage(CardTextures.getTexture(card)
                    , posX, posY, cardWidth(), cardHeight(), this);

            g.drawRect(posX, posY, cardWidth(), cardHeight());
        }

    }

    private void paintComputerHand(Graphics g) {

        for (Card card : game.getComputerHand()) {


        }
    }

    private void paintFaceDownDeck(Graphics g) {
        int depth;
        BufferedImage cardBackTexture = CardBackTextures.getTexture(CardBack.CARD_BACK_BLUE);

        for (depth = 0; depth < game.getFaceDown().getCards().size(); depth++) {

            int posX = getSpacing() + depth;
            int posY = getSpacing() + Y_OFFSET - CARD_SPACING * depth;

            g.drawImage(cardBackTexture, posX, posY, cardWidth(), cardHeight(), this);
            g.drawRect(posX, posY, cardWidth(), cardHeight());
        }
        /*
        MovableCard dependency = drawGame.getMovableCard();
        if (dependency == null) {
            return;
        }

        movableX = getSpacing() + depth + dependency.getRelativeX();
        movableY = getSpacing() + Y_OFFSET - CARD_SPACING * depth + dependency.getRelativeY();
        g.drawImage(cardBackTexture, movableX, movableY, cardWidth(), cardHeight(), this);
        g.drawRect(movableX, movableY, cardWidth(), cardHeight()); */
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        paintAreas(g);
        paintFaceUpDeck(g);
        paintFaceDownDeck(g);
        paintPlayerHand(g);
    }

    /**
     * Get the scaled spacing between edges and cards
     */
    private int getSpacing() {
        return (int) ((getHeight() * 20) / CARD_HEIGHT);
    }

    /**
     * Get the scaled width of cards. Default height is 600, default
     * width is 436, and cards are scaled depending on which dimension limits
     * their relative dimensions
     */
    public int cardWidth() {
        if ((getHeight() * CARD_HEIGHT) / (getWidth() * CARD_WIDTH) <= 1.0)
            return (int) ((cardHeight() * CARD_WIDTH) / CARD_HEIGHT);
        return (getWidth() - getSpacing() * 3 - 2 * Card.values().length) / 2;
    }

    /**
     * Get the scaled height of cards. Default height is 600, default
     * width is 436, and cards are scaled depending on which dimension limits
     * their relative dimensions
     */

    public int cardHeight() {
        if ((getHeight() * CARD_HEIGHT) / (getWidth() * CARD_WIDTH) > 1.0)
            return (int) ((cardWidth() * CARD_HEIGHT) / CARD_WIDTH);
        return (getHeight() - getSpacing() * 2 - 2 * Card.values().length);
    }

}
