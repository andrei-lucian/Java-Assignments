package nl.rug.oop.grapheditor.view.buttons;

import nl.rug.oop.grapheditor.controller.actions.AddEdgeAction;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;

/**
 * Creates button for adding edge
 */
public class AddEdgeButton extends JButton {

    public AddEdgeButton(GraphModel graph){
        super(new AddEdgeAction(graph));
    }
}