package nl.rug.oop.grapheditor.view;

import nl.rug.oop.grapheditor.io.Load;
import nl.rug.oop.grapheditor.model.Edge;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.Node;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;

/** Displays the file chooser when the user decides to save or load a file. */
public class SaveAndLoad {

    private static final JFrame parentFrame = new JFrame();
    private static File filePath;

    /**
     * Shows a file chooser and asks user to select save location and filename
     * @return file to save
     */
    public static File chooseSaveLocation(){
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

    /**
     *
     * @return true if user selected a file to load or
     * false if the user decided to cancel the operation
     */
    public static boolean chooseFile() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("graph files", "graph");
        final JFileChooser fc = new JFileChooser();
        fc.setFileFilter(filter);
        int returnVal = fc.showOpenDialog(parentFrame);
        File file;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            filePath = new File(file.getAbsolutePath());
            return true;
        }
        else if(returnVal == JFileChooser.CANCEL_OPTION){
            JOptionPane.showMessageDialog(null, "No file chosen.");
        }
        return false;
    }

    public static int saveCheck(){
        return JOptionPane.showConfirmDialog(
                null,
                "Would you like to save the graph?",
                "Save graph?",
                JOptionPane.YES_NO_OPTION);
    }

    public static File getFilePath() {
        return filePath;
    }

    /** Resets all variables in order to start a new graph */
    public static void createNewGraph(GraphModel graph){
        graph.getUndoManager().discardAllEdits();
        graph.setSelectedNode(null);
        graph.setSelectedEdge(null);
        graph.getNodeList().clear();
        graph.getEdgeList().clear();
    }

    /**
     * Loads all edges and nodes in case the user decides to load a graph.
     */
    public static void loadGraph(File loadPath, GraphModel graph) throws FileNotFoundException {
        graph.getUndoManager().discardAllEdits();
        graph.setSelectedEdge(null);
        graph.setSelectedNode(null);
        graph.setNodeList(Load.loadNodes(loadPath));
        for (Node node : graph.getNodeList()){
            node.addObserver(graph);
        }
        graph.setEdgeList(Load.loadEdges(loadPath));
        for (Edge edge: graph.getEdgeList()){
            Node node1 = graph.getNodeList().get(edge.getNode1Index());
            Node node2 = graph.getNodeList().get(edge.getNode2Index());
            edge.setNode1(node1);
            node1.addEdge(edge);
            edge.setNode2(node2);
            node2.addEdge(edge);
        }
    }
}
