package nl.rug.oop.grapheditor.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

/** A node in a graph - has a name, and its
 * coordinates and size are represented by a rectangle */
public class Node extends Observable {

    private Rectangle nodeBounds;
    private String name;

    private ArrayList<Edge> edges;

    public Node(){
        this.nodeBounds = new Rectangle(100,100, 100, 50);
        this.name = "New node";
        this.edges = new ArrayList<>();
    }

    public void setBounds(int x, int y){
        this.nodeBounds.x = x;
        this.nodeBounds.y = y;
    }

    public void addEdge(Edge edge){
        edges.add(edge);
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

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
    }

    public void setNodeBounds(Rectangle nodeBounds) {
        this.nodeBounds = nodeBounds;
    }

}
