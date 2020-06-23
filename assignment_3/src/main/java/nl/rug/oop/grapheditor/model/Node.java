package nl.rug.oop.grapheditor.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

/** A node in a graph - has a name, and its
 * coordinates and size are represented by a rectangle */
public class Node extends Observable {

    private Rectangle nodeBounds;
    private String name;

    private final ArrayList<Edge> edges;

    /**
     * Initiating a standard node
     */
    public Node(){
        this.nodeBounds = new Rectangle(100,100, 100, 50);
        this.name = "New node";
        this.edges = new ArrayList<>();
    }

    public void addEdge(Edge edge){
        edges.add(edge);
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    /** Used when the node is moved. */
    public void setNewLocation(int x, int y){
        this.nodeBounds.x = x;
        this.nodeBounds.y = y;
        setChanged();
        notifyObservers();
    }

    public String getName() {
        return name;
    }

    public Rectangle getNodeBounds() {
        return nodeBounds;
    }

    public void setName(String name) {
        this.name = name;
        setChanged();
        notifyObservers();
    }

    public void setNodeBounds(Rectangle nodeBounds) {
        this.nodeBounds = nodeBounds;
    }

}
