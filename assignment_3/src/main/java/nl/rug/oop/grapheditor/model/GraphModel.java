package nl.rug.oop.grapheditor.model;

import java.util.ArrayList;

/** Keeps track of all the nodes and edges */
public class GraphModel {

    ArrayList<Node> nodeList = new ArrayList<>();
    ArrayList<Edge> edgeList = new ArrayList<>();

    public void addNode(Node node){
        nodeList.add(node);
    }

    public void removeNode(Node node){
        nodeList.remove(node);
        for (Edge edge : node.getEdges()){
            edgeList.remove(edge);
        }
    }

    public void addEdge(Edge edge){
        edgeList.add(edge);
    }

    public void removeEdge(Edge edge){
        edgeList.remove(edge);
    }
}
