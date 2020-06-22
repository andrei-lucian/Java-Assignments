package nl.rug.oop.grapheditor.view.buttons;

import nl.rug.oop.grapheditor.controller.actions.UndoAction;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;

/**
 * Creates button for undoing action
 */
public class UndoButton extends JButton {

    public UndoButton(GraphModel graph){
        super(new UndoAction(graph));
    }
}
