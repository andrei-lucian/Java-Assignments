package nl.rug.oop.grapheditor.io;

import nl.rug.oop.grapheditor.model.Edge;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.Node;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Save {

    static JFrame parentFrame = new JFrame();
    /** Saves the state of a graph to a specified file  */

    private static File chooseSaveLocation(){
        File fileToSave = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify directory and save name");
        int userSelection = fileChooser.showSaveDialog(parentFrame);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            fileToSave = fileChooser.getSelectedFile();
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
        }
        return fileToSave;
    }

    public static void save(GraphModel graph){
        File fileToSave = chooseSaveLocation();
        try {
            FileWriter myWriter = new FileWriter(fileToSave + ".graph");
            myWriter.write(graph.getNodeList().size() + " " + graph.getEdgeList().size() + "\n");
            for (Node node : graph.getNodeList()){
                Rectangle bounds = node.getNodeBounds();
                myWriter.write(bounds.x + " " + bounds.y + " " +
                        bounds.width + " " + bounds.height + " " + node.getName() + "\n");
            }
            for (Edge edge : graph.getEdgeList()){
                myWriter.write(edge.getNode1() + " " + edge.getNode2() + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
