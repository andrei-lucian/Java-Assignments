package nl.rug.oop.grapheditor.model;

/** Represents an edge - always connects 2 nodes */
public class Edge {

    public void setNode1(int node1) {
        this.node1 = node1;
    }

    private int node1;

    public void setNode2(int node2) {
        this.node2 = node2;
    }

    private int node2;

    public void setNodes(int node1, int node2) {
        this.node1 = node1;
        this.node2 = node2;
    }

    public int getNode1() {
        return node1;
    }

    public int getNode2() {
        return node2;
    }

}
