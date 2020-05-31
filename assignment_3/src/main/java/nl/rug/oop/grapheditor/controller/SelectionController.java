package nl.rug.oop.grapheditor.controller;

import nl.rug.oop.grapheditor.model.Edge;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.view.GraphPanel;
import nl.rug.oop.grapheditor.model.Node;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

public class SelectionController extends MouseAdapter {

    private final GraphModel graph;
    private final GraphPanel panel;
    private Node draggedNode;

    public SelectionController(GraphModel graph, GraphPanel panel) {
        this.graph = graph;
        this.panel = panel;
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        draggedNode = null;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        boolean nodeSelected = false;
        boolean edgeSelected = false;
        boolean secondNodeSelected = false;
        if (!graph.isCurrentlyAddingEdge()) {
            for (Node node : graph.getNodeList()) {
                Rectangle bounds = node.getNodeBounds();
                if (bounds.contains(event.getPoint())) {
                    graph.setSelectedNode(node);
                    nodeSelected = true;
                    break;
                }
            }
            if (!nodeSelected) {
                graph.setSelectedNode(null);
            }

            for (Edge edge : graph.getEdgeList()) {
                Line2D.Float line = panel.getEdgeMap().get(edge);
                if (line.getBounds2D().contains(event.getPoint())) {
                    graph.setSelectedEdge(edge);
                    edgeSelected = true;
                    System.out.println("edge selected");
                }
            }
            if (!edgeSelected) {
                graph.setSelectedEdge(null);
            }
        }

        if (graph.isCurrentlyAddingEdge()){
            for (Node node : graph.getNodeList()) {
                Rectangle bounds = node.getNodeBounds();
                if (bounds.contains(event.getPoint())) {
                    graph.setSecondNode(node);
                    secondNodeSelected = true;
                    break;
                }
            }
            if (!secondNodeSelected){
                graph.setSecondNode(null);
            }
            graph.setCurrentlyAddingEdge(false);
        }
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        int startX = 0;
        int startY = 0;
        for (Node node : graph.getNodeList()) {
            Rectangle bounds = node.getNodeBounds();
            if (bounds.contains(event.getPoint())) {
                draggedNode = node;
                startX = event.getX() - node.getNodeBounds().x;
                startY = event.getY() - node.getNodeBounds().y;
            }
        }
        if (draggedNode!=null) {
            draggedNode.setNewLocation(event.getX() - startX, event.getY() - startY);
        }
    }

    @Override
    public void mouseMoved(MouseEvent event){
        if (graph.isCurrentlyAddingEdge()){
            graph.setMouseCoordinates(event.getX(), event.getY());
        }
    }
}
