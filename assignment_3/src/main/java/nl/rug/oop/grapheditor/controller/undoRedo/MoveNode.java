package nl.rug.oop.grapheditor.controller.undoRedo;

import nl.rug.oop.grapheditor.model.Edge;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.Node;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public class MoveNode extends AbstractUndoableEdit {

    private GraphModel graph;
    private Node movedNode;
    private int movedNodeStartX;
    private int movedNoteStartY;

    public MoveNode(GraphModel graph){
        this.graph = graph;
    }

    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        movedNode.setNewLocation(movedNodeStartX, movedNoteStartY);
    }

    @Override
    public void redo() throws CannotRedoException {
        this.movedNode = graph.getMovedNode();
        this.movedNodeStartX = graph.getMovedNodeStartX();
        this.movedNoteStartY = graph.getMovedNodeStartY();
    }
}
