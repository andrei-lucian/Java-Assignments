package nl.rug.oop.grapheditor.io;

import com.sun.org.apache.xml.internal.serializer.Serializer;
import nl.rug.oop.grapheditor.model.Edge;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.Node;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class SaveAndLoad {

    /** Saves the state of a graph to a specified file  */
    public static void save(GraphModel graph){
        File fileToSave = null;
        JFrame parentFrame = new JFrame();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify directory and save name");

        int userSelection = fileChooser.showSaveDialog(parentFrame);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            fileToSave = fileChooser.getSelectedFile();
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
        }

        try {
            FileWriter myWriter = new FileWriter(fileToSave + ".txt");
            myWriter.write(graph.getNodeList().size() + " " + graph.getEdgeList().size() + "\n");
            for (Node node : graph.getNodeList()){
                Rectangle bounds = node.getNodeBounds();
                myWriter.write(bounds.x + " " + bounds.y + " " +
                        bounds.height + " " + bounds.width + " " + node.getName() + "\n");
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

    public static GraphModel load(String fileName) throws IOException, ClassNotFoundException {
        File saveDirectory = new File("savedGames");

        try(FileInputStream fileInputStream = new FileInputStream(saveDirectory + File.separator + fileName + ".txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            return (GraphModel)objectInputStream.readObject();
        }
    }
   /* public static GraphModel load(GraphModel graph, String fileName){
        try {
            graph = Serializer.loadGraphModel(fileName);
            System.out.println("Game loaded.");
            return graph;
        } catch (IOException e) {
            System.out.println("Could not load from the file");
        } catch (ClassNotFoundException e) {
            System.out.println("The savefile could not be used to load a player");
        }
        System.out.println("Could not load previous game, starting from scratch instead.");
        return graph;
    }*/
}
