package nl.rug.oop.grapheditor.view;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.view.buttons.*;

import javax.swing.*;
import java.awt.*;

/** Menu bar that contains buttons for adding/removing nodes/edges */
public class MenuBar extends JMenuBar {

    private static final Color BACKGROUND_COLOR = new Color(61, 63, 65, 255);
    public MenuBar(GraphModel graph){
        this.setLayout(new GridBagLayout());
        this.add(new AddNodeButton(graph));
        this.add(new RemoveNodeButton(graph));
        this.add(new AddEdgeButton(graph));
        this.add(new RemoveEdgeButton(graph));
        this.add(new UndoButton(graph));
        this.add(new RedoButton(graph));
        this.setBorder(null);
        setBackground(BACKGROUND_COLOR);
    }

}
