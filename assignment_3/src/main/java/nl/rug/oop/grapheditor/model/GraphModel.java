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
        this.edgeList.removeIf(edge -> edge.getNode1() == this.nodeList.indexOf(node) ||
                edge.getNode2() == this.nodeList.indexOf(node));
        nodeList.remove(node);
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
        setChanged();
        notifyObservers();
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
