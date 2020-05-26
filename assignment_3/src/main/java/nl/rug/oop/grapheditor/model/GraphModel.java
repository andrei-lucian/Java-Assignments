package nl.rug.oop.grapheditor.model;
import nl.rug.oop.grapheditor.io.SaveAndLoad;

import java.io.IOException;
import java.util.ArrayList;

/** Keeps track of all the nodes and edges */
public class GraphModel {

    ArrayList<Edge> edgeList;
    ArrayList<Node> nodeList;

    public GraphModel(){
        this.nodeList = new ArrayList<>();
        this.edgeList = new ArrayList<>();
    }

    public GraphModel(String filename){
        this.nodeList = SaveAndLoad.loadNodes(filename);
        this.edgeList = SaveAndLoad.loadEdges(filename);
    }

    public ArrayList<Node> getNodeList() {
        return nodeList;
    }

    public ArrayList<Edge> getEdgeList() {
        return edgeList;
    }

    public void addNode(Node node){
        nodeList.add(node);
    }

//    private GraphModel graphModel(GraphModel graphModel, String filename){
//    }

    public void removeNode(Node node){
        nodeList.remove(node);
        for (Edge edge : node.getEdges()){
            edgeList.remove(edge);
        }
    }

    public void printEdges(){
        for(Edge edge : edgeList){
            System.out.println(edge.getNode1());
        }
        for(Node node : nodeList){
            System.out.println(node.getName());
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
