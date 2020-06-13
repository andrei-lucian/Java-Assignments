package nl.rug.oop.grapheditor.view.buttons;

import nl.rug.oop.grapheditor.controller.actions.RedoAction;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;

public class EditNameButton extends JButton {

    public EditNameButton(GraphModel graph){
        super(new EditNameAction(graph));
    }

}
