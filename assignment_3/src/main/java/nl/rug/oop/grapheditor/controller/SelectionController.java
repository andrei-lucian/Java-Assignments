package nl.rug.oop.grapheditor.controller;

import nl.rug.oop.grapheditor.controller.undoRedo.MoveNode;
import nl.rug.oop.grapheditor.model.Edge;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.view.GraphPanel;
import nl.rug.oop.grapheditor.model.Node;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

/** Holds all methods for the mouse controller input */
public class SelectionController extends MouseAdapter {

    private int startX;
    private int startY;
    private final GraphModel graph;
    private final GraphPanel panel;
    private Node draggedNode;
    private boolean nodeSelected;

    public SelectionController(GraphModel graph, GraphPanel panel) {
        this.graph = graph;
        this.panel = panel;
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        draggedNode = null;
    }

    /** Either selects a node, edge, or
     * second node if the mouse is pressed */
    @Override
    public void mousePressed(MouseEvent event) {
        draggedNode = null;
        if (!graph.isCurrentlyAddingEdge()) {
            selectNode(event);
            selectEdge(event);
        }
        if (graph.isCurrentlyAddingEdge()) {
            selectSecondNode(event);
        }
    }

    /** Drags a node */
    @Override
    public void mouseDragged(MouseEvent event) {
        for (Node node : graph.getNodeList()) {
            Rectangle bounds = node.getNodeBounds();
            if (bounds.contains(event.getPoint())) {
                draggedNode = node;
                graph.setSelectedNode(draggedNode);
                startX = event.getX() - draggedNode.getNodeBounds().x;
                startY = event.getY() - draggedNode.getNodeBounds().y;
            }
        }
        if (draggedNode!=null) {
            draggedNode.setNewLocation(event.getX() - startX, event.getY() - startY);
        }
    }

    /** If AddEdgeAction button has been clicked,
     * send mouse X and Y coordinates to GraphModel
     * so it can update GraphPanel and draw a line
     * from the selected node to the cursor */
    @Override
    public void mouseMoved(MouseEvent event){
        if (graph.isCurrentlyAddingEdge()){
            graph.setMouseCoordinates(event.getX(), event.getY());
        }
    }

    /** Select the node that has been clicked on
     * and store the starting coordinates of the node
     * in case the user decides to move it */
    private void selectNode(MouseEvent event){
        nodeSelected = false;
        for (Node node : graph.getNodeList()) {
            Rectangle bounds = node.getNodeBounds();
            if (bounds.contains(event.getPoint())) {
                graph.setSelectedNode(node);
                graph.setMovedNodeStartX(node.getNodeBounds().x);
                graph.setMovedNodeStartY(node.getNodeBounds().y);
                nodeSelected = true;
                break;
            }
        }
        if (!nodeSelected) {
            graph.setSelectedNode(null);
        }
    }

    /** Moves a when the mouse is Released*/
    @Override
    public void mouseReleased(MouseEvent event){
        if (draggedNode != null) {
            if (draggedNode.getNodeBounds().x != graph.getMovedNodeStartX() ||
                    draggedNode.getNodeBounds().y != graph.getMovedNodeStartY()) {
                MoveNode moveNode = new MoveNode(graph);
                moveNode.redo();
                graph.getUndoManager().addEdit(moveNode);
                graph.update();
            }
        }
    }

    /** Select the edge that has been clicked on */
    private void selectEdge(MouseEvent event){
        boolean edgeSelected = false;
        for (Edge edge : graph.getEdgeList()) {
            Line2D.Float line = panel.getEdgeMap().get(edge);
            if (line.getBounds2D().contains(event.getPoint())) {
                graph.setSelectedEdge(edge);
                if(!nodeSelected){
                    edgeSelected = true;
                }
            }
        }
        if (!edgeSelected) {
            graph.setSelectedEdge(null);
        }
    }

    /** Select the second node that has been clicked on */
    private void selectSecondNode(MouseEvent event){
        boolean secondNodeSelected = false;
        for (Node node : graph.getNodeList()) {
            Rectangle bounds = node.getNodeBounds();
            if (bounds.contains(event.getPoint())) {
                graph.setSecondNode(node);
                secondNodeSelected = true;
                break;
            }
        }
        if (!secondNodeSelected) {
            graph.setSecondNode(null);
        }
        graph.setCurrentlyAddingEdge(false);
    }
}
