package nl.rug.oop.grapheditor.model;
import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class GraphPanel extends JPanel implements Observer {

    private GraphModel model;
    private static final Color BACKGROUND_COLOR = new Color(0, 0, 0, 50);

    public GraphPanel(GraphModel model){
        this.model = model;
        setBackground(BACKGROUND_COLOR);
        setVisible(true);
        setOpaque(true);
        //addMouseListener(new Clicker(this.game, this));
        model.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
