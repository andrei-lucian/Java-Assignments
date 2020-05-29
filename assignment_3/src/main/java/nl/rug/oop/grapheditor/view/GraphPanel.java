package nl.rug.oop.grapheditor.view;
import nl.rug.oop.grapheditor.model.Edge;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.Node;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class GraphPanel extends JPanel implements Observer {

    private GraphModel model;
    private static final Color BACKGROUND_COLOR = new Color(205, 115, 10, 255);

    public GraphPanel(GraphModel model){
        add(new MenuBar(model));
        this.model = model;
        setBackground(BACKGROUND_COLOR);
        setVisible(true);
        setOpaque(true);
        model.addObserver(this);
    }

    private void paintNodes(Graphics g){
        for (Node node : model.getNodeList()){
            Rectangle bounds = node.getNodeBounds();
            g.fillRect(bounds.x, bounds.y,  bounds.width, bounds.height);
        }
    }

    private void paintEdges(Graphics g){
        for (Edge edge : model.getEdgeList()){
            Node n1 = model.getNodeList().get(edge.getNode1());
            Node n2 = model.getNodeList().get(edge.getNode2());
            Rectangle b1 = n1.getNodeBounds();
            Rectangle b2 = n2.getNodeBounds();
            g.drawLine(b1.x + b1.width/2, b1.y + b1.height/2, b2.x + b2.width/2, b2.y + b2.height/2);
        }
    }

    private void paintSelectedNode(Graphics g){
        if (model.getSelectedNode()!=null){
            Node node = model.getSelectedNode();
            Rectangle bounds = node.getNodeBounds();
            g.setColor(Color.YELLOW);
            g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);

        }
        if (model.getPreviouslySelectedNode()!=null){

            Node node1 = model.getPreviouslySelectedNode();
            Rectangle bounds1 = node1.getNodeBounds();
            g.setColor(Color.YELLOW);
            g.fillRect(bounds1.x, bounds1.y, bounds1.width, bounds1.height);
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        paintNodes(g);
        paintEdges(g);
        paintSelectedNode(g);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
