package nl.rug.oop.grapheditor.controller.undoRedo;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.Node;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public class RemoveNode extends AbstractUndoableEdit {

    private GraphModel graph;

    public RemoveNode(GraphModel graph){
        this.graph = graph;
    }

    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        graph.addNode(new Node());
    }

    @Override
    public void redo() throws CannotRedoException {
        super.redo();
        graph.removeNode(graph.getSelectedNode());
        graph.setSelectedNode(null);
    }
}
