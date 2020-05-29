package nl.rug.oop.grapheditor.controller.actions;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.Node;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class RemoveNodeAction extends AbstractAction implements Observer {

    private GraphModel graph;

    public RemoveNodeAction(GraphModel graph) {
        super("Remove a node");
        this.graph = graph;
        graph.addObserver(this);
        fixEnabled();
    }

    private void fixEnabled(){
        setEnabled(graph.getSelectedNode() != null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        graph.removeNode(graph.getSelectedNode());
        graph.setSelectedNode(null);
    }

    @Override
    public void update(Observable o, Object arg) {
        fixEnabled();
    }
}
