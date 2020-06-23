package nl.rug.oop.grapheditor.controller.actions;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

/** Undoes an action */
public class UndoAction extends AbstractAction implements Observer {

    private final GraphModel graph;

    public UndoAction(GraphModel graph) {
        super("Undo");
        this.graph = graph;
        graph.addObserver(this);
        fixEnabled();
        this.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_Z);
    }

    /** Only show if a node has been selected */
    private void fixEnabled(){
        setEnabled(graph.getUndoManager().canUndo());
    }

    /** Undo an action */
    @Override
    public void actionPerformed(ActionEvent e) {
        graph.getUndoManager().undo();
    }

    @Override
    public void update(Observable o, Object arg) {
        fixEnabled();
    }
}
