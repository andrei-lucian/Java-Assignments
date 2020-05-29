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
    private Node selectedNode1;
    private Node selectedNode2;

    public AddEdgeAction(GraphModel graph) {
        super("Add an edge");
        this.graph = graph;
        graph.addObserver(this);
        fixEnabled();
    }

    private void fixEnabled(){
        setEnabled(selectedNode1 != null && selectedNode2 != null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        graph.addEdge(new Edge(),selectedNode1, selectedNode2);
    }

    @Override
    public void update(Observable o, Object arg) {
        fixEnabled();
    }
}
