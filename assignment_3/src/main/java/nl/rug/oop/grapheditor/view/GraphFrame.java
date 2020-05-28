package nl.rug.oop.grapheditor.view;

import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;
import java.awt.*;

public class GraphFrame extends JFrame {

    public GraphFrame(GraphModel graph) {
        super("Graph Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        GraphPanel panel = new GraphPanel(graph);
        add(panel);
        setPreferredSize(new Dimension(1200, 800));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
