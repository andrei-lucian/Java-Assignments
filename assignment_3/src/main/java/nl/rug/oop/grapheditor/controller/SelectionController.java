package nl.rug.oop.grapheditor.controller;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.view.GraphPanel;
import nl.rug.oop.grapheditor.model.Node;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectionController extends MouseAdapter {

    private final GraphModel graph;
    private Node selectedNode;
    private int startX;
    private int startY;

    public SelectionController(GraphModel graph, GraphPanel panel) {
        this.graph = graph;
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        selectedNode = null;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        for (Node node : graph.getNodeList()) {
            Rectangle bounds = node.getNodeBounds();
            if (bounds.contains(event.getPoint())) {
                System.out.println("Node clicked");
                selectedNode = node;
                startX = event.getX();
                startY = event.getY();
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        if (selectedNode!=null) {
            selectedNode.setNewLocation(event.getX() - startX, event.getY() - startY);
            System.out.println("Node dragged");
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if (selectedNode!=null) {
            selectedNode.setNewLocation(event.getX() - startX, event.getY() - startY);
            System.out.println("Node released");
        }
        selectedNode = null;
    }
}
