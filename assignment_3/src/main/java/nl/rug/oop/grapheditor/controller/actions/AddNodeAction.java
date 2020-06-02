package nl.rug.oop.grapheditor.controller.actions;

import nl.rug.oop.grapheditor.controller.undoRedo.AddNode;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/** Add a node to a graph */
public class AddNodeAction extends AbstractAction implements Observer {

    private GraphModel graph;

    public AddNodeAction(GraphModel graph){
        super("Add a node");
        graph.addObserver(this);
        this.graph = graph;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AddNode addNode = new AddNode(graph);
        addNode.redo();
        graph.getUndoManager().addEdit(addNode);
        //System.out.println(graph.getUndoManager().getUndoOrRedoPresentationName());
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
