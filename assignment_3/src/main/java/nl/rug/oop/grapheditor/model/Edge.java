package nl.rug.oop.grapheditor.model;

/** Represents an edge - always connects 2 nodes */
public class Edge {

    private Node node1;
    private Node node2;

    public int getNode1Index() {
        return node1Index;
    }

    public void setNode1Index(int node1Index) {
        this.node1Index = node1Index;
    }

    public int getNode2Index() {
        return node2Index;
    }

    public void setNode2Index(int node2Index) {
        this.node2Index = node2Index;
    }

    private int node1Index;
    private int node2Index;

    /**
     *
     * @param node1 first node that connects the edge
     * @param node2 second node that connects the edge
     */
    public Edge(Node node1, Node node2){
        this.node1 = node1;
        this.node2 = node2;
    }

    /** Empty constructor used for when the user decides to add an edge
     * When an edge is added it is initiated empty until both nodes are selected*/
    public Edge(){

    }

    public void setNode1(Node node1) {
        this.node1 = node1;
    }

    public void setNode2(Node node2) {
        this.node2 = node2;
    }

    public void setNodes(Node node1, Node node2) {
        this.node1 = node1;
        this.node2 = node2;
    }

    public Node getNode1() {
        return node1;
    }

    public Node getNode2() {
        return node2;
    }
}
