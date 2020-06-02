package nl.rug.oop.grapheditor.controller.undoRedo;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public class RemoveEdge extends AbstractUndoableEdit {

    @Override
    public void undo() throws CannotUndoException {
        super.undo();
    }

    @Override
    public void redo() throws CannotRedoException {
        super.redo();
    }
}
