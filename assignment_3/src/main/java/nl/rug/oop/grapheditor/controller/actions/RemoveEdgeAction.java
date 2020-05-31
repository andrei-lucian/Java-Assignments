package nl.rug.oop.grapheditor.controller.actions;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/** Remove an edge */
public class RemoveEdgeAction extends AbstractAction implements Observer {

    private final GraphModel graph;

    public RemoveEdgeAction(GraphModel graph) {
        super("Remove edge");
        this.graph = graph;
        graph.addObserver(this);
        fixEnabled();
    }

    /** Only show if an edge has been selected */
    private void fixEnabled(){
        setEnabled(graph.getSelectedEdge()!= null);
    }

    /** Remove a graph's selected edge */
    @Override
    public void actionPerformed(ActionEvent e) {
        graph.removeEdge(graph.getSelectedEdge());
        graph.setSelectedEdge(null);
    }

    @Override
    public void update(Observable o, Object arg) {
        fixEnabled();
    }
}
