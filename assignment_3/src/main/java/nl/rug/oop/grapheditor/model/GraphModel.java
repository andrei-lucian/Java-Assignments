package nl.rug.oop.grapheditor.model;
import nl.rug.oop.grapheditor.controller.undoRedo.AddEdge;
import nl.rug.oop.grapheditor.view.SaveAndLoad;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/** Keeps track of all the nodes and edges */
public class GraphModel extends Observable implements Observer {

    private ArrayList<Edge> edgeList;
    private ArrayList<Node> nodeList;
    private Node selectedNode;
    private Edge selectedEdge;
    private Edge addedEdge;
    private Node secondNode = null;
    private int mouseX;
    private int mouseY;
    private boolean currentlyAddingEdge;
    private UndoManager undoManager;
    private int movedNodeStartX;
    private int movedNodeStartY;

    /** Constructor 1 called when no file name is given */
    public GraphModel(){
        this.nodeList = new ArrayList<>();
        this.edgeList = new ArrayList<>();
        this.undoManager = new UndoManager();
    }

    /** Constructor 2 called when file name is given in terminal */
    public GraphModel(File filepath){
        this.nodeList = new ArrayList<>();
        this.edgeList = new ArrayList<>();
        this.undoManager = new UndoManager();
        try {
            SaveAndLoad.loadGraph(filepath, this);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            JOptionPane.showMessageDialog(null, "File not found.");
        }
    }

    /** Add a node to a graph */
    public void addNode(Node node){
        nodeList.add(node);
        node.addObserver(this);
        setChanged();
        notifyObservers();
    }

    /** Remove a node from a graph */
    public void removeNode(Node node){
        for (Edge edge : node.getEdges()){
            this.edgeList.remove(edge);
        }
        this.nodeList.remove(node);
    }

    /** Add an edge to a graph */
    public void addEdge(Edge edge, Node node1, Node node2){
        if (node1!= null && node2!= null){
            edgeList.add(edge);
            edge.setNodes(node1, node2);
            node1.addEdge(edge);
            node2.addEdge(edge);
            setChanged();
            notifyObservers();
        }
    }

    /** Add an edge to a graph without knowing which nodes it's connected to yet */
    public void addEdge(Edge edge){
        edgeList.add(edge);
        setChanged();
        notifyObservers();
    }

    /** Remove an edge from a graph */
    public void removeEdge(Edge edge){
        edgeList.remove(edge);
        Node node1 = edge.getNode1();
        Node node2 = edge.getNode2();
        node1.getEdges().remove(edge);
        node2.getEdges().remove(edge);
        setChanged();
        notifyObservers();
    }

    public UndoManager getUndoManager() {
        return undoManager;
    }

    /** Update function used in SelectionController so that
     * the state of the graph is updated once the mouse is released */
    public void update(){
        this.setChanged();
        this.notifyObservers();
    }

    /** Set the second selected node if connecting 2 nodes */
    public void setSecondNode(Node secondNode) {
        this.secondNode = secondNode;
        connectEdge();
        AddEdge addEdge = new AddEdge(this);
        addEdge.redo();
        undoManager.addEdit(addEdge);

    }

    public Edge getAddedEdge() {
        return addedEdge;
    }

    /** Connect 2 nodes together if 'add edge' button is clicked */
    public void connectEdge(){
        if (secondNode!=null){
            addedEdge = new Edge();
            addEdge(addedEdge, selectedNode, secondNode);
        }
        setChanged();
        notifyObservers();
    }

    /** Get the X position of the mouse
     * - used in GraphPanel for drawing a line from a
     * node to the cursor when adding an edge */
    public int getMouseX() {
        return mouseX;
    }

    /** Same as getMouseX but for y coordinate */
    public int getMouseY() {
        return mouseY;
    }

    /** Set the X position of the mouse
     * - used in SelectionController for drawing a line from
     * a node to the cursor when adding an edge */
    public void setMouseCoordinates(int mouseX, int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        setChanged();
        notifyObservers();
    }

    /** @return the state of the program - if the
     *  AddEdgeAction button has been clicked */
    public boolean isCurrentlyAddingEdge() {
        return currentlyAddingEdge;
    }

    /** Used in AddEdgeAction to set the state of the program so
     * that SelectionController knows which actions to carry out. */
    public void setCurrentlyAddingEdge(boolean currentlyAddingEdge) {
        this.currentlyAddingEdge = currentlyAddingEdge;
        setChanged();
        notifyObservers();
    }

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

    public ArrayList<Node> getNodeList() {
        return nodeList;
    }

    public ArrayList<Edge> getEdgeList() {
        return edgeList;
    }

    public int getMovedNodeStartX() {
        return movedNodeStartX;
    }

    public void setMovedNodeStartX(int movedNodeStartX) {
        this.movedNodeStartX = movedNodeStartX;
    }

    public int getMovedNodeStartY() {
        return movedNodeStartY;
    }

    public void setMovedNodeStartY(int movedNodeStartY) {
        this.movedNodeStartY = movedNodeStartY;
    }

    public void setEdgeList(ArrayList<Edge> edgeList) {
        this.edgeList = edgeList;
        setChanged();
        notifyObservers();
    }

    public void setNodeList(ArrayList<Node> nodeList) {
        this.nodeList = nodeList;
        setChanged();
        notifyObservers();
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }

}