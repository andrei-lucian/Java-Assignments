package nl.rug.oop.grapheditor.model;
import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class GraphPanel extends JPanel implements Observer {

    private GraphModel model;
    private static final Color BACKGROUND_COLOR = new Color(205, 115, 10, 255);

    public GraphPanel(GraphModel model){
        add(new MenuBar());
        this.model = model;
        setBackground(BACKGROUND_COLOR);
        setVisible(true);
        setOpaque(true);
        //addMouseListener(new Clicker(this.game, this));
        model.addObserver(this);
    }

    private void paintNodes(Graphics g){
        for (Node node : model.getNodeList()){
            Rectangle bounds = node.getNodeBounds();
            g.fillRect(bounds.x + node.getRelativeX(),
                    bounds.y + node.getRelativeY(), bounds.width, bounds.height);
        }
    }

    private void paintEdges(Graphics g){
        for (Edge edge : model.getEdgeList()){
            Node n1 = model.getNodeList().get(edge.getNode1());
            Node n2 = model.getNodeList().get(edge.getNode2());
            Rectangle b1 = n1.getNodeBounds();
            Rectangle b2 = n2.getNodeBounds();
            g.drawLine(b1.x, b1.y, b2.x, b2.y);
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        paintNodes(g);
        paintEdges(g);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
