package nl.rug.oop.grapheditor.view;

import javax.swing.*;

public class TopMenuBar extends JMenuBar {

    private JMenu fileMenu;
    private JMenuItem saveButton;
    private JMenuItem newGraphButton;
    private JMenuItem loadGraphButton;

    public TopMenuBar(){
        fileMenu = new JMenu("File");
        this.add(fileMenu);
        saveButton = new JMenuItem("Save graph");
        newGraphButton = new JMenuItem("New empty graph");
        loadGraphButton = new JMenuItem("Open graph from file");
        fileMenu.add(saveButton);
        fileMenu.add(newGraphButton);
        fileMenu.add(loadGraphButton);
    }


}
