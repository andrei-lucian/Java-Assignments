package nl.rug.oop.grapheditor.controller.undoRedo;

import nl.rug.oop.grapheditor.model.Edge;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.Node;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import java.util.ArrayList;

public class RemoveNode extends AbstractUndoableEdit {

    private GraphModel graph;
    private Node removedNode;
    private ArrayList<Edge> connectedEdges = new ArrayList<>();

    public RemoveNode(GraphModel graph){
        this.graph = graph;
    }

    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        graph.addNode(removedNode);
        System.out.println("remove node undo");
        for (Edge edge : connectedEdges){
            graph.addEdge(edge, edge.getNode1(), edge.getNode2());
        }
    }

    @Override
    public void redo() throws CannotRedoException {
        System.out.println("remove node redo");
        if (!canRedo()) {
            removedNode = graph.getSelectedNode();
            for (Edge edge : removedNode.getEdges()) {
                connectedEdges.add(edge);
            }
            graph.removeNode(graph.getSelectedNode());
        }
        else {
            super.redo();
            for (Edge edge : removedNode.getEdges()) {
                connectedEdges.add(edge);
            }
            graph.removeNode(removedNode);
        }
        graph.setSelectedNode(null);
    }
}
