package nl.rug.oop.cardgame.view;

import javax.swing.*;

/** Menu that pops up when the user selects an
 * 8 card, making them choose a suit  */

public class SelectSuitMenu extends JPopupMenu {

    JMenuItem hearts = new JMenuItem("Hearts");
    JMenuItem diamonds = new JMenuItem("Diamonds");
    JMenuItem spades = new JMenuItem("Spades");
    JMenuItem clubs = new JMenuItem("Clubs");

    public SelectSuitMenu(){
        this.add(hearts);
        this.add(diamonds);
        this.add(spades);
        this.add(clubs);
    }
}
