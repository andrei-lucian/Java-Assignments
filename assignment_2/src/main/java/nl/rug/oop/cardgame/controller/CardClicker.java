package nl.rug.oop.cardgame.controller;

import nl.rug.oop.cardgame.model.Game;
import nl.rug.oop.cardgame.model.elements.Card;
import nl.rug.oop.cardgame.view.GamePanel;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

public class CardClicker extends MouseInputAdapter {

    private final Game game;
    private final GamePanel panel;

    /**
     * Create a new card clicker that receives mouse events from the DrawPanel
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
        playerCardClicked(event);
        deckClicked(event);
    }

    public void playerCardClicked(MouseEvent event){
        for (int i = game.getPlayerHand().size() - 1; i >= 0; i--) {
            Card card = game.getPlayerHand().get(i);
            Rectangle bounds = panel.getMapCards().get(card);
            if (bounds.contains(event.getPoint())) {
                if (game.isPlayerTurn()) {
                    game.setClickedCard(card);
                }
                break;
            }
        }
    }

    public void deckClicked(MouseEvent event){
        Rectangle deckBounds;
        deckBounds = panel.getDrawBounds().get(panel.getLastCard());
        if (deckBounds.contains(event.getPoint())){
            game.setClickedCard(game.getFaceDown().peekTopCard());
            System.out.println("You drew a card.");
        }
    }

    public void showMenu(MouseEvent event){

    }
}
