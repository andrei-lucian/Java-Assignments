package nl.rug.oop.grapheditor.controller.actions;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.Node;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class RemoveNodeAction extends AbstractAction implements Observer {

    private GraphModel graph;
    private Node selectedNode;

    public RemoveNodeAction(GraphModel graph) {
        super("Remove a node");
        this.graph = graph;
        graph.addObserver(this);
        fixEnabled();
    }

    private void fixEnabled(){
        setEnabled(selectedNode != null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        graph.getNodeList().remove(this.selectedNode);
    }

    @Override
    public void update(Observable o, Object arg) {
        fixEnabled();
    }
}
