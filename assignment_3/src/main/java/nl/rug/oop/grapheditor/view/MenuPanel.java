package nl.rug.oop.grapheditor.view;

import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class MenuPanel extends JPanel implements Observer {

    public MenuPanel(GraphModel graph){
        this.setLayout(new BorderLayout());
        add(new TopMenuBar(graph), BorderLayout.NORTH);
        add(new MenuBar(graph), BorderLayout.SOUTH);
        setVisible(true);
        setOpaque(true);
        graph.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
