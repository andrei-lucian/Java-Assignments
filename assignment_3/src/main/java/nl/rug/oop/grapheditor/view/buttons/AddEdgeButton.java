package nl.rug.oop.grapheditor.view.buttons;

import nl.rug.oop.grapheditor.controller.actions.AddEdgeAction;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;

public class AddEdgeButton extends JButton {

    public AddEdgeButton(GraphModel graph){
        super(new AddEdgeAction(graph));
    }

}
