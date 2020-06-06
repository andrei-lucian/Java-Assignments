package nl.rug.oop.grapheditor.view;
import nl.rug.oop.grapheditor.model.Edge;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/** JPanel that draws all the components of a graph */
public class GraphPanel extends JPanel implements Observer {

    private final GraphModel graph;
    private static final Color BACKGROUND_COLOR = new Color(61, 63, 65, 255);
    private static final Color NODE_COLOR = new Color(185, 186, 186, 255);
    private static final Color HIGHLIGHT_COLOR = new Color(63, 156, 248, 255);
    private static final Color TEXT_COLOR = Color.BLACK;
    private final HashMap<Edge, Line2D.Float> edgeMap;

    public GraphPanel(GraphModel graph){
        this.graph = graph;
        setBackground(BACKGROUND_COLOR);
        setVisible(true);
        setOpaque(true);
        this.setBorder(null);
        graph.addObserver(this);
        edgeMap = new HashMap<>();
    }

    /** Paint all the nodes of a graph */
    private void paintNodes(Graphics g){
        for (Node node : graph.getNodeList()){
            Rectangle bounds = node.getNodeBounds();
            g.setColor(new Color(185, 186, 186, 255));
            g.fillRect(bounds.x, bounds.y,  bounds.width, bounds.height);
            g.setColor(TEXT_COLOR);
            g.drawString(node.getName(), (bounds.x + bounds.width/4),
                    (bounds.y+bounds.height/2));
        }
    }

    /** Paint all the edges of a graph */
    private void paintEdges(Graphics g){
        for (Edge edge : graph.getEdgeList()){
            Node n1 = graph.getNodeList().get(edge.getNode1());
            Node n2 = graph.getNodeList().get(edge.getNode2());
            Rectangle b1 = n1.getNodeBounds();
            Rectangle b2 = n2.getNodeBounds();
            Graphics2D line = (Graphics2D) g;
            line.setStroke(new BasicStroke(2));
            line.setColor(NODE_COLOR);
            Line2D.Float drawnEdge = new Line2D.Float(b1.x + b1.width/2, b1.y + b1.height/2,
                    b2.x + b2.width/2, b2.y + b2.height/2);
            line.draw(drawnEdge);
            edgeMap.put(edge, drawnEdge);
        }
    }

    /** Highlight selected node */
    private void highlightNode(Graphics g){
        if (graph.getSelectedNode()!=null){
            Node node = graph.getSelectedNode();
            Rectangle bounds = node.getNodeBounds();
            g.setColor(HIGHLIGHT_COLOR);
            g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
            g.setColor(TEXT_COLOR);
            g.drawString(node.getName(), (bounds.x + bounds.width/4),
                    (bounds.y+bounds.height/2));
        }
    }

    /** Highlight selected edge */
    private void highlightEdge(Graphics g){
        if (graph.getSelectedEdge()!=null){
            Edge edge = graph.getSelectedEdge();
            Line2D.Float selectedLine = edgeMap.get(edge);
            Graphics2D line = (Graphics2D) g;
            line.setColor(HIGHLIGHT_COLOR);
            line.draw(selectedLine);
        }
    }

    /** Paint selecting edge from node to cursor */
    public void paintSelectingEdge(Graphics g){
        if (graph.isCurrentlyAddingEdge()) {
            Graphics2D line = (Graphics2D) g;
            Rectangle nodeBounds = graph.getSelectedNode().getNodeBounds();
            line.setStroke(new BasicStroke(2));
            line.setColor(NODE_COLOR);
            Line2D.Float drawnEdge = new Line2D.Float(nodeBounds.x + nodeBounds.width / 2,
                    nodeBounds.y + nodeBounds.height / 2, graph.getMouseX(), graph.getMouseY());
            line.draw(drawnEdge);
        }
    }

    /** Paint all components of the panel */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        paintEdges(g);
        highlightEdge(g);
        paintSelectingEdge(g);
        paintNodes(g);
        highlightNode(g);
    }

    public HashMap<Edge, Line2D.Float> getEdgeMap() {
        return edgeMap;
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
