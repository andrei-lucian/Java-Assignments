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
        super.undo();
        graph.removeEdge(addedEdge);
    }

    @Override
    public void redo() throws CannotRedoException {
        if (!canRedo()){
            addedEdge = graph.getAddedEdge();
        }
        else {
            super.redo();
            graph.addEdge(addedEdge);
        }
    }
}
