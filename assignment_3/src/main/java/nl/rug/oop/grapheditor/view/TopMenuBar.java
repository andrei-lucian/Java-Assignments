package nl.rug.oop.grapheditor.view;

import nl.rug.oop.grapheditor.io.Load;
import nl.rug.oop.grapheditor.io.Save;
import nl.rug.oop.grapheditor.model.Edge;
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
            createNewGraph();
        }

        if (e.getSource() == loadFromGraphButton) {
            loadGraph();
        }
    }

    private void createNewGraph(){
        graph.getUndoManager().discardAllEdits();
        graph.setSelectedNode(null);
        graph.setSelectedEdge(null);
        graph.getNodeList().clear();
        graph.getEdgeList().clear();
    }

    private void loadGraph(){
        if(SaveAndLoad.chooseFile()){
            String loadPath = SaveAndLoad.getFilePath();
            System.out.println(loadPath);
            graph.getUndoManager().discardAllEdits();
            graph.setSelectedEdge(null);
            graph.setSelectedNode(null);
            graph.setNodeList(Load.loadNodes(loadPath));
            for (Node node : graph.getNodeList()){
                node.addObserver(graph);
            }
            graph.setEdgeList(Load.loadEdges(loadPath));
            for (Edge edge: graph.getEdgeList()){
                Node node1 = graph.getNodeList().get(edge.getNode1Index());
                Node node2 = graph.getNodeList().get(edge.getNode2Index());
                edge.setNode1(node1);
                node1.addEdge(edge);
                edge.setNode2(node2);
                node2.addEdge(edge);
            }
        }
    }
}
