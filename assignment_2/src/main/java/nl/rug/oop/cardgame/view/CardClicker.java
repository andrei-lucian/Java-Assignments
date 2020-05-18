package nl.rug.oop.cardgame.view;

import nl.rug.oop.cardgame.model.Game;
import nl.rug.oop.cardgame.model.elements.Card;
import nl.rug.oop.cardgame.model.utils.ReverseArrayList;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

public class CardClicker extends MouseInputAdapter implements ReverseArrayList {

    private Game game;
    private GamePanel panel;

    /**
     * Boolean denoting whether a card is selected.
     */
    private boolean selected;

    /**
     * Create a new card dragger that receives mouse events from the DrawPanel
     * supplied to this constructor
     *
     * @param game The actual Game
     * @param panel    DrawPanel needed to receive mouse events from
     */
    public CardClicker(Game game, GamePanel panel) {
        this.game = game;
        this.panel = panel;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        if (panel.getSelected() != null) {
            Rectangle bounds = panel.getMapCards().get(panel.getSelected());
            bounds.y += 20;
            panel.repaint();
        }
        panel.setSelected(null);
        // This is done backwards, as the last card is on
        // top.  Of course you could render the cards
        // in reverse order, but you get the idea
        for (Card card : game.getPlayerHand()) {
            Rectangle bounds = panel.getMapCards().get(card);
            if (bounds.contains(event.getPoint())) {
                panel.setSelected(card);
                System.out.println(panel.getSelected());
                int counter = 0;
                counter += 1;
                System.out.println(counter);
                bounds.y -= 20;
                panel.repaint();
                break;
            }
        }
    }

    /**
     * When the top card is released with the mouse in the discard square,
     * the card is moved.
     *
     * @param event The MouseEvent needed to locate the position of the cursor

    @Override
    public void mouseReleased(MouseEvent event) {
        if (selected) {
            if (panel.inDiscardArea(event.getPoint()))
                drawGame.move();
            else {
                drawGame.getMovableCard().setRelativeX(0);
                drawGame.getMovableCard().setRelativeY(0);
            }
        }
        selected = false;
    }

    /**
     * If a card is selected it is moved relative to the positions the mouse
     * was first pressed.
     *
     * @param event The MouseEvent needed to locate the position of the cursor
     */
//    @Override
//    public void mouseDragged(MouseEvent event) {
//        if (selected) {
//            drawGame.getMovableCard().setRelativeX(event.getX() - startX);
//            drawGame.getMovableCard().setRelativeY(event.getY() - startY);
//        }
//    }

}
