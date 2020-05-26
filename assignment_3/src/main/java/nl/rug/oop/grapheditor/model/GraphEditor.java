package nl.rug.oop.grapheditor.model;

import nl.rug.oop.grapheditor.io.SaveAndLoad;

public class GraphEditor {

    public static void main(String[] args) {
        GraphModel graph = new GraphModel();

        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);

        Edge edge1 = new Edge();
        Edge edge2 = new Edge();
        Edge edge3 = new Edge();

        graph.addEdge(edge1, node1, node2);
        graph.addEdge(edge2, node2, node3);
        graph.addEdge(edge3, node3, node1);

        SaveAndLoad.save(graph);

    }

}