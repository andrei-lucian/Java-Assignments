package nl.rug.oop.grapheditor.view;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.view.buttons.*;

import javax.swing.*;

/** Menu bar that contains buttons for adding/removing nodes/edges */
public class MenuBar extends JMenuBar {

    public MenuBar(GraphModel graph){
        this.add(new AddNodeButton(graph));
        this.add(new RemoveNodeButton(graph));
        this.add(new AddEdgeButton(graph));
        this.add(new RemoveEdgeButton(graph));
        this.add(new UndoButton(graph));
        this.add(new RedoButton(graph));
    }
}
