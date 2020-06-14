package nl.rug.oop.grapheditor.controller.actions;

import nl.rug.oop.grapheditor.controller.undoRedo.EditName;
import nl.rug.oop.grapheditor.controller.undoRedo.RemoveEdge;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/** Edit name of node */
public class EditNameAction extends AbstractAction implements Observer {

    private GraphModel graph;
    public EditNameAction(GraphModel graph){
        super("Edit node name");
        this.graph = graph;
        graph.addObserver(this);
        fixEnabled();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EditName editName = new EditName(graph);
        graph.getUndoManager().addEdit(editName);
        editName.redo();
    }

    /** Only enable button if there is a node selected */
    private void fixEnabled() {
        setEnabled(graph.getSelectedNode()!= null);
    }

    @Override
    public void update(Observable o, Object arg) {
        fixEnabled();
    }
}
