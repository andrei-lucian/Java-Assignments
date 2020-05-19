package nl.rug.oop.cardgame.view;

import nl.rug.oop.cardgame.model.Game;
import nl.rug.oop.cardgame.model.elements.Card;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

public class CardClicker extends MouseInputAdapter{

    private final Game game;
    private final GamePanel panel;

    public CardClicker(Game game, GamePanel panel) {
        this.game = game;
        this.panel = panel;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        if (panel.getSelected() != null) {
            panel.getSelected().setRelativeY(-20);
            System.out.println("go back down");
            panel.repaint();
        }
        panel.setSelected(null);
        for (int i = game.getClickableCards().size() - 1; i >= 0; i--) {
            ClickableCard card = game.getClickableCards().get(i);
            Rectangle bounds = panel.getPlayerCards().get(card);
            if (bounds.contains(event.getPoint())) {
                panel.setSelected(card);
                System.out.println("lift up");
                card.setRelativeY(-20);
                panel.repaint();
                System.out.println(panel.getSelected());
                break;
            }
        }

        Rectangle deckBounds;
        deckBounds = panel.getDrawBounds().get(panel.getTopCard());
        if (deckBounds.contains(event.getPoint())){
            game.getPlayer().drawCard(game.getFaceDown());
            panel.repaint();
        }
    }

    /*
    @Override
    public void mouseEntered(MouseEvent event) {
        if (panel.getHovered() != null) {
            Rectangle bounds = panel.getMapCards().get(panel.getSelected());
            bounds.y += 20;
            panel.repaint();
        }
        panel.setHovered(null);
        for (int i = game.getPlayerHand().size() - 1; i >= 0; i--) {
            Card card = game.getPlayerHand().get(i);
            Rectangle bounds = panel.getMapCards().get(card);
            if (bounds.contains(event.getPoint())) {
                panel.setHovered(card);
                System.out.println(panel.getHovered());
                bounds.y -= 20;
                panel.repaint();
                break;
            }
        }*/
}
