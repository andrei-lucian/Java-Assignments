package nl.rug.oop.grapheditor.controller.actions;

import nl.rug.oop.grapheditor.model.Edge;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class RemoveEdgeAction extends AbstractAction implements Observer {

    private final GraphModel graph;

    public RemoveEdgeAction(GraphModel graph) {
        super("Remove an edge");
        this.graph = graph;
        graph.addObserver(this);
        fixEnabled();
    }

    private void fixEnabled(){
        setEnabled(graph.getSelectedEdge()!= null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        graph.getEdgeList().remove(graph.getSelectedEdge());
        graph.setSelectedEdge(null);
    }

    @Override
    public void update(Observable o, Object arg) {
        fixEnabled();
    }
}
