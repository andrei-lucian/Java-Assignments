package nl.rug.oop.cardgame.view;

import nl.rug.oop.cardgame.model.Game;
import nl.rug.oop.cardgame.view.GameFrame;
import nl.rug.oop.cardgame.model.elements.Card;

import javax.swing.*;

/** Menu that pops up when the user selects an
 * 8 card, making them choose a suit  */

public class SelectSuitMenu{

    SelectSuitMenu(Game game){
        //    JMenuItem hearts = new JMenuItem("Hearts");
        //    JMenuItem diamonds = new JMenuItem("Diamonds");
        //    JMenuItem spades = new JMenuItem("Spades");
        //    JMenuItem clubs = new JMenuItem("Clubs");
        //
        //    public SelectSuitMenu(Game game){
        //        this.add(hearts);
        //        this.add(diamonds);
        //        this.add(spades);
        //        this.add(clubs);
        //
        //        hearts.addActionListener(e -> game.setClickedSuit(Card.Suit.HEARTS));
        //        diamonds.addActionListener(e -> game.setClickedSuit(Card.Suit.DIAMONDS));
        //        spades.addActionListener(e -> game.setClickedSuit(Card.Suit.SPADES));
        //        clubs.addActionListener(e -> game.setClickedSuit(Card.Suit.CLUBS));
        //    }
        GameFrame frame = game.getGameFrame();
        Object[] possibilities = {"hearts", "diamonds", "spades", "clubs"};
        String suit = (String)JOptionPane.showInputDialog(frame, "Choose a suit:\n",
                "Customized Dialog", JOptionPane.PLAIN_MESSAGE, null, possibilities, "hearts");

        switch(suit){
            case "hearts": game.setClickedSuit(Card.Suit.HEARTS);
            case "diamonds":game.setClickedSuit(Card.Suit.DIAMONDS);
            case "spades": game.setClickedSuit(Card.Suit.SPADES);
            case "clubs": game.setClickedSuit(Card.Suit.CLUBS);
        }
    }
}
