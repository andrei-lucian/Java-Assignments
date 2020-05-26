package nl.rug.oop.grapheditor.model;
import nl.rug.oop.grapheditor.io.SaveAndLoad;

import java.io.IOException;
import java.util.ArrayList;

/** Keeps track of all the nodes and edges */
public class GraphModel {

    public GraphModel(String filename){
        this = graphModel(null, filename);
    }
    ArrayList<Edge> edgeList = new ArrayList<>();
    ArrayList<Node> nodeList = new ArrayList<>();

    public ArrayList<Node> getNodeList() {
        return nodeList;
    }

    public ArrayList<Edge> getEdgeList() {
        return edgeList;
    }

    public void addNode(Node node){
        nodeList.add(node);
    }

    private GraphModel graphModel(GraphModel graphModel, String filename){
        try {
            graphModel = SaveAndLoad.load("fileName");
            System.out.println("Model loaded.");
        } catch (IOException e) {
            System.out.println("Could not load from the file");
        } catch (ClassNotFoundException e) {
            System.out.println("The savefile could not be used to load a player");
        }
        System.out.println("Could not load previous game, starting from scratch instead.");
        return graphModel;
    }

    public void removeNode(Node node){
        nodeList.remove(node);
        for (Edge edge : node.getEdges()){
            edgeList.remove(edge);
        }
    }

    public void addEdge(Edge edge, Node node1, Node node2){
        if (node1!= null && node2!= null){
            edgeList.add(edge);
            edge.setNodes(nodeList.indexOf(node1), nodeList.indexOf(node2));
        }
    }

    public void removeEdge(Edge edge){
        edgeList.remove(edge);
    }
}
