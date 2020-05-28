package nl.rug.oop.grapheditor.view;

import javax.swing.*;

/** Menu bar that contains buttons for adding/removing nodes/edges */
public class MenuBar extends JMenuBar {

    JButton addNode = new JButton("Add node");
    JButton removeNode = new JButton("Remove node");
    JButton addEdge = new JButton("Add Edge");
    JButton removeEdge = new JButton("Remove edge");

    public MenuBar(){
        this.add(addNode);
        this.add(removeNode);
        this.add(addEdge);
        this.add(removeEdge);
    }
}
