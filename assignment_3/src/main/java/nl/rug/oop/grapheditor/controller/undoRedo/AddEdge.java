package nl.rug.oop.grapheditor.controller.undoRedo;

import nl.rug.oop.grapheditor.model.Edge;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public class AddEdge extends AbstractUndoableEdit {

    private GraphModel graph;
    private Edge addedEdge;

    public AddEdge(GraphModel graph){
        this.graph = graph;
    }

    @Override
    public void undo() throws CannotUndoException {
        graph.removeEdge(addedEdge);
        super.undo();
    }

    @Override
    public void redo() throws CannotRedoException {
        addedEdge = graph.getAddedEdge();
    }
}
