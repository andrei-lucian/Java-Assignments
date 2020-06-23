package nl.rug.oop.grapheditor.view;

import nl.rug.oop.grapheditor.io.Save;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Menu bar that holds the save/load and start new graph buttons
 */

public class TopMenuBar extends JMenuBar implements ActionListener {

    private final GraphModel graph;
    private final JMenuItem saveButton;
    private final JMenuItem newGraphButton;
    private final JMenuItem loadFromGraphButton;

    public TopMenuBar(GraphModel graph){
        this.graph = graph;
        JMenu fileMenu = new JMenu("File");
        this.add(fileMenu);
        saveButton = new JMenuItem("Save graph");
        newGraphButton = new JMenuItem("New empty graph");
        loadFromGraphButton = new JMenuItem("Open graph from file");
        fileMenu.add(saveButton);
        fileMenu.add(newGraphButton);
        fileMenu.add(loadFromGraphButton);
        saveButton.addActionListener(this);
        newGraphButton.addActionListener(this);
        loadFromGraphButton.addActionListener(this);
        this.setBorder(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            Save.save(graph);
        }
        if (e.getSource() == newGraphButton) {
            switch(SaveAndLoad.saveCheck()){
                case JOptionPane.YES_OPTION: Save.save(graph); SaveAndLoad.createNewGraph(graph); break;
                case JOptionPane.NO_OPTION: SaveAndLoad.createNewGraph(graph); break;
                case JOptionPane.CANCEL_OPTION: break;
            }
        }
        if (e.getSource() == loadFromGraphButton) {
            if(SaveAndLoad.chooseFile()){
                File loadPath = SaveAndLoad.getFilePath();
                System.out.println(loadPath);
                try {
                    SaveAndLoad.loadGraph(loadPath, graph);
                } catch (FileNotFoundException fileNotFoundException) {
                    System.out.println("File not found");
                }
            }
        }
    }

}
