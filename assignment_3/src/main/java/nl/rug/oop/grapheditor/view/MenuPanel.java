package nl.rug.oop.grapheditor.view;

import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class MenuPanel extends JPanel implements Observer {

    private static final Color BACKGROUND_COLOR = new Color(61, 63, 65, 255);

    public MenuPanel(GraphModel graph){
        this.setLayout(new BorderLayout());
        add(new TopMenuBar(graph), BorderLayout.NORTH);
        add(new MenuBar(graph), BorderLayout.SOUTH);
        this.setPreferredSize(new Dimension(1200, 47));
        this.setMaximumSize(new Dimension(1200, 47));
        this.setMinimumSize(new Dimension(1200, 47));
        setBackground(BACKGROUND_COLOR);
        graph.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}