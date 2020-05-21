package nl.rug.oop.cardgame.view;

import nl.rug.oop.cardgame.model.Game;
import nl.rug.oop.cardgame.model.elements.Card;

import javax.swing.*;

/** Menu that pops up when the user selects an
 * 8 card, making them choose a suit  */

public class SelectSuitMenu extends JPopupMenu {

    JMenuItem hearts = new JMenuItem("Hearts");
    JMenuItem diamonds = new JMenuItem("Diamonds");
    JMenuItem spades = new JMenuItem("Spades");
    JMenuItem clubs = new JMenuItem("Clubs");

    public SelectSuitMenu(Game game){
        this.add(hearts);
        this.add(diamonds);
        this.add(spades);
        this.add(clubs);

        hearts.addActionListener(e -> game.setClickedSuit(Card.Suit.HEARTS));
        diamonds.addActionListener(e -> game.setClickedSuit(Card.Suit.DIAMONDS));
        spades.addActionListener(e -> game.setClickedSuit(Card.Suit.SPADES));
        clubs.addActionListener(e -> game.setClickedSuit(Card.Suit.CLUBS));
    }
}
