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
        this.selectedNode1 = graph.getSelectedNode();
        this.selectedNode2 = graph.getPreviouslySelectedNode();
        fixEnabled();
    }

    private void fixEnabled(){
        setEnabled(graph.getSelectedNode() != null && graph.getPreviouslySelectedNode() != null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("hi");
        graph.addEdge(new Edge(),graph.getPreviouslySelectedNode(), graph.getSelectedNode());
    }

    @Override
    public void update(Observable o, Object arg) {
        fixEnabled();
    }
}
