package nl.rug.oop.grapheditor.controller.undoRedo;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.Node;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

/** Implements moving a node in the graph and the undo and redo of said action*/
public class MoveNode extends AbstractUndoableEdit {

    private final GraphModel graph;
    private Node movedNode;
    private int movedNodeStartX;
    private int movedNoteStartY;
    private int movedNodeEndX;
    private int movedNodeEndY;

    public MoveNode(GraphModel graph){
        this.graph = graph;
    }

    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        movedNodeEndX = movedNode.getNodeBounds().x;
        movedNodeEndY = movedNode.getNodeBounds().y;
        movedNode.setNewLocation(movedNodeStartX, movedNoteStartY);
    }

    @Override
    public void redo() throws CannotRedoException {
        if (!canRedo()){
            this.movedNode = graph.getSelectedNode();
            this.movedNodeStartX = graph.getMovedNodeStartX();
            this.movedNoteStartY = graph.getMovedNodeStartY();
        }
        else {
            super.redo();
            movedNode.setNewLocation(movedNodeEndX, movedNodeEndY);
        }
    }
}
