package nl.rug.oop.cardgame.view;

import nl.rug.oop.cardgame.model.Game;
import nl.rug.oop.cardgame.model.elements.Card;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Collections;

public class CardClicker extends MouseInputAdapter {

    private Game game;
    private GamePanel panel;

    /**
     * Boolean denoting whether a card is selected.
     */
    private boolean selected;

    /**
     * Starting x position of a mousePressed. Used for dragging.
     */
    private int startX;

    /**
     * Starting y position of a mousePressed. Used for dragging.
     */
    private int startY;

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
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        selected = false;
    }

    /**
     * If the mouse button is pressed in the area where the top card is
     * drawn (obviously a lack of drawable cards makes this impossible)
     * that card is 'selected' so it can be dragged.
     *
     * @param event The MouseEvent needed to locate the position of the cursor
     */
    @Override
    public void mousePressed(MouseEvent event) {
        if (panel.getSelected() != null) {
            Rectangle bounds = panel.getMapCards().get(selected);
            bounds.y += 20;
            panel.repaint();
        }
        panel.setSelected(null);
        // This is done backwards, as the last card is on
        // top.  Of course you could render the cards
        // in reverse order, but you get the idea
        for (Card card : Collections.reverse( )) {
            Rectangle bounds = panel.getMapCards().get(card);
            if (bounds.contains(event.getPoint())) {
                panel.setSelected(card);
                bounds.y -= 20;
                panel.repaint();
                break;
            }
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
