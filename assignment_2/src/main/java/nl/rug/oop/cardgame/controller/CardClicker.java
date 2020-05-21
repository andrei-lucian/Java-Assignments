package nl.rug.oop.cardgame.controller;

import nl.rug.oop.cardgame.model.Game;
import nl.rug.oop.cardgame.model.elements.Card;
import nl.rug.oop.cardgame.view.GamePanel;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

public class CardClicker extends MouseInputAdapter {

    private final Game game;
    private final GamePanel panel;
    private boolean selected = false;

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
        if (game.isPlayerTurn()) {
            playerCardClicked(event);
            deckClicked(event);
        }
    }

    /** Plays card when card is clicker */
    public void playerCardClicked(MouseEvent event){
        for (int i = game.getPlayerHand().size() - 1; i >= 0; i--) {
            Card card = game.getPlayerHand().get(i);
            Rectangle bounds = panel.getMapCards().get(card);
            if (bounds.contains(event.getPoint())) {
                if (card.getFace() == Card.Face.EIGHT){
                    pickSuit();
                }
                if(selected || card.getFace() != Card.Face.EIGHT){
                    game.setClickedCard(card);
                }
                break;
            }
        }
    }

    /** Draws card when the deck is clicked */
    public void deckClicked(MouseEvent event){
        Rectangle deckBounds;
        deckBounds = panel.getDrawBounds().get(panel.getLastCard());
        if (deckBounds.contains(event.getPoint())){
            game.setClickedCard(game.getFaceDown().peekTopCard());
            System.out.println("You drew a card.");
        }
    }

    /** Creates the option menu when you play an 8 */
    public void pickSuit(){
        Object[] possibilities = {"hearts", "diamonds", "spades", "clubs"};
        try{
            String suit = (String) JOptionPane.showInputDialog(null, "Choose a suit:\n",
                    "Customized Dialog", JOptionPane.PLAIN_MESSAGE, null, possibilities, "hearts");
            switch(suit){
                case "hearts": game.setClickedSuit(Card.Suit.HEARTS); selected = true; break;
                case "diamonds":game.setClickedSuit(Card.Suit.DIAMONDS); selected = true; break;
                case "spades": game.setClickedSuit(Card.Suit.SPADES); selected = true; break;
                case "clubs": game.setClickedSuit(Card.Suit.CLUBS); selected = true; break;
                default: selected = false;
            }
        }
        catch (NullPointerException e){
            System.out.println("Please choose a suit or play another card");
        }
    }
}
