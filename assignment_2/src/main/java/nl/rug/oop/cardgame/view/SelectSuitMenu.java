package nl.rug.oop.cardgame.view;

import nl.rug.oop.cardgame.model.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        hearts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                //game.setClickedSuit(Card.Suit suit);
            }
        });

        diamonds.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

            }
        });

        spades.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

            }
        });

        clubs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

            }
        });
    }
}
