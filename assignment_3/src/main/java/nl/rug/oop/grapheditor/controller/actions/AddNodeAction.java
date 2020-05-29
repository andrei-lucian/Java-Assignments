package nl.rug.oop.grapheditor.controller.actions;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.Node;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class AddNodeAction extends AbstractAction implements Observer {

    private GraphModel graph;

    public AddNodeAction(GraphModel graph){
        super("Add a node");
        graph.addObserver(this);
        this.graph = graph;
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        graph.addNode(new Node());
    }
}
