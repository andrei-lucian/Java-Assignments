package nl.rug.oop.grapheditor.view.buttons;

import nl.rug.oop.grapheditor.controller.actions.RedoAction;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;

/**
 * Creates button for redoing actions
 */
public class RedoButton extends JButton {

    public RedoButton(GraphModel graph){
        super(new RedoAction(graph));
    }


}
