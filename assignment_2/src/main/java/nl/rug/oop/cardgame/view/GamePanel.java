package nl.rug.oop.cardgame.view;

import nl.rug.oop.cardgame.model.Game;
import nl.rug.oop.cardgame.model.elements.Card;
import nl.rug.oop.cardgame.view.textures.CardBack;
import nl.rug.oop.cardgame.view.textures.CardBackTextures;
import nl.rug.oop.cardgame.view.textures.CardTextures;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class GamePanel extends JPanel implements Observer{

    private static final Color BACKGROUND_COLOR = new Color(10, 120, 81, 255);
    private final Game game;
    private static final int CARD_SPACING = 1;
    private static final int Y_OFFSET = Card.values().length * CARD_SPACING;
    private static final double CARD_WIDTH = 43.6;
    private static final double CARD_HEIGHT = 60.0;
    private Map<ClickableCard, Rectangle> playerCards;
    private HashMap<Card, Rectangle> drawCards;
    private ClickableCard selected;
    private Card hovered;
    private Card topCard;

    public GamePanel(Game game) {
        this.game = game;
        setBackground(BACKGROUND_COLOR);
        setVisible(true);
        setOpaque(true);
        addMouseListener(new CardClicker(this.game, this));
        game.addObserver(this);
        playerCards = new HashMap<>(game.getPlayerCards().size()* 5);
        drawCards = new HashMap<>();
    }

    /** Return the HashMap of cards and the
     * rectangle defining their bounds */
    public Map<ClickableCard, Rectangle> getPlayerCards() {
        return this.playerCards;
    }

    /** Return the bounds of a card */
    public Map<Card, Rectangle> getDrawBounds() {
        return drawCards;
    }

    /** Return the selected card */
    public ClickableCard getSelected() {
        return selected;
    }

    /** Set the selected card */
    public void setSelected(ClickableCard selected) {
        this.selected = selected;
    }

    public Card getHovered() {
        return hovered;
    }

    public void setHovered(Card hovered) {
        this.hovered = hovered;
    }

    /** Return the last card of the faceDown deck */
    public Card getTopCard() {
        return topCard;
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
        int size = game.getFaceDown().getCards().size();
        for (int x = 0; x < size; x++) {

            posX = getSpacing() + depth;
            posY = getSpacing() + Y_OFFSET - CARD_SPACING * depth;

            g.drawImage(cardBackTexture, posX, posY, cardWidth(), cardHeight(), this);
            g.drawRect(posX, posY, cardWidth(), cardHeight());
            depth++;
        }
        topCard = game.getFaceDown().peekTopCard();
        Rectangle bounds = new Rectangle(posX, posY, cardWidth(), cardHeight());
        drawCards.put(topCard, bounds);
        //System.out.println(bounds);
    }

    /**
     * Separated paintPlayerHand and paintCard
     * trying to get the cards to lift up
     * didn't work tho...
     * the problem is that the bounds update
     * in the cardClicker but it ends up being
     * overridden in the paintCard method before they get drawn
    * */
    public void paintPlayerHand() {
        this.playerCards.clear();
        int move = cardWidth() / 2;
        int posX = (int) ((getWidth() / 2) - (cardWidth() * (game.getPlayerCards().size() / 4.0)));

        for (ClickableCard card : game.getClickableCards()) {
            System.out.println(card.getCard());
            int posY = (getHeight() - 20) - cardHeight() + card.getRelativeY();
            Rectangle bounds = new Rectangle(posX + move, posY, cardWidth(), cardHeight());
            playerCards.put(card, bounds);
            posX += cardWidth() / 2;
            System.out.println(bounds);
        }
    }

    private void paintCard(Graphics g){
        paintPlayerHand();
        System.out.println("map made");
        for (Map.Entry<ClickableCard, Rectangle> entry : playerCards.entrySet()) {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
        for (ClickableCard card : game.getClickableCards()) {
            System.out.println(card);
            Rectangle bounds = playerCards.get(card);
            if (bounds != null) {
                g.drawImage(CardTextures.getTexture(card.getCard())
                        , bounds.x, bounds.y, cardWidth(), cardHeight(), this);

                g.drawRect(bounds.x, bounds.y, cardWidth(), cardHeight());
            }
        }
    }

    private void paintComputerHand(Graphics g) {
        BufferedImage cardBackTexture = CardBackTextures.getTexture(CardBack.CARD_BACK_BLUE);
        int move = 0;
        for(int x = 0; x <= game.getComputerHand().size(); x++) {
            int posX = (int) ((getWidth() / 2) - (cardWidth() * (game.getComputerHand().size() / 4.0)));
            int posY = 20;

            g.drawImage(cardBackTexture
                    , posX + move, posY, cardWidth(), cardHeight(), this);

            g.drawRect(posX + move, posY, cardWidth(), cardHeight());
            move += cardWidth() / 2;
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        paintCard(g);
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

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
