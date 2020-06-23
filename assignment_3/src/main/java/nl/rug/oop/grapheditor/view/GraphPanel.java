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
            paintNodeAndText(node, g, NODE_COLOR);
        }
    }

    /** Highlight selected node */
    private void highlightNode(Graphics g){
        if (graph.getSelectedNode()!=null){
            paintNodeAndText(graph.getSelectedNode(), g, HIGHLIGHT_COLOR);
        }
    }

    /** Method used in paintNodes and highlightNode */
    private void paintNodeAndText(Node node, Graphics g, Color nodeColor){
        Rectangle bounds = node.getNodeBounds();
        g.setColor(nodeColor);
        g.fillRect(bounds.x, bounds.y,  bounds.width, bounds.height);
        g.setColor(GraphPanel.TEXT_COLOR);
        Graphics2D g2d = (Graphics2D) g.create();
        Font font = new Font("Arial", Font.BOLD, 20);
        g2d.setFont(scaleFont(node.getName(), bounds.width, g, font));
        FontMetrics fm = g2d.getFontMetrics();
        int x = (bounds.x + (bounds.width/2 - fm.stringWidth(node.getName()) / 2));
        int y = (bounds.y + bounds.height/2 + fm.getHeight()/4);
        g2d.drawString(node.getName(), x, y);
    }

    public static Font scaleFont(String text, int width, Graphics g, Font pFont)
    {
        float fontSize = pFont.getSize();
        float fWidth = g.getFontMetrics(pFont).stringWidth(text);
        if(fWidth < width)
            return pFont;
        fontSize = ((float)width / fWidth) * fontSize;
        return pFont.deriveFont(fontSize);
    }

    /** Paint all the edges of a graph */
    private void paintEdges(Graphics g){
        for (Edge edge : graph.getEdgeList()){
            Node n1 = edge.getNode1();
            Node n2 = edge.getNode2();
            Rectangle b1 = n1.getNodeBounds();
            Rectangle b2 = n2.getNodeBounds();
            Graphics2D line = (Graphics2D) g;
            line.setStroke(new BasicStroke(2));
            line.setColor(NODE_COLOR);
            Line2D.Float drawnEdge = new Line2D.Float(b1.x + (float)b1.width/2, b1.y + (float)b1.height/2,
                    b2.x + (float)b2.width/2, b2.y + (float)b2.height/2);
            line.draw(drawnEdge);
            edgeMap.put(edge, drawnEdge);
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
            Line2D.Float drawnEdge = new Line2D.Float(nodeBounds.x + (float)nodeBounds.width / 2,
                    nodeBounds.y + (float)nodeBounds.height / 2, graph.getMouseX(), graph.getMouseY());
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
