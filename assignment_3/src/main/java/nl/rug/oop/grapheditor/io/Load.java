package nl.rug.oop.grapheditor.io;

import nl.rug.oop.grapheditor.model.Edge;
import nl.rug.oop.grapheditor.model.Node;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Load {

    public static ArrayList<Node> loadNodes(String filename){
        Scanner sc = scanFile(filename);
        int numberNodes = sc.nextInt();
        sc.nextInt();
        ArrayList<Node> nodes = new ArrayList<>();
        for (int x = 0; x < numberNodes; x++){
            Node node = new Node();
            Rectangle rectangle = new Rectangle(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
            sc.skip(" ");
            node.setNodeBounds(rectangle);
            String name = sc.nextLine();
            node.setName(name);
            nodes.add(node);
        }
        return nodes;
    }

    public static ArrayList<Edge> loadEdges(String filename){
        Scanner sc = scanFile(filename);
        int numberNodes = sc.nextInt();
        int numberEdges = sc.nextInt();
        ArrayList<Edge> edges = new ArrayList<>();

        for(int x = 0; x <= numberNodes; x++){
            sc.nextLine();
        }

        for (int x = 0; x < numberEdges; x++){
            Edge edge = new Edge();
            int node1 = sc.nextInt();
            int node2 = sc.nextInt();
            edge.setNode1Index(node1);
            edge.setNode2Index(node2);
            edges.add(edge);
        }
        return edges;
    }

    private static Scanner scanFile(String filename){
        File save = new File(filename);
        Scanner sc = null;
        try {
            sc = new Scanner(save);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert sc != null;
        return sc;
    }
}
