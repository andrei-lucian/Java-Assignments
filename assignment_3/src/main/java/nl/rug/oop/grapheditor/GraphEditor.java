package nl.rug.oop.grapheditor;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.view.GraphFrame;

public class GraphEditor {

    public static void main(String[] args) {
        GraphModel graph = new GraphModel();
        new GraphFrame(graph);
    }

}