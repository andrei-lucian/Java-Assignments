package nl.rug.oop.grapheditor.controller.undoRedo;

import nl.rug.oop.grapheditor.model.Edge;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public class RemoveEdge extends AbstractUndoableEdit {

    private GraphModel graph;
    private Edge removedEdge;

    public RemoveEdge(GraphModel graph){
        this.graph = graph;
    }

    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        System.out.println("remove edge undo");
        graph.addEdge(removedEdge);
    }

    @Override
    public void redo() throws CannotRedoException {
        System.out.println("remove edge redo");
        if(canRedo()){
            super.redo();
        }
        removedEdge = graph.getSelectedEdge();
        graph.removeEdge(graph.getSelectedEdge());
        graph.setSelectedEdge(null);
    }
}
