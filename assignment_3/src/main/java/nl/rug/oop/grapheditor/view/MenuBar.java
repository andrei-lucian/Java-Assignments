package nl.rug.oop.grapheditor.view;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.view.buttons.AddEdgeButton;
import nl.rug.oop.grapheditor.view.buttons.AddNodeButton;
import nl.rug.oop.grapheditor.view.buttons.RemoveEdgeButton;
import nl.rug.oop.grapheditor.view.buttons.RemoveNodeButton;

import javax.swing.*;

/** Menu bar that contains buttons for adding/removing nodes/edges */
public class MenuBar extends JMenuBar {

    public MenuBar(GraphModel graph){
        this.add(new AddNodeButton(graph));
        this.add(new RemoveNodeButton(graph));
        this.add(new AddEdgeButton(graph));
        this.add(new RemoveEdgeButton(graph));
    }
}
