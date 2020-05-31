package nl.rug.oop.grapheditor.controller;

import nl.rug.oop.grapheditor.model.Edge;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.view.GraphPanel;
import nl.rug.oop.grapheditor.model.Node;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectionController extends MouseAdapter {

    private final GraphModel graph;
    private Node draggedNode;
    private int startX;
    private int startY;

    public SelectionController(GraphModel graph, GraphPanel panel) {
        this.graph = graph;
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        draggedNode = null;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        boolean nodeSelected = false;
        for (Node node : graph.getNodeList()) {
            Rectangle bounds = node.getNodeBounds();
            if (bounds.contains(event.getPoint())) {
                graph.setSelectedNode(node);
                nodeSelected = true;
                break;
            }
        }
        if (!nodeSelected){
            graph.setSelectedNode(null);
        }
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        for (Node node : graph.getNodeList()) {
            Rectangle bounds = node.getNodeBounds();
            if (bounds.contains(event.getPoint())) {
                //System.out.println("Node clicked");
                draggedNode = node;
                startX = event.getX() - node.getNodeBounds().x;
                startY = event.getY() - node.getNodeBounds().y;
            }
        }
        if (draggedNode!=null) {
            draggedNode.setNewLocation(event.getX() - startX, event.getY() - startY);
            //System.out.println("Node dragged");
        }
    }
}
