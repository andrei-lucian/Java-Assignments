package nl.rug.oop.grapheditor.controller.actions;

import nl.rug.oop.grapheditor.model.Edge;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.Node;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class AddEdgeAction extends AbstractAction implements Observer {
    private final GraphModel graph;

    public AddEdgeAction(GraphModel graph) {
        super("Add an edge");
        this.graph = graph;
        graph.addObserver(this);
        fixEnabled();
    }

    private void fixEnabled(){
        setEnabled(graph.getSelectedNode() != null && graph.getSelectedNode() != null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("hi");
        graph.addEdge(new Edge(),graph.getSelectedNode(), graph.getSelectedNode());
    }

    @Override
    public void update(Observable o, Object arg) {
        fixEnabled();
    }
}
