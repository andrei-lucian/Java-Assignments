package nl.rug.oop.grapheditor.view.buttons;

import nl.rug.oop.grapheditor.controller.actions.AddNodeAction;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Creates button for adding node
 */
public class AddNodeButton extends JButton {
    /**
     * Initialise the properties of this button
     */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_D);
        setToolTipText("Add node");
    }

    public AddNodeButton(GraphModel graph) {
        super(new AddNodeAction(graph));
        setButtonProperties();
    }

}
