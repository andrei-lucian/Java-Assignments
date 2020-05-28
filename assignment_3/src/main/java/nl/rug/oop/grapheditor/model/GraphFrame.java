package nl.rug.oop.grapheditor.model;

import javax.swing.*;
import java.awt.*;

public class GraphFrame extends JFrame {

    public GraphFrame(GraphModel graph) {
        super("Crazy Eights");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setPreferredSize(new Dimension(1200, 800));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        GraphPanel panel = new GraphPanel(graph);
        add(panel);
        MenuBar menubar = new MenuBar();
        add(menubar);
    }
}
