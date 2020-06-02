package nl.rug.oop.grapheditor.controller.undoRedo;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.Node;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public class AddNode extends AbstractUndoableEdit {

    private GraphModel graph;

    public AddNode(GraphModel graph){
        this.graph = graph;
    }
    @Override
    public void undo() throws CannotUndoException {
        super.undo();
    }

    @Override
    public void redo() throws CannotRedoException {
        super.redo();
        graph.addNode(new Node());
    }
}
