package nl.rug.oop.grapheditor.controller.undoRedo;

import nl.rug.oop.grapheditor.model.Edge;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.Node;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public class RemoveEdge extends AbstractUndoableEdit {

    private final GraphModel graph;
    private Edge removedEdge;
    private Node node1;
    private Node node2;

    public RemoveEdge(GraphModel graph){
        this.graph = graph;
    }

    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        graph.addEdge(removedEdge, node1, node2);
        node1.addEdge(removedEdge);
        node2.addEdge(removedEdge);
    }

    @Override
    public void redo() throws CannotRedoException {
        if (!canRedo()) {
            removedEdge = graph.getSelectedEdge();
            graph.removeEdge(graph.getSelectedEdge());
            this.node1 = removedEdge.getNode1();
            this.node2 = removedEdge.getNode2();
            graph.setSelectedEdge(null);
        }
        else {
            super.redo();
            graph.removeEdge(removedEdge);
        }
    }
}
