package nl.rug.oop.cardgame.view;

import nl.rug.oop.cardgame.model.Game;
import nl.rug.oop.cardgame.model.elements.Card;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

public class CardClicker extends MouseInputAdapter {

    private final Game game;
    private final GamePanel panel;

    /**
     * Create a new card dragger that receives mouse events from the DrawPanel
     * supplied to this constructor
     *
     * @param game The actual Game
     * @param panel DrawPanel needed to receive mouse events from
     */
    public CardClicker(Game game, GamePanel panel) {
        this.game = game;
        this.panel = panel;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        game.setX(10);
        //System.out.println(game.getX());
        for (int i = game.getPlayerHand().size() - 1; i >= 0; i--) {
            Card card = game.getPlayerHand().get(i);
            Rectangle bounds = panel.getMapCards().get(card);
            if (bounds.contains(event.getPoint())) {
                game.setChosenCard(card);
                System.out.println(game.getChosenCard());
                break;
            }
        }
        //draw from deck
        Rectangle deckBounds;
        deckBounds = panel.getDrawBounds().get(panel.getLastCard());
        if (deckBounds.contains(event.getPoint())){
            game.setChosenCard(game.getFaceDown().peekTopCard());
            System.out.println("Card drawn");
        }
    }
}
