package nl.rug.oop.grapheditor.view.buttons;

import nl.rug.oop.grapheditor.controller.actions.EditNameAction;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;

/**
 * Creates button for editing name of node
 */
public class EditNameButton extends JButton {

    public EditNameButton(GraphModel graph){
        super(new EditNameAction(graph));
    }

}
