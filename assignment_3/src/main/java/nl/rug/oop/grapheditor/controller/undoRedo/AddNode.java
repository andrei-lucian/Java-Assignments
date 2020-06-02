package nl.rug.oop.grapheditor.controller.undoRedo;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.Node;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public class AddNode extends AbstractUndoableEdit {

    private GraphModel graph;
    private Node addedNode;

    public AddNode(GraphModel graph){
        this.graph = graph;
    }

    @Override
    public void undo() throws CannotUndoException {
        graph.removeNode(addedNode);
        graph.setSelectedNode(null);
        super.undo();
    }

    @Override
    public void redo() throws CannotRedoException {
        addedNode = new Node();
        graph.addNode(addedNode);
        //graph.getUndoManager().addEdit(this);
    }
}