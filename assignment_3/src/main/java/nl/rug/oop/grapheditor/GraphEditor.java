package nl.rug.oop.grapheditor;

import nl.rug.oop.grapheditor.model.Edge;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.Node;
import nl.rug.oop.grapheditor.view.GraphFrame;

public class GraphEditor {

    public static void main(String[] args) {
        GraphModel graph = new GraphModel();

       /* Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        Node node4 = new Node();
        node1.setBounds(50,50);
        node2.setBounds(300, 300);
        node3.setBounds(450,100);
        node4.setBounds(600,600);

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);

        Edge edge1 = new Edge();
        Edge edge2 = new Edge();
        Edge edge3 = new Edge();
        Edge edge4 = new Edge();

        graph.addEdge(edge1, node1, node2);
        graph.addEdge(edge2, node2, node3);
        graph.addEdge(edge3, node3, node1);
        graph.addEdge(edge4, node3, node4);*/

        new GraphFrame(graph);

        /*
        Save.save(graph);
        String loadPath = Load.chooseFile();
        GraphModel graph = new GraphModel(loadPath);
        graph.printEdges();
        */

    }
}