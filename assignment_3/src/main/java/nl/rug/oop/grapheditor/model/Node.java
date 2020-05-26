package nl.rug.oop.grapheditor.model;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/** A node in a graph - has a name, and its
 * coordinates and size are represented by a rectangle */
public class Node implements Serializable {

    private static final long serialVersionUID = 2L;

    private Rectangle nodeBounds;

    private String name;
    private ArrayList<Edge> edges = new ArrayList<>();

    public Node(){
        this.nodeBounds = new Rectangle(0,0, 10, 10);
        this.name = "New node";
    }

    public String getName() {
        return name;
    }

    public Rectangle getNodeBounds() {
        return nodeBounds;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void addEdge(Edge edge){
        edges.add(edge);
    }

}
