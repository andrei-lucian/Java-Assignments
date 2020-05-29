package nl.rug.oop.grapheditor.view.buttons;

import nl.rug.oop.grapheditor.controller.actions.RemoveNodeAction;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;

public class RemoveNodeButton extends JButton {

    public RemoveNodeButton(GraphModel model) {
        super(new RemoveNodeAction(model));
    }
}
