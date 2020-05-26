package nl.rug.oop.grapheditor.io;

import nl.rug.oop.grapheditor.model.Edge;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.Node;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class SaveAndLoad {

    static JFrame parentFrame = new JFrame();
    /** Saves the state of a graph to a specified file  */
    public static void save(GraphModel graph){
        File fileToSave = null;

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

    public static String chooseFile() {
        final JFileChooser fc = new JFileChooser();
        //Handle open button action.
        int returnVal = fc.showOpenDialog(parentFrame);
        File file = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
        }
        assert file != null;
        return file.getAbsolutePath();
    }

    public static ArrayList<Node> loadNodes(String filename){
        File save = new File(filename);
        Scanner sc = null;
        try {
            sc = new Scanner(save);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert sc != null;
        int numberNodes = sc.nextInt();
        ArrayList<Node> nodes = new ArrayList<>();
        for (int x = 0; x < numberNodes; x++){
            Node node = new Node();
            Rectangle rectangle = new Rectangle(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
            node.setNodeBounds(rectangle);
            String name = sc.nextLine();
            node.setName(name);
            nodes.add(node);
        }
        return nodes;
    }

    public static ArrayList<Edge> loadEdges(String filename){
        File save = new File(filename);
        Scanner sc = null;
        try {
            sc = new Scanner(save);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert sc != null;
        int numberNodes = sc.nextInt();
        int numberEdges = sc.nextInt();
        ArrayList<Edge> edges = new ArrayList<>();

        for(int x = 0; x < numberNodes; x++){
            sc.nextLine();
        }

        for (int x = 0; x < numberEdges; x++){
            Edge edge = new Edge();
            int node1 = sc.nextInt();
            int node2 = sc.nextInt();
            edge.setNode1(node1);
            edge.setNode2(node2);
            edges.add(edge);
        }
        return edges;
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
