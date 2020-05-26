package nl.rug.oop.grapheditor.model;
import java.util.ArrayList;

/** Keeps track of all the nodes and edges */
public class GraphModel {

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
