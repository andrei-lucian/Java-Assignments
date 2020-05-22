package nl.rug.oop.cardgame.view;

import nl.rug.oop.cardgame.controller.Clicker;
import nl.rug.oop.cardgame.model.Game;
import nl.rug.oop.cardgame.model.elements.Card;
import nl.rug.oop.cardgame.view.textures.CardBack;
import nl.rug.oop.cardgame.view.textures.CardBackTextures;
import nl.rug.oop.cardgame.view.textures.CardTextures;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class GamePanel extends JPanel implements Observer {

    private final Game game;
    private static final Color BACKGROUND_COLOR = new Color(10, 120, 81, 255);
    private static final int CARD_SPACING = 1;
    private static final int Y_OFFSET = Card.values().length * CARD_SPACING;
    private static final double CARD_WIDTH = 43.6;
    private static final double CARD_HEIGHT = 60.0;
    private final Map<Card, Rectangle> mapCards;
    private final HashMap<Card, Rectangle> drawCards;
    private Card lastCard;

    public GamePanel(Game game) {
        this.game = game;
        setBackground(BACKGROUND_COLOR);
        setVisible(true);
        setOpaque(true);
        addMouseListener(new Clicker(this.game, this));
        game.addObserver(this);
        mapCards = new HashMap<>(54);
        drawCards = new HashMap<>();
    }

    public int cardWidth() {
        if ((getHeight() * CARD_HEIGHT) / (getWidth() * CARD_WIDTH) <= 1.0)
            return (int) ((cardHeight() * CARD_WIDTH) / CARD_HEIGHT);
        return (getWidth() - getSpacing() * 3 - 2 * Card.values().length) / 2;
    }

    public int cardHeight() {
        if ((getHeight() * CARD_HEIGHT) / (getWidth() * CARD_WIDTH) > 1.0)
            return (int) ((cardWidth() * CARD_HEIGHT) / CARD_WIDTH);
        return (getHeight() - getSpacing() * 2 - 2 * Card.values().length);
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

    private void paintFaceDownDeck(Graphics g) {
        int depth = 0;
        BufferedImage cardBackTexture = CardBackTextures.getTexture(CardBack.CARD_BACK_BLUE);
        int posX = 0;
        int posY = 0;

        for (Card card : game.getFaceDown().getCards()) {

            posX = getSpacing() + depth;
            posY = getSpacing() + Y_OFFSET - CARD_SPACING * depth;

            g.drawImage(cardBackTexture, posX, posY, cardWidth(), cardHeight(), this);
            g.drawRect(posX, posY, cardWidth(), cardHeight());

            lastCard = card;
            depth++;
        }
        //storing last card and its bounds in order to draw from deck
        Rectangle bounds = new Rectangle(posX, posY, cardWidth(), cardHeight());
        drawCards.put(lastCard, bounds);
    }

    public void paintPlayerHand() {
        mapCards.clear();
        int move = cardWidth() / 2;
        int posX = (int) ((getWidth() / 2) - (cardWidth() * (game.getPlayerHand().size() / 4.0))-55);
        int posY = (getHeight() - 20) - cardHeight();
        for (Card card : game.getPlayerHand()) {
            Rectangle bounds = new Rectangle(posX + move, posY, cardWidth(), cardHeight());
            mapCards.put(card, bounds);
            posX += cardWidth() / 2;
        }
    }

    private void paintCard(Graphics g){
        paintPlayerHand();
        for (Card card : game.getPlayerHand()) {
            Rectangle bounds = mapCards.get(card);
            g.drawImage(CardTextures.getTexture(card)
                    , bounds.x, bounds.y, cardWidth(), cardHeight(), this);

            g.drawRect(bounds.x, bounds.y, cardWidth(), cardHeight());
        }
    }

    private void paintComputerHand(Graphics g) {
        BufferedImage cardBackTexture = CardBackTextures.getTexture(CardBack.CARD_BACK_BLUE);
        int move = 0;
        for(int x = 0; x <= game.getComputerHand().size() -1 ; x++) {
            int posX = (int) ((getWidth() / 2) - (cardWidth() * (game.getComputerHand().size() / 4.0)));
            int posY = 20;

            g.drawImage(cardBackTexture
                    , posX + move, posY, cardWidth(), cardHeight(), this);

            g.drawRect(posX + move, posY, cardWidth(), cardHeight());
            move += cardWidth() / 2;
        }
    }

    public void paintSuit (Graphics g){
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(game.getSuitString(), 390, 550 );
    }

    public void paintChooseMessage(Graphics g){
        g.setFont(new Font("Arial", Font.BOLD, 13));
        if (game.getChooseAgain()){
            g.drawString("Please choose again", 550, 580);
        }
    }

    public void paintRules (Graphics g){
        g.setFont(new Font("Arial", Font.BOLD, 13));
        g.drawString("Rules:", 20, 240 );
        g.drawString("1.Match the suit or number with", 40, 260 );
        g.drawString("the card on the table.", 40, 280 );
        g.drawString("2.Eights can be played", 40, 320 );
        g.drawString("on top of everything", 40, 340 );
        g.drawString("and they allow you to switch", 40, 360 );
        g.drawString("to your preferred suit.", 40, 380 );
        g.drawString("3.Click the deck to draw a card", 40, 420 );
        g.drawString("if you can't play anything", 40, 440 );
        g.drawString("3.Finish your hand to win.", 40, 480 );
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        paintCard(g);
        paintSuit(g);
        paintChooseMessage(g);
        paintRules(g);
        paintComputerHand(g);
        paintFaceDownDeck(g);
        paintFaceUpDeck(g);
    }

    /**
     * Get the scaled spacing between edges and cards
     */
    private int getSpacing() {
        return (int) ((getHeight() * 20) / CARD_HEIGHT);
    }

    public Map<Card, Rectangle> getMapCards() {
        return this.mapCards;
    }

    public Map<Card, Rectangle> getDrawBounds() {
        return drawCards;
    }

    public Card getLastCard() {
        return lastCard;
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}