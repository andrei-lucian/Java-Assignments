package nl.rug.oop.grapheditor.model;
import java.awt.*;
import java.util.Observable;

/** A node in a graph - has a name, and its
 * coordinates and size are represented by a rectangle */
public class Node extends Observable {

    private Rectangle nodeBounds;

    private String name;

    public Node(){
        this.nodeBounds = new Rectangle(100,100, 100, 150);
        this.name = "New node";
    }

    public void setBounds(int x, int y, int width, int height){
        this.nodeBounds.x = x;
        this.nodeBounds.y = y;
        this.nodeBounds.width = width;
        this.nodeBounds.height = height;
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
