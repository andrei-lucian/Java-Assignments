package nl.rug.oop.grapheditor;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.view.GraphFrame;

import java.io.File;

public class GraphEditor {

    public static void main(String[] args) {

        if (args.length == 1){
            String filepath = args[0];
            File fp = new File(filepath);
            GraphModel graph = new GraphModel(fp);
            new GraphFrame(graph);
        }

        else {
            GraphModel graph = new GraphModel();
            new GraphFrame(graph);
        }
    }
}