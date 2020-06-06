package nl.rug.oop.grapheditor.controller.actions;
import nl.rug.oop.grapheditor.controller.undoRedo.RemoveNode;
import nl.rug.oop.grapheditor.model.GraphModel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/** Remove a node */
public class RemoveNodeAction extends AbstractAction implements Observer {

    private final GraphModel graph;

    public RemoveNodeAction(GraphModel graph) {
        super("Remove node");
        this.graph = graph;
        graph.addObserver(this);
        fixEnabled();
    }

    /** Only show if a node has been selected */
    private void fixEnabled(){
        setEnabled(graph.getSelectedNode() != null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RemoveNode removeNode = new RemoveNode(graph);
        graph.getUndoManager().addEdit(removeNode);
        removeNode.redo();
    }

    @Override
    public void update(Observable o, Object arg) {
        fixEnabled();
    }
}
