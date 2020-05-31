package nl.rug.oop.grapheditor.view;
import nl.rug.oop.grapheditor.model.Edge;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class GraphPanel extends JPanel implements Observer {

    private GraphModel model;
    private static final Color BACKGROUND_COLOR = new Color(205, 115, 10, 255);
    private HashMap<Edge, Line2D.Float> edgeMap;

    public GraphPanel(GraphModel model){
        add(new MenuBar(model));
        this.model = model;
        setBackground(BACKGROUND_COLOR);
        setVisible(true);
        setOpaque(true);
        model.addObserver(this);
        edgeMap = new HashMap<>();
    }

    public HashMap<Edge, Line2D.Float> getEdgeMap() {
        return edgeMap;
    }

    private void paintNodes(Graphics g){
        for (Node node : model.getNodeList()){
            Rectangle bounds = node.getNodeBounds();
            g.fillRect(bounds.x, bounds.y,  bounds.width, bounds.height);
            int index = model.getNodeList().indexOf(node);
            String i = Integer.toString(index);
            //g.drawString(i, (bounds.x+bounds.width)/2, (bounds.y+bounds.height)/2);
        }
    }

    private void paintEdges(Graphics g){
        for (Edge edge : model.getEdgeList()){
            Node n1 = model.getNodeList().get(edge.getNode1());
            Node n2 = model.getNodeList().get(edge.getNode2());
            Rectangle b1 = n1.getNodeBounds();
            Rectangle b2 = n2.getNodeBounds();
            Graphics2D line = (Graphics2D) g;
            line.setStroke(new BasicStroke(10));
            Line2D.Float drawnEdge = new Line2D.Float(b1.x + b1.width/2, b1.y + b1.height/2,
                    b2.x + b2.width/2, b2.y + b2.height/2);
            line.draw(drawnEdge);
            edgeMap.put(edge, drawnEdge);
        }
    }

    private void paintSelectedNode(Graphics g){
        if (model.getSelectedNode()!=null){
            Node node = model.getSelectedNode();
            Rectangle bounds = node.getNodeBounds();
            g.setColor(Color.YELLOW);
            g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
    }

    private void paintSelectedEdge(Graphics g){
        if (model.getSelectedEdge()!=null){
            Edge edge = model.getSelectedEdge();
            Line2D.Float selectedLine = edgeMap.get(edge);
            Graphics2D line = (Graphics2D) g;
            line.setColor(Color.YELLOW);
            line.draw(selectedLine);
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        paintNodes(g);
        paintEdges(g);
        paintSelectedNode(g);
        paintSelectedEdge(g);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
