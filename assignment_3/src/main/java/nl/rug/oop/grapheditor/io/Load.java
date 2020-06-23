package nl.rug.oop.grapheditor.io;

import nl.rug.oop.grapheditor.model.Edge;
import nl.rug.oop.grapheditor.model.Node;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Load {

    /**
     *
     * @param filename retrieved by the user
     * @return the nodes found in the given file
     */
    public static ArrayList<Node> loadNodes(File filename) throws FileNotFoundException {
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

    /**
     *
     * @param filename retrieved by the user
     * @return the edges found in the given file
     */
    public static ArrayList<Edge> loadEdges(File filename) throws FileNotFoundException {
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

    /**
     *
     * @param filename retrieved by the user
     * @return a scanner that scans the file
     */
    private static Scanner scanFile(File filename) throws FileNotFoundException {
        return new Scanner(filename);
    }
}
