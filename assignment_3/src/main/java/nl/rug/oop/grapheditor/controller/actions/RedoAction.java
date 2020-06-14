package nl.rug.oop.grapheditor.controller.actions;

import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/** Redoing an action */
public class RedoAction extends AbstractAction implements Observer {

    private GraphModel graph;

    public RedoAction(GraphModel graph) {
        super("Redo");
        this.graph = graph;
        graph.addObserver(this);
        fixEnabled();
    }

    /** Only show if a node has been selected */
    private void fixEnabled(){
        setEnabled(graph.getUndoManager().canRedo());
    }

    /** Redo */
    @Override
    public void actionPerformed(ActionEvent e) {
        graph.getUndoManager().redo();
    }

    @Override
    public void update(Observable o, Object arg) {
        fixEnabled();
    }
}
