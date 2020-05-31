package nl.rug.oop.grapheditor.model;
import nl.rug.oop.grapheditor.io.Load;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/** Keeps track of all the nodes and edges */
public class GraphModel extends Observable implements Observer {

    ArrayList<Edge> edgeList;
    ArrayList<Node> nodeList;
    private Node selectedNode;
    private Edge selectedEdge;

    public Edge getSelectedEdge() {
        return selectedEdge;
    }

    public void setSelectedEdge(Edge selectedEdge) {
        this.selectedEdge = selectedEdge;
        setChanged();
        notifyObservers();
    }

    public Node getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(Node selectedNode) {
        this.selectedNode = selectedNode;
        setChanged();
        notifyObservers();
    }

    public GraphModel(){
        this.nodeList = new ArrayList<>();
        this.edgeList = new ArrayList<>();
    }

    public GraphModel(String filename){
        this.nodeList = Load.loadNodes(filename);
        this.edgeList = Load.loadEdges(filename);
    }

    public ArrayList<Node> getNodeList() {
        return nodeList;
    }

    public ArrayList<Edge> getEdgeList() {
        return edgeList;
    }

    public void addNode(Node node){
        nodeList.add(node);
        node.addObserver(this);
        setChanged();
        notifyObservers();
    }

    public void removeNode(Node node){
        edgeList.removeIf(edge -> edge.getNode1() == this.nodeList.indexOf(node) ||
                edge.getNode2() == this.nodeList.indexOf(node));
        int indexOfNode = nodeList.indexOf(node);
        System.out.println("Index is " + indexOfNode);
        for (Edge edge : edgeList){
            System.out.println(edge);
            if (edge.getNode1() > indexOfNode){
                System.out.println("Node1 " + edge.getNode1());
                edge.setNode1(edge.getNode1()-1);
            }
            if (edge.getNode2() > indexOfNode){
                System.out.println("Node2 " + edge.getNode2());
                edge.setNode2(edge.getNode2()-1);
            }
        }
        nodeList.remove(node);
    }

    public void printEdges(){
        for(Edge edge : edgeList){
            System.out.println(edge);
            System.out.println(edge.getNode1());
            System.out.println(edge.getNode2());
        }
    }

    public void addEdge(Edge edge, Node node1, Node node2){
        if (node1!= null && node2!= null){
            edgeList.add(edge);
            edge.setNodes(nodeList.indexOf(node1), nodeList.indexOf(node2));
            setChanged();
            notifyObservers();
        }
    }

    public void removeEdge(Edge edge){
        edgeList.remove(edge);
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }
}
