package nl.rug.oop.grapheditor.controller.actions;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/** Add an edge between two nodes */
public class AddEdgeAction extends AbstractAction implements Observer {

    private final GraphModel graph;

    public AddEdgeAction(GraphModel graph) {
        super("Add an edge");
        this.graph = graph;
        graph.addObserver(this);
        fixEnabled();
    }

    /** Only show if a node has been selected */
    private void fixEnabled(){
        setEnabled(graph.getSelectedNode() != null);
    }

    /** Set the state to currently adding edge -
     * so that SelectionController knows to
     * set the next selected node as GraphModel's secondNode*/
    @Override
    public void actionPerformed(ActionEvent e) {
        graph.setCurrentlyAddingEdge(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        fixEnabled();
    }
}
