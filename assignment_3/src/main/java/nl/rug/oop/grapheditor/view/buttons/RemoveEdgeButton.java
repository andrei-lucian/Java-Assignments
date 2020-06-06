package nl.rug.oop.grapheditor.view.buttons;

import nl.rug.oop.grapheditor.controller.actions.RemoveEdgeAction;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;

public class RemoveEdgeButton extends JButton {

    public RemoveEdgeButton(GraphModel graph) {
        super(new RemoveEdgeAction(graph));
    }
}
