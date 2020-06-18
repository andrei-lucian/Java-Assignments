package nl.rug.oop.grapheditor.controller.undoRedo;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.Node;

import javax.swing.*;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public class EditName extends AbstractUndoableEdit {

    private final GraphModel graph;
    private Node node;
    private String previousName;
    private String newName;

    public EditName(GraphModel graph){
        this.graph = graph;
    }

    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        node.setName(previousName);
    }

    @Override
    public void redo() throws CannotRedoException {
        if(!canRedo()) {
            this.node = graph.getSelectedNode();
            this.previousName = node.getName();
            newName = JOptionPane.showInputDialog(
                    null,
                    "Input a new name:",
                    node.getName());
        }
        else {
            super.redo();
        }
        node.setName(newName);
    }
}
