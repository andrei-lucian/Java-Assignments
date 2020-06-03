package nl.rug.oop.grapheditor.view;

import nl.rug.oop.grapheditor.io.Load;
import nl.rug.oop.grapheditor.io.Save;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.Node;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopMenuBar extends JMenuBar implements ActionListener {

    private final GraphModel graph;
    private final JMenuItem saveButton;
    private final JMenuItem newGraphButton;
    private final JMenuItem loadFromGraphButton;

    public TopMenuBar(GraphModel graph){
        this.graph = graph;
        JMenu fileMenu = new JMenu("File");
        this.add(fileMenu);
        saveButton = new JMenuItem("Save graph");
        newGraphButton = new JMenuItem("New empty graph");
        loadFromGraphButton = new JMenuItem("Open graph from file");
        fileMenu.add(saveButton);
        fileMenu.add(newGraphButton);
        fileMenu.add(loadFromGraphButton);
        saveButton.addActionListener(this);
        newGraphButton.addActionListener(this);
        loadFromGraphButton.addActionListener(this);
        this.setBorder(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            Save.save(graph);
        }
        if (e.getSource() == newGraphButton) {
            graph.getUndoManager().discardAllEdits();
            graph.setSelectedNode(null);
            graph.setSelectedEdge(null);
            graph.getNodeList().clear();
            graph.getNodeList().clear();
        }

        if (e.getSource() == loadFromGraphButton) {
            String loadPath = Load.chooseFile();
            System.out.println(loadPath);
            graph.getUndoManager().discardAllEdits();
            graph.setNodeList(Load.loadNodes(loadPath));
            for (Node node : graph.getNodeList()){
                node.addObserver(graph);
            }
            graph.setEdgeList(Load.loadEdges(loadPath));
        }
    }
}
