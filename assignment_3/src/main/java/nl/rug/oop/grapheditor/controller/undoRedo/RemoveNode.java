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
    int index;

    public RemoveNode(GraphModel graph){
        this.graph = graph;
    }

    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        graph.addNode(removedNode);
        /*for (Edge edge : graph.getEdgeList()){
            if (edge.getNode1() >= index){
                edge.setNode1(edge.getNode1()+1);
            }
            if (edge.getNode2() >= index){
                edge.setNode2(edge.getNode2()+1);
            }
        }*/
        for (Edge edge : connectedEdges){
            graph.addEdge(edge, edge.getNode1(), edge.getNode2());
        }
    }

    @Override
    public void redo() throws CannotRedoException {
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
