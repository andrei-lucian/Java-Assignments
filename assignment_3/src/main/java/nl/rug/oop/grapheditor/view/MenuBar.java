package nl.rug.oop.grapheditor.view;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.view.buttons.AddEdgeButton;
import nl.rug.oop.grapheditor.view.buttons.AddNodeButton;
import nl.rug.oop.grapheditor.view.buttons.RemoveEdgeButton;
import nl.rug.oop.grapheditor.view.buttons.RemoveNodeButton;

import javax.swing.*;

/** Menu bar that contains buttons for adding/removing nodes/edges */
public class MenuBar extends JMenuBar {

    private GraphModel graph;

    AddNodeButton addNode = new AddNodeButton();
    RemoveNodeButton removeNode = new RemoveNodeButton(graph);
    AddEdgeButton addEdge = new AddEdgeButton();
    RemoveEdgeButton removeEdge = new RemoveEdgeButton(graph);

    public MenuBar(GraphModel graph){
        this.graph = graph;
        this.add(addNode);
        this.add(removeNode);
        this.add(addEdge);
        this.add(removeEdge);
    }
}
