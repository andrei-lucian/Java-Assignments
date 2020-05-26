package nl.rug.oop.grapheditor.io;

import nl.rug.oop.grapheditor.model.Edge;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.Node;

import java.awt.*;
import java.io.*;

public class SaveAndLoad {

    public static void save(GraphModel graph){
        try {
            FileWriter myWriter = new FileWriter("file.txt");

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

    public static void load(){

    }
}
